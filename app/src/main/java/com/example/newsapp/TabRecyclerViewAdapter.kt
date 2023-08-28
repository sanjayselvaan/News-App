package com.example.newsapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView


class TabRecyclerViewAdapter(
    private val recyclerMutableList: MutableList<News>,
    private val itemClick: RecyclerViewItemClick
) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return recyclerMutableList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem:News = recyclerMutableList[position]
        holder.header.text = currentItem.Heading
        holder.body.text = currentItem.Body
        holder.itemView.setOnClickListener {
            itemClick.onClickItemListener(holder.adapterPosition, currentItem.index)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.newsHeading)
        val body: TextView = itemView.findViewById(R.id.newsBody)
    }



}
