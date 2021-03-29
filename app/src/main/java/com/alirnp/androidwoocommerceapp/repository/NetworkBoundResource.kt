              package com.alirnp.androidwoocommerceapp.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import retrofit2.Response

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
abstract class NetworkBoundResource<ResultType, RequestType> {
   // Called to save the result of the API response into the database
   @WorkerThread
   protected abstract suspend fun saveCallResult(item: RequestType)

   // Called with the data in the database to decide whether to fetch
   // potentially updated data from the network.
   @MainThread
   protected abstract fun shouldFetch(data: ResultType?): Boolean

   // Called to get the cached data from the database.
   @MainThread
   protected abstract suspend fun loadFromDb(): LiveData<ResultType>

   // Called to create the API call.
   @MainThread
   protected abstract fun createCall(): LiveData<Response<RequestType>>

   // Called when the fetch fails. The child class may want to reset components
   // like rate limiter.
   protected open fun onFetchFailed() {}
}
