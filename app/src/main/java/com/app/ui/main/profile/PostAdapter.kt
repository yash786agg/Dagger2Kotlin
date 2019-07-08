package com.app.ui.main.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.daggerkotlin.R
import com.app.daggerkotlin.databinding.PostItemAdapterBinding
import com.app.models.Post
import java.util.ArrayList

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyViewHolder>()
{
    private var postItemList: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val binding : PostItemAdapterBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.post_item_adapter, parent, false)

        return MyViewHolder(binding)
    }

    // Binds each data in the ArrayList to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val dataModel = postItemList[position]
        holder.dataItemBinding.dataManager = dataModel
    }

    // Gets the number of Items in the list
    override fun getItemCount(): Int
    {
        return postItemList.size
    }

    override fun getItemViewType(position: Int): Int
    {
        return position
    }

    // UpdateData method is to add items in the postItemList and notify the adapter for the data change.
    fun updateData(postItemList: ArrayList<Post>)
    {
        this.postItemList = postItemList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(dataItemLayoutBinding:PostItemAdapterBinding) : RecyclerView.ViewHolder(dataItemLayoutBinding.root)
    {
        var dataItemBinding : PostItemAdapterBinding = dataItemLayoutBinding

        init
        {
            dataItemLayoutBinding.root.tag = dataItemLayoutBinding.root
        }
    }
}