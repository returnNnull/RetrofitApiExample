package com.bam.retrofitapiexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bam.retrofitapiexample.databinding.PostsListItemBinding
import com.bam.retrofitapiexample.model.PostData

class PostResponseListAdapter : RecyclerView.Adapter<PostResponseListAdapter.ViewHolder>() {

    var items: List<PostData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemClick: (PostData) -> Unit = {}
    fun itemClick(listener: (PostData) -> Unit) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.post = items[position]
        holder.itemView.setOnClickListener {
            itemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = PostsListItemBinding.bind(view)

    }
}