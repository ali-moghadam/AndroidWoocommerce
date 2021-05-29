package com.alirnp.androidwoocommerceapp.model.user


import com.google.gson.annotations.SerializedName

data class Caps(
    @SerializedName("administrator")
    val administrator: Boolean
)