package com.example.teanmanagement.model

data class User(
    val userId:Int,
    val avatar : String,
    val name : String,
    val email : String) : java.io.Serializable