package com.cherish.speedroommatingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cherish.speedroommatingapp.data.AppDataManager
import com.cherish.speedroommatingapp.model.response.UpcomingEventsResponse
import com.cherish.speedroommatingapp.utils.Resource
import com.cherish.speedroommatingapp.utils.Status
import com.cherish.speedroommatingapp.utils.TestSchedulerProvider
import com.cherish.speedroommatingapp.viewmodel.UpcomingEventsViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpcomingViewModelTest {

    @Mock
    var appDataManager : AppDataManager? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private var observer : Observer<Resource<List<UpcomingEventsResponse>>>? = null

    private var upcomingEventsViewModel: UpcomingEventsViewModel? = null

    private var testScheduler: TestScheduler? = null

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Resource<List<UpcomingEventsResponse>>>




    @Before
    @Throws(Exception::class)
    fun setUp() {
        testScheduler = TestScheduler()
        val testSchedulerProvider =  TestSchedulerProvider(testScheduler!!)
        upcomingEventsViewModel = UpcomingEventsViewModel(appDataManager!!, testSchedulerProvider)
        upcomingEventsViewModel!!.getUpcomingData().observeForever(observer!!)


    }

    @After
    @Throws(Exception::class)
    fun tearDown(){
      testScheduler = null
      upcomingEventsViewModel = null

    }

    @Test
    fun testGetUpcomingEvent(){
        val list = mutableListOf<UpcomingEventsResponse>()
        val response = UpcomingEventsResponse("free","12/34/2012", "http//nnn","Lagos","+234677", "23/7/23","Freetown")
        list.add(response)
        `when`(appDataManager!!.getUpComingEvents(BuildConfig.SECRET_KEY)).thenReturn(Flowable.just(list))
        upcomingEventsViewModel!!.getUpcomingEvent()
        testScheduler!!.triggerActions()
        verify(observer, times(1))!!.onChanged(argumentCaptor.capture())
        
        }

    @Test
    fun idealState(){
        val list = mutableListOf<UpcomingEventsResponse>()
        val response = UpcomingEventsResponse("free","12/34/2012", "http//nnn","Lagos","+234677", "23/7/23","Freetown")
        list.add(response)
        `when`(appDataManager!!.getUpComingEvents(BuildConfig.SECRET_KEY)).thenReturn(Flowable.just(list))
        upcomingEventsViewModel!!.getUpcomingEvent()
        testScheduler!!.triggerActions()
        verify(observer, times(1))!!.onChanged(argumentCaptor.capture())
        assertEquals(Status.IDEAL,argumentCaptor.value.status)

    }

    @Test
    fun emptyState(){
        val list = mutableListOf<UpcomingEventsResponse>()
        `when`(appDataManager!!.getUpComingEvents(BuildConfig.SECRET_KEY)).thenReturn(Flowable.just(list))
        upcomingEventsViewModel!!.getUpcomingEvent()
        testScheduler!!.triggerActions()
        verify(observer, times(1))!!.onChanged(argumentCaptor.capture())
        assertEquals(Status.EMPTY,argumentCaptor.value.status)
    }

    @Test
    fun errorState(){
        `when`(appDataManager!!.getUpComingEvents(BuildConfig.SECRET_KEY)).thenReturn(Flowable.error(Throwable("")))
        upcomingEventsViewModel!!.getUpcomingEvent()
        testScheduler!!.triggerActions()
        verify(observer, times(1))!!.onChanged(argumentCaptor.capture())
        assertEquals(Status.ERROR,argumentCaptor.value.status)
    }



    }



