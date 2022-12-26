package com.example.teanmanagement.model

data class Information(
    val userId : Int,
    val about : String,
    val experience: ArrayList<Experience>
)