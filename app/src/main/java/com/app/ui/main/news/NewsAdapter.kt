package com.app.ui.main.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.callBacks.RcylcvItemClick
import com.app.daggerkotlin.R
import com.app.daggerkotlin.databinding.ListItemNewsBinding
import com.app.models.NewsEntity
import java.util.ArrayList

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.MyViewHolder>()
{
    private var newsItemList: ArrayList<NewsEntity> = ArrayList()
    private var clickListener : RcylcvItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val binding : ListItemNewsBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.list_item_news, parent, false)

        return MyViewHolder(binding)
    }

    // Binds each data in the ArrayList to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val dataModel = newsItemList[position]
        holder.dataItemBinding.dataManager = dataModel
    }

    // Gets the number of Items in the list
    override fun getItemCount(): Int
    {
        return newsItemList.size
    }

    override fun getItemViewType(position: Int): Int
    {
        return position
    }

    // UpdateData method is to add items in the newsItemList and notify the adapter for the data change.
    fun updateData(newsItemList: ArrayList<NewsEntity>)
    {
        this.newsItemList = newsItemList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(mItemClickListener : RcylcvItemClick)
    {
        this.clickListener = mItemClickListener
    }

    inner class MyViewHolder(dataItemLayoutBinding:ListItemNewsBinding) : RecyclerView.ViewHolder(dataItemLayoutBinding.root), View.OnClickListener
    {
        var dataItemBinding : ListItemNewsBinding = dataItemLayoutBinding

        init
        {
            dataItemLayoutBinding.root.tag = dataItemLayoutBinding.root
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?)
        {
            if (clickListener != null)
                clickListener!!.onItemClick(view!!, adapterPosition)
        }
    }
}
