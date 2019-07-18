package com.app.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.network.auth.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.models.User
import com.app.util.NetworkResource

/*  Reference for Fixing the Mutli Binding Issue in Dagger2 Kotlin
* - https://stackoverflow.com/questions/56241656/dagger-missingbinding-error-when-injecting-viewmodels
* - https://stackoverflow.com/questions/56737938/error-dagger-missingbinding-androidx-lifecycle-viewmodelprovider-factory-canno/56749264#56749264
* - https://stackoverflow.com/questions/47145490/error-while-trying-to-inject-viewmodelprovider-into-activity-with-dagger2-and-ko
* */

class AuthViewModel @Inject constructor(app: Application/*, private val repository: MainRepository*/): AndroidViewModel(app)
{
    private lateinit var authApi: AuthApi

    private var authUser : MutableLiveData<NetworkResource<User>>? = null

    @Inject
    fun AuthViewModel(authApi: AuthApi)
    {
        this.authApi = authApi
    }

    fun getAuthUserData(userId : Int) : MutableLiveData<NetworkResource<User>>
    {
        if (authUser == null)
        {
            authUser = MutableLiveData() // Initialization of MutableLiveData.

            viewModelScope.launch{
                authenticateWithId(userId)
            }
        }

        return authUser!!
    }

    private suspend fun authenticateWithId(userId : Int)
    {
        authUser!!.value = NetworkResource.Loading(null)

        //Do operations on some thread async
        try
        {
            val response = authApi.getUserData(userId).await()

            withContext(Dispatchers.Main) {
                // Perform operations on the main thread
                authUser!!.value = NetworkResource.Success(response)
            }
        }
        catch (e: HttpException)
        {
            e.printStackTrace()
            authUser!!.value = NetworkResource.Error(e.code().toString(),null)
        }
    }
}