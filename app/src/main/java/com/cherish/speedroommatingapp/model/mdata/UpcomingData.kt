package com.cherish.speedroommatingapp.model.mdata

import java.io.Serializable

data class UpcomingData(var cost : String?,
                        val end_time : String?,
                        val image_url : String?,
                        val location : String?,
                        val phone_number : String?,
                        val start_time : String?,
                        val venue : String?) : Serializable {

}