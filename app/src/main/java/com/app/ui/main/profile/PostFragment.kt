package com.app.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.daggerkotlin.R
import com.app.util.NetworkResource
import com.app.util.SessionManager
import com.app.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class PostFragment : DaggerFragment()
{
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var profileViewModel : PostViewModel
    @Inject lateinit var sessionManager : SessionManager

    private val TAG : String = "AuthActivity"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel::class.java)

        subscribeObservers()
    }

    private fun subscribeObservers() {

        Log.e(TAG, "PostFragment: userId... "+sessionManager.isUserId())

        profileViewModel.getUserPostData(sessionManager.isUserId()!!).observe(viewLifecycleOwner, Observer {

            when(it)
            {
                is NetworkResource.Loading ->
                {
                    showProgressBar(true)
                    Log.e(TAG, "PostFragment: NetworkResource Loading...")
                }

                is NetworkResource.Success ->
                {
                    Log.e(TAG, "PostFragment: NetworkResource Success...")
                    if(it.data != null)
                    {
                        Log.e(TAG,"PostFragment id: "+it.data[0].id)
                        Log.e(TAG,"PostFragment userId: "+it.data[0].userId)
                        Log.e(TAG,"PostFragment Title: "+it.data[0].Title)
                        Log.e(TAG,"PostFragment Body: "+it.data[0].Body)
                    }
                    showProgressBar(false)
                }

                is NetworkResource.Error ->
                {
                    showProgressBar(false)
                    Log.e(TAG, "PostFragment: NetworkResourceError..."+ it.message)
                    Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            progress_bar.visibility = View.GONE
        else
            progress_bar.visibility = View.VISIBLE
    }
}