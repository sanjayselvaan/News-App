package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TabRecyclerViewAdapter(private val newsList: ArrayList<News>) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.MyViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        context = parent.context
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return newsList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.header.text = currentItem.Heading
        holder.body.text = currentItem.Body

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.newsHeading)
        val body: TextView = itemView.findViewById(R.id.newsBody)
        val testLayout: CardView = itemView.findViewById(R.id.test)

    }

}
