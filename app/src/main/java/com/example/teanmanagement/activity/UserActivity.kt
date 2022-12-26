package com.example.teanmanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teanmanagement.R
import com.example.teanmanagement.adapter.ExperienceAdapter
import com.example.teanmanagement.api.ApiListUser
import com.example.teanmanagement.model.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {
    lateinit var user : User
    lateinit var listExperience : ArrayList<Experience>
    lateinit var adapterExperience : ExperienceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        if(intent.getSerializableExtra("infomationUser") != null) {
            user = intent.getSerializableExtra("infomationUser") as User
        }
        setInfor()


        var linearLayout : LinearLayoutManager = LinearLayoutManager(this@UserActivity,LinearLayoutManager.VERTICAL,false)
        recycleViewUser.layoutManager = linearLayout

        listExperience = ArrayList()

        getDataInfor()
    }

    private fun getDataInfor() {
        loadExp.visibility = View.VISIBLE

        val gson : Gson =GsonBuilder().setDateFormat("dd-MM-yy").create()

        val retrofit : ApiListUser = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/s/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiListUser::class.java)

        retrofit.getInformation(1).enqueue(object : Callback<DataInformation>{
            override fun onResponse(call: Call<DataInformation>, response: Response<DataInformation>) {
                    var userList: ArrayList<Information> = response.body()!!.userList
                    var id: Int = intent.getSerializableExtra("ID") as Int

                for(i in 0 until userList.size) {
                    if (userList[i].experience != null && userList[i].userId == id) {
                        aboutUser.text = userList[i].about
                        listExperience = userList[i].experience
                        adapterExperience = ExperienceAdapter(listExperience)
                        recycleViewUser.adapter = adapterExperience
                    }
                }
               loadExp.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<DataInformation>, t: Throwable) {
                Toast.makeText(this@UserActivity,"Error",Toast.LENGTH_SHORT).show()
                loadExp.visibility = View.INVISIBLE
            }

        })
    }

    private fun setInfor() {
        Picasso.get().load(user.avatar)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.failimage)
            .into(anhUser)
        nameUser.text = user.name
        emailUser.text = user.email
    }
}