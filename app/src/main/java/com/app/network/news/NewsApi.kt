package com.app.network.news

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi
{
    @GET
    fun getNewsList(@Url url: String): Deferred<JsonObject>
}