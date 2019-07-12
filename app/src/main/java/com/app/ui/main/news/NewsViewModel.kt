package com.app.ui.main.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.daggerkotlin.BuildConfig
import com.app.models.NewsEntity
import com.app.network.news.NewsApi
import com.app.util.Constants.Companion.numResultTag
import com.app.util.Constants.Companion.resultsTag
import com.app.util.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.util.ArrayList
import javax.inject.Inject

class NewsViewModel @Inject constructor(app : Application) : AndroidViewModel(app)
{
    private var dataList : MutableLiveData<NetworkResource<ArrayList<NewsEntity>>>? = null
    private lateinit var newsApi : NewsApi

    @Inject
    fun ProfileViewModel(newsApi: NewsApi) {
        this.newsApi = newsApi
    }

    fun newsList() : LiveData<NetworkResource<ArrayList<NewsEntity>>>
    {
        if (dataList == null)
        {
            dataList = MutableLiveData() // Initialization of MutableLiveData.

            viewModelScope.launch {// Dispatchers.Main
                getNewsList()      // Dispatchers.Main (suspend function call)
            }
        }

        return dataList!!
    }

    private suspend fun getNewsList()
    {
        dataList!!.value = NetworkResource.Loading(null)
        try
        {
            // Perform operations on the main thread
            val response = getNewsData()

            val json = JSONObject(response.toString())
            val result = json.optInt(numResultTag,0)

            if(result != 0) {
                val newsItemList : ArrayList<NewsEntity> = ArrayList()
                val resultArray : JSONArray = json.getJSONArray(resultsTag)

                for (i in 0 until resultArray.length()) {
                    val newsObject  = resultArray.getJSONObject(i)
                    val newsEntity = NewsEntity(newsObject)
                    newsItemList.add(newsEntity)
                }
                dataList!!.value = NetworkResource.Success(newsItemList)
            }
        }
        catch (e: HttpException) {
            e.printStackTrace()
            dataList!!.value = NetworkResource.Error(e.code().toString(),null)
        }
    }

    /**
     * Heavy operation that cannot be done in the Main Thread
     */

    private suspend fun getNewsData() = withContext(Dispatchers.IO) {// Dispatchers.IO (main-safety block)
        // Start a Co-routine
        newsApi.getNewsList(BuildConfig.BASE_URL).await()
    }
}