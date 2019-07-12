package com.app.ui.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.models.Post
import com.app.network.post.PostApi
import com.app.util.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class PostViewModel @Inject constructor(app : Application) : AndroidViewModel(app)
{
    private var userPost : MutableLiveData<NetworkResource<List<Post>>>? = null
    private lateinit var postApi : PostApi

    @Inject
    fun ProfileViewModel(postApi: PostApi) {
        this.postApi = postApi
    }

    fun getUserPostData(userId : Int) : MutableLiveData<NetworkResource<List<Post>>>
    {
        if (userPost == null)
        {
            userPost = MutableLiveData() // Initialization of MutableLiveData.
            viewModelScope.launch{  // Dispatchers.Main
                getUserPost(userId) // Dispatchers.Main (suspend function call)
            }
        }

        return userPost!!
    }

    private suspend fun getUserPost(userId: Int)
    {
        userPost!!.value = NetworkResource.Loading(null)

        //Do operations on some thread async
        try {
            val response = getPostsData(userId)// Dispatchers.Main
            // Perform operations on the main thread
            userPost!!.value = NetworkResource.Success(response)
        }
        catch (e: HttpException) {
            e.printStackTrace()
            userPost!!.value = NetworkResource.Error(e.code().toString(),null)
        }
    }

    /**
     * Heavy operation that cannot be done in the Main Thread
     */

    private suspend fun getPostsData(userId : Int) = withContext(Dispatchers.IO) {// Dispatchers.IO (main-safety block)
        // Start a Co-routine
        postApi.getPosts(userId).await()
    }
}