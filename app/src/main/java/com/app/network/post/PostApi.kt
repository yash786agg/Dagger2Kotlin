package com.app.network.post

import com.app.models.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi
{
    @GET("posts")
    fun getPosts(@Query("userId") id: Int): Deferred<List<Post>>
}