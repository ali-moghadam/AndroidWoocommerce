package com.alirnp.androidwoocommerceapp.model.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: User,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("caps")
    val caps: Caps,
    @SerializedName("cap_key")
    val capKey: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("allcaps")
    val allcaps: Allcaps,
    @SerializedName("filter")
    val filter: Any
)