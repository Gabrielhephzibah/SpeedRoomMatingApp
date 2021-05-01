package com.cherish.speedroommatingapp.model.response

import java.io.Serializable

data class UpcomingEventsResponse(var cost : String? = null,
                                  val end_time : String? = null,
                                  val image_url : String? = null,
                                  val location : String? = null,
                                  val phone_number : String? = null,
                                  val  start_time : String?= null,
                                  val venue : String? = null){

}