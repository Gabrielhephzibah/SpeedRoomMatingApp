package com.cherish.speedroommatingapp.ui

import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.cherish.speedroommatingapp.R
import com.cherish.speedroommatingapp.ViewModelProviderFactory
import com.cherish.speedroommatingapp.di.module.GlideApp
import com.cherish.speedroommatingapp.model.mdata.UpcomingEventsData
import com.cherish.speedroommatingapp.utils.Status
import com.cherish.speedroommatingapp.viewmodel.UpcomingEventsViewModel
import kotlinx.android.synthetic.main.upcoming_layout.*
import kotlinx.coroutines.*
import java.io.File
import javax.inject.Inject

class IncomingEventsFragment : Fragment() {
    @set:Inject
    var factory: ViewModelProviderFactory? = null
    private var mAdapter: IncomingEventsAdapter? = null
    var upComingViewModel: UpcomingEventsViewModel? = null
    var newList = ArrayList<UpcomingEventsData>()
    var number: String? = null
    var cacheFailed = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upcoming_layout, container, false)
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upComingViewModel = ViewModelProvider(activity!!).get(UpcomingEventsViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = IncomingEventsAdapter( callback = {
            val item = mAdapter!!.getData(it)
            number = item.phone_number
            checkForPermission() })
            recyclerView.adapter = mAdapter

        upComingViewModel!!.getUpcomingData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.IDEAL -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        val operation = async(Dispatchers.IO) {
                            for (item in it.data!!) {
                        if (item.cost.isNullOrBlank()) {
                        } else {
                            val cost = item.cost
                            val location = item.location
                            val endDate = item.end_time
                            val imageUrl = item.image_url
                            val phoneNumber = item.phone_number
                            val venue = item.venue
                            val startDate = item.start_time
                            loadImageToCache(imageUrl!!)
                            newList.add(UpcomingEventsData(cost, endDate, imageUrl, location, phoneNumber, startDate, venue)) } }
                        }
                        operation.await()
                        withContext(Dispatchers.Main){
                            if (!cacheFailed){
                                Toast.makeText(requireActivity(),getString(R.string.failed_to_cache), Toast.LENGTH_LONG).show()
                            }
                            shimmerLayout.visibility = View.GONE
                            mAdapter!!.submitList(newList)
                        }
                }

                }
                else -> { }
            }

        })

    }

    fun checkForPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), CALL_PHONE)) {
                showMessageOKCancel(
                    getString(R.string.allow_permission),
                    DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                        myRequestPermission.launch(CALL_PHONE)
                    })
            } else {
                myRequestPermission.launch(CALL_PHONE)
            }
        } else {
            number?.let { callPhoneNumber(it) }
        }
    }


    fun callPhoneNumber(number: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(activity!!)
            .setTitle(message)
            .setPositiveButton(getString(R.string.ok), okListener)
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
            .show()
    }


    private var myRequestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->
            if (permission) {
                number?.let { callPhoneNumber(it) }
            } else {
                Toast.makeText(activity, getString(R.string.not_granted), Toast.LENGTH_LONG).show()

            }
        }



    fun loadImageToCache(image: String){
        GlideApp.with(recyclerView)
            .downloadOnly()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .load(image)
            .listener(object :
                RequestListener<File> {
                override fun onResourceReady(resource: File?, model: Any?, target: Target<File>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                   return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<File>?, isFirstResource: Boolean): Boolean {
                    cacheFailed = false
                   return false

                }
            })
            .submit(200, 200)


        
    }


}