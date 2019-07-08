package com.app.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.app.daggerkotlin.R
import com.app.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment()
{
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var profileViewModel : PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)

        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()

        profileViewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel::class.java)

        return view
    }
}