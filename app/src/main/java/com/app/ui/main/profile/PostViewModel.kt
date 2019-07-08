package com.app.ui.main.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.app.network.post.PostApi
import javax.inject.Inject

class PostViewModel @Inject constructor(app : Application) : AndroidViewModel(app)
{
    private lateinit var postApi : PostApi
    private val TAG : String = "AuthActivity"

    @Inject
    fun ProfileViewModel(postApi: PostApi)
    {
        this.postApi = postApi

        Log.e(TAG, "PostViewModel: viewModel is working...")
    }
}