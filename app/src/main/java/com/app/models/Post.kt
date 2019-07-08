package com.app.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(@SerializedName("userId") val userId :Int,
                @SerializedName("id") val id :Int,
                @SerializedName("title") val Title :String,
                @SerializedName("body") val Body : String) : Parcelable

