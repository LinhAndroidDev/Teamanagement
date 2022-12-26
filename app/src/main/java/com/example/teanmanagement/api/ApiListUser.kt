package com.example.teanmanagement.api

import com.example.teanmanagement.model.DataListAccount
import com.example.teanmanagement.model.DataInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiListUser {
    //https://www.dropbox.com/s/r1ipog039blb5j8/acount_list.json?dl=1
    //https://www.dropbox.com/s/d8xgmn67528b826/account_detail.json?dl=1
    @GET("r1ipog039blb5j8/acount_list.json")
    fun getListUser(@Query("dl") dl : Int) : Call<DataListAccount>

    @GET("d8xgmn67528b826/account_detail.json")
    fun getInformation(@Query("dl") dl : Int) : Call<DataInformation>
}