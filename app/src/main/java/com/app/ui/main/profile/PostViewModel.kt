package com.app.ui.main.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.models.Post
import com.app.network.post.PostApi
import com.app.util.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class PostViewModel @Inject constructor(app : Application) : AndroidViewModel(app)
{
    private var userPost : MutableLiveData<NetworkResource<List<Post>>>? = null
    private lateinit var postApi : PostApi
    private val TAG : String = "AuthActivity"

    @Inject
    fun ProfileViewModel(postApi: PostApi)
    {
        this.postApi = postApi

        Log.e(TAG, "PostViewModel: viewModel is working...")
    }

    fun getUserPostData(userId : Int) : MutableLiveData<NetworkResource<List<Post>>>
    {
        if (userPost == null)
        {
            userPost = MutableLiveData() // Initialization of MutableLiveData.

            getUserPost(userId)
        }

        return userPost!!
    }

    private fun getUserPost(userId: Int)
    {
        userPost!!.value = NetworkResource.Loading(null)

        // Start a Co-routine
        GlobalScope.launch(Dispatchers.Default) {

            //Do operations on some thread async
            try
            {
                val response = postApi.getPosts(userId).await()

                withContext(Dispatchers.Main) {
                    // Perform operations on the main thread

                    Log.e(TAG, "PostViewModel: UserData response: $response")

                    userPost!!.value = NetworkResource.Success(response)
                }
            }
            catch (e: HttpException)
            {
                e.printStackTrace()

                Log.e(TAG, "PostViewModel: HttpException message: ${e.message()}")
                Log.e(TAG, "PostViewModel: HttpException code: ${e.code()}")

                withContext(Dispatchers.Main) {
                    // Perform operations on the main thread

                    userPost!!.value = NetworkResource.Error(e.code().toString(),null)
                }
            }
        }
    }
}