package com.app.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(@SerializedName("id") val id :Int,
                @SerializedName("name") val name :String,
                @SerializedName("email") val email :String,
                @SerializedName("website") val website : String) : Parcelable