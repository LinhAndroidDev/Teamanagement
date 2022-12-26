package com.example.teanmanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teanmanagement.R
import com.example.teanmanagement.model.User
import com.example.teanmanagement.adapter.UserAdapter
import com.example.teanmanagement.api.ApiListUser
import com.example.teanmanagement.model.DataListAccount
import com.example.teanmanagement.viewmodel.ViewmodelInfor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var listUser : ArrayList<User>
    lateinit var adapter : UserAdapter
    lateinit var viewModel : ViewmodelInfor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ViewmodelInfor::class.java]
        viewModel.getListAccount()

        Khaibao()

        getListAccount()
    }

    private fun Khaibao() {
        var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this@MainActivity,
            LinearLayoutManager.VERTICAL,false)

        var divider : DividerItemDecoration = DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)

        recycleView.addItemDecoration(divider)
        recycleView.layoutManager = linearLayoutManager

        listUser = ArrayList()
    }

    private fun getListAccount() {
        var gson : Gson = GsonBuilder().setDateFormat("dd-MM-yy").create()

        val retrofit : ApiListUser = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/s/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiListUser::class.java)

        loadView.visibility = View.VISIBLE
            viewModel.listUserLive.observe(this, Observer { users ->

            })

        retrofit.getListUser(1).enqueue(object : Callback<DataListAccount>{
            override fun onResponse(call: Call<DataListAccount>, response: Response<DataListAccount>) {
                listUser = (response.body() as DataListAccount).userList
                adapter = UserAdapter(this@MainActivity,listUser)
                recycleView.adapter = adapter
                loadView.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<DataListAccount>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
                loadView.visibility = View.INVISIBLE
            }

        })
    }
}