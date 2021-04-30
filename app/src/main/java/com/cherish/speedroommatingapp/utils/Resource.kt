package com.cherish.speedroommatingapp.utils

 class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object{
        fun <T> Ideal(data: T?): Resource<T> {
            return Resource(Status.IDEAL, data, null)
        }

        fun <T> Error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }


        fun <T> Empty(data: T?): Resource<T> {
            return Resource(Status.EMPTY, data, null)
        }

        fun <T> IsOnline(data: T?): Resource<T> {
            return Resource(Status.ISONLINE, data, null)
        }
    }

}