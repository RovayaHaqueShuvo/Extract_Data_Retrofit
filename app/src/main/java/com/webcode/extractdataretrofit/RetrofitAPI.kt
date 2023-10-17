package com.webcode.extractdataretrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    // as we are making get request so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @GET("WO6S")
    // as we are calling data from array so we are calling
    // it with array list and naming that method as getAllCourses();
    fun getAllCourses(): Call<ArrayList<RecyclerData>>
}