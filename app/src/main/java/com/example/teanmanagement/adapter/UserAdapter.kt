package com.example.teanmanagement.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teanmanagement.activity.UserActivity
import com.example.teanmanagement.R
import com.example.teanmanagement.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_user.view.*

class UserAdapter(private val context: Context, private val listUser: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolderUser>() {

    class ViewHolderUser(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hinh : ImageView = itemView.findViewById(R.id.anh)
        var ten : TextView = itemView.findViewById(R.id.name)
        var mail : TextView = itemView.findViewById(R.id.email)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_user,parent,false)
        return ViewHolderUser(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        var user: User = listUser.get(position)
        Picasso.get().load(user.avatar).placeholder(R.drawable.loadimage)
            .error(R.drawable.failimage).into(holder.hinh)
        holder.ten.text = user.name
        holder.mail.text = user.email

        var animation : Animation = AnimationUtils.loadAnimation(context,R.anim.anim_user)
        holder.itemView.startAnimation(animation)

        holder.itemView.setOnClickListener {
            var intent : Intent = Intent(context, UserActivity::class.java)
            intent.putExtra("infomationUser", listUser[position])
            intent.putExtra("ID",user.userId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}