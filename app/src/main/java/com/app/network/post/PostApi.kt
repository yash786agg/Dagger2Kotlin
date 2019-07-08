package com.app.network.post

import com.app.models.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi
{
    // /posts?userId=1/
    @GET("posts")
    fun getPostsFromUser(@Query("userId") id: Int): Deferred<List<Post>>
}