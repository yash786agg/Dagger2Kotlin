package com.app.ui.main.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.daggerkotlin.BuildConfig
import com.app.models.NewsEntity
import com.app.network.news.NewsApi
import com.app.util.Constants.Companion.numResultTag
import com.app.util.Constants.Companion.resultsTag
import com.app.util.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    private val TAG = NewsViewModel::class.java.simpleName


    @Inject
    fun ProfileViewModel(newsApi: NewsApi)
    {
        this.newsApi = newsApi
        Log.e(TAG,"NewsViewModel is working")
    }

    fun newsList() : LiveData<NetworkResource<ArrayList<NewsEntity>>>
    {
        if (dataList == null)
        {
            dataList = MutableLiveData() // Initialization of MutableLiveData.

            getNewsList()
        }

        return dataList!!
    }

    private fun getNewsList()
    {
        dataList!!.value = NetworkResource.Loading(null)

        // Start a Co-routine
        GlobalScope.launch(Dispatchers.Default) {

            //Do operations on some thread async
            try
            {
                val response = newsApi.getNewsList(BuildConfig.BASE_URL).await()

                Log.e(TAG, "NewsListViewModel response: $response")

                withContext(Dispatchers.Main) {
                    // Perform operations on the main thread
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
            }
            catch (e: HttpException) {
                e.printStackTrace()

                withContext(Dispatchers.Main) {
                    // Perform operations on the main thread
                    dataList!!.value = NetworkResource.Error(e.code().toString(),null)
                }
            }
        }
    }
}