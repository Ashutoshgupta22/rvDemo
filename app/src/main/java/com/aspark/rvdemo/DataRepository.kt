package com.aspark.rvdemo

import android.util.Log
import com.aspark.rvdemo.retrofit.ResponseData
import com.aspark.rvdemo.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {

    fun getRemoteImages( callback : (ArrayList<String>?) -> Unit ) {

        val remoteApi = RetrofitService.retrofit.create(RemoteApi::class.java)
        var list: ArrayList<String>?

        remoteApi.getImages().enqueue(object : Callback<ArrayList<ResponseData>> {

            override fun onResponse(call: Call<ArrayList<ResponseData>>,
                                    response: Response<ArrayList<ResponseData>>) {

                if (response.isSuccessful && response.body() != null) {

                    Log.i("DataRepo", "onResponse: response successful")
                    list = arrayListOf()

                    for ( data in response.body()!!)
                        list!!.add(data.url)

                    callback(list!!)
                }
                else {

                    Log.e("SecondViewModel", "onResponse: response unsuccessful")
                    callback(null)

                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseData>>, t: Throwable) {

                Log.e("SecondViewModel", "onFailure: Fetch Failed ",t )
                callback(null)
            }
        })

        Log.i("DataRepo", "getRemoteImages: returning list")
    }
}