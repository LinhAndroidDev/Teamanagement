package com.example.teanmanagement.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teanmanagement.adapter.UserAdapter
import com.example.teanmanagement.api.ApiListUser
import com.example.teanmanagement.model.DataListAccount
import com.example.teanmanagement.model.Information
import com.example.teanmanagement.model.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewmodelInfor() : ViewModel() {
    lateinit var retrofit : ApiListUser
    val listUserLive = MutableLiveData<List<User>>()
    val infoListLive = MutableLiveData<List<Information>>()

     fun getListAccount() {
        var gson : Gson = GsonBuilder().setDateFormat("dd-MM-yy").create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/s/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiListUser::class.java)

         listUserLive.postValue(abc)
         listUserLive.value = abc

        retrofit.getListUser(1).enqueue()
    }
}