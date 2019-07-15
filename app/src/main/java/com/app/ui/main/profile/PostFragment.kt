package com.app.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.daggerkotlin.R
import com.app.models.Post
import com.app.util.NetworkResource
import com.app.util.SessionManager
import com.app.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import java.util.ArrayList
import javax.inject.Inject
import com.app.util.VerticalSpaceItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class PostFragment : DaggerFragment()
{
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var profileViewModel : PostViewModel
    @Inject lateinit var sessionManager : SessionManager
    @Inject lateinit var postAdapter: PostAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_post,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel::class.java)

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {

        profileViewModel.getUserPostData(sessionManager.isUserId()!!).observe(viewLifecycleOwner, Observer {

            when(it)
            {
                is NetworkResource.Loading ->
                {
                    showProgressBar(true)
                }

                is NetworkResource.Success ->
                {
                    if(it.data != null)
                        postAdapter.updateData(it.data as ArrayList<Post>)

                    showProgressBar(false)
                }

                is NetworkResource.Error ->
                {
                    showProgressBar(false)
                    Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpaceItemDecoration(15)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = postAdapter
    }

    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            progress_bar.visibility = View.GONE
        else
            progress_bar.visibility = View.VISIBLE
    }
}