package com.alirnp.androidwoocommerceapp.repository

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
   val data: T? = null,
   val message: String? = null,
   val fromServer: Boolean = false
) {
   class Loading<T>(data: T? = null) : Resource<T>(data)
   class Success<T>(data: T, fromServer: Boolean) : Resource<T>(data,fromServer = fromServer)
   class Error<T>(message: String, data: T? = null, fromServer: Boolean) : Resource<T>(data, message,fromServer)
}