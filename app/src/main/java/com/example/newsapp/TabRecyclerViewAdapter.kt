package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView

class TabRecyclerViewAdapter(private val newsList: MutableList<News>) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.MyViewHolder>() {
    private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        context=parent.context
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return newsList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.header.text = currentItem.Heading
        holder.body.text = currentItem.Body
        holder.itemView.setOnClickListener {
            Toast.makeText(context,currentItem.Heading,Toast.LENGTH_SHORT).show()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.newsHeading)
        val body: TextView = itemView.findViewById(R.id.newsBody)
    }

}
