package com.app.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.daggerkotlin.R
import com.app.ui.main.MainActivity
import com.app.util.Constants
import com.app.util.NetworkResource
import com.app.util.PreferencesHelper
import com.app.util.SessionManager
import com.app.viewmodels.ViewModelProviderFactory
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import com.facebook.imagepipeline.request.ImageRequest
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(),View.OnClickListener
{
    @Inject
    lateinit var draweeController : PipelineDraweeControllerBuilder

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    //@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var authViewModel : AuthViewModel

    @Inject lateinit var sharedPreferences: SharedPreferences

    @Inject lateinit var sessionManager : SessionManager
    //@Inject lateinit var preferencesHelper : PreferencesHelper

    private val TAG : String = "AuthActivity"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if(sessionManager.isLogIn()!!)
            moveToNextScreen()

        Log.e(TAG, "draweeController First: $draweeController")

       // sessionManager = SessionManager(sharedPreferences)

        Log.e(TAG, "sharedPreferences: ${sharedPreferences}")

        Log.e(TAG, "PreferencesHelper: ${sessionManager}")

        Log.e(TAG, "PreferencesHelper isLogIn first: ${sessionManager.isLogIn()}")

        //Log.e(TAG, "PreferencesHelper isLogIn first: ${preferencesHelper.getBoolean(Constants.logInSess)}")

        val imageUrlFirst = "https://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg"
        val draweeControllerFirst = draweeController.setImageRequest(ImageRequest.fromUri(Uri.parse(imageUrlFirst))).setOldController(fresco_ImageView.controller).build()
        fresco_ImageView.controller = draweeControllerFirst

        login_button.setOnClickListener(this)

        authViewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel::class.java)
        //authViewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)

        /*authViewModel.getAuthUserData(8).observe(this, Observer {

            if(it != null)
            {
                Log.e(TAG,"AuthActivity email: "+it.email)
                Log.e(TAG, "AuthActivity name: "+it.name)
            }
        })*/
    }

    override fun onClick(v: View?) {

        when(v!!.id)
        {
            R.id.login_button -> {
                if(!TextUtils.isEmpty(user_id_input.text.toString())
                    && user_id_input.text.toString().toIntOrNull()!! in 1..10)
                {
                    attemptLogin(user_id_input.text.toString().toIntOrNull()!!)
                }
                else
                    Toast.makeText(this,R.string.wrong_input, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun attemptLogin(userId : Int)
    {
        authViewModel.getAuthUserData(userId).observe(this, Observer {

            if(it != null)
            {
                when(it)
                {
                    is NetworkResource.Loading ->
                    {
                        sessionManager.setLogIn(false)
                        Log.e(TAG, "SessionManager isLogIn second: ${sessionManager.isLogIn()}")
                        showProgressBar(true)
                        Log.e(TAG, "AuthActivity: NetworkResource Loading...")
                    }

                    is NetworkResource.Success ->
                    {
                        Log.e(TAG, "AuthActivity: NetworkResource Success...")
                        if(it.data != null)
                        {
                            Log.e(TAG,"AuthActivity email: "+it.data.email)
                            Log.e(TAG, "AuthActivity name: "+it.data.name)
                        }
                        showProgressBar(false)

                        sessionManager.setLogIn(true)

                        Log.e(TAG, "SessionManager isLogIn third: ${sessionManager.isLogIn()}")

                        moveToNextScreen()
                    }

                    is NetworkResource.Error ->
                    {
                        showProgressBar(false)
                        Log.e(TAG, "AuthActivity: NetworkResourceError..."+ it.message)
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun moveToNextScreen() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            progress_bar.visibility = View.GONE
        else
            progress_bar.visibility = View.VISIBLE
    }
}
