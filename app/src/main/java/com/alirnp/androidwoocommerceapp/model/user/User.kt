package com.alirnp.androidwoocommerceapp.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("ID")
    val iD: String,
    @SerializedName("user_login")
    val userLogin: String,
    @SerializedName("user_pass")
    val userPass: String,
    @SerializedName("user_nicename")
    val userNicename: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_url")
    val userUrl: String,
    @SerializedName("user_registered")
    val userRegistered: String,
    @SerializedName("user_activation_key")
    val userActivationKey: String,
    @SerializedName("user_status")
    val userStatus: String,
    @SerializedName("display_name")
    val displayName: String
)