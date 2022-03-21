package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class UserModel (
    // json의 키값
    @SerializedName("first_name")
    // value값

    var firstName : String,
    @SerializedName("last_name")
    var lastName : String
)