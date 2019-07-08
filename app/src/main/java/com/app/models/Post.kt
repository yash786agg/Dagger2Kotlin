package com.app.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(@SerializedName("UserId") val userId :Int,
                @SerializedName("Id") val id :Int,
                @SerializedName("Title") val Title :String,
                @SerializedName("Body") val Body : String) : Parcelable