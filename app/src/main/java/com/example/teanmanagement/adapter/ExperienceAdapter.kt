package com.example.teanmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teanmanagement.R
import com.example.teanmanagement.model.Experience
import com.squareup.picasso.Picasso

class ExperienceAdapter(val listEp : ArrayList<Experience>) : RecyclerView.Adapter<ExperienceAdapter.ExperViewHolder>() {
    class ExperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var anhEp : ImageView = itemView.findViewById(R.id.anhEp)
        var nameEp : TextView = itemView.findViewById(R.id.nameEp)
        var characterEp : TextView = itemView.findViewById(R.id.characterEp)
        var timeEp : TextView = itemView.findViewById(R.id.timeEp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_experience,parent,false)
        return ExperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExperViewHolder, position: Int) {
        var experience : Experience = listEp[position]
        Picasso.get().load(experience.logo)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.failimage)
            .into(holder.anhEp)
        holder.nameEp.text = experience.companyName
        holder.characterEp.text = experience.position
        holder.timeEp.text = experience.startTime
    }

    override fun getItemCount(): Int {
        return listEp.size
    }
}