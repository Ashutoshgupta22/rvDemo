package com.aspark.rvdemo

import com.aspark.rvdemo.retrofit.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface RemoteApi {

    @GET("photos/")
    fun getImages(): Call<ArrayList<ResponseData>>
}