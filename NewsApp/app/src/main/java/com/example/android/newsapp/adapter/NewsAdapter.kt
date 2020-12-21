package com.example.android.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.provider.LiveFolders.INTENT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.newsapp.R

class NewsAdapter
        (private var titles:List<String>,
         private var details:List<String>,
         private var images:List<String>,
         private var links:List<String>,
         private var author:List<String>
         ): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.title)
        val itemDetail : TextView = itemView.findViewById(R.id.description)
        val itemPicture: ImageView = itemView.findViewById(R.id.image_view)
        val itemAuthor: TextView = itemView.findViewById(R.id.published_by)
        init {
            itemView.setOnClickListener { v: View ->
                val position :Int = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(links[position])
                startActivity(itemView.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text =titles[position]
        holder.itemDetail.text =details[position]
        holder.itemAuthor.text =author[position]
        Glide.with(holder.itemPicture).load(images[position]).into(holder.itemPicture)
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}