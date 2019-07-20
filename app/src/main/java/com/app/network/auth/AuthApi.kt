package com.app.network.auth

import com.app.models.User
import com.app.util.NetworkResource
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi
{
    @GET("users/{id}")
    fun getUserData(@Path("id") id : Int) : Deferred<User>
}

