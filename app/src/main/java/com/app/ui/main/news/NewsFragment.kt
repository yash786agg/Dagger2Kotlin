package com.app.ui.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.callBacks.RcylcvItemClick
import com.app.daggerkotlin.R
import com.app.models.NewsEntity
import com.app.util.NetworkResource
import com.app.util.VerticalSpaceItemDecoration
import com.app.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.ArrayList
import javax.inject.Inject

class NewsFragment : DaggerFragment(), RcylcvItemClick
{
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var newsViewModel : NewsViewModel
    @Inject lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProviders.of(this,providerFactory).get(NewsViewModel::class.java)

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers()
    {
        newsViewModel.newsList().observe(viewLifecycleOwner, Observer {

            when(it)
            {
                is NetworkResource.Loading ->
                {
                    showProgressBar(true)
                }

                is NetworkResource.Success ->
                {
                    if(it.data != null)
                        newsAdapter.updateData(it.data)

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
        news_recylv.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpaceItemDecoration(15)
        news_recylv.addItemDecoration(itemDecoration)
        news_recylv.adapter = newsAdapter

        newsAdapter.setOnItemClickListener(this)
    }

    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            progress_bar.visibility = View.GONE
        else
            progress_bar.visibility = View.VISIBLE
    }

    override fun onItemClick(view: View, position: Int) {

    }

}