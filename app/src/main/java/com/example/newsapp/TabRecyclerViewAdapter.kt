package com.example.newsapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.RecyclerView


class TabRecyclerViewAdapter(
    private val isDraft: Boolean,
    private val fragmentActivity: FragmentActivity,
    private val itemClick: RecyclerViewItemClick
) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.MyViewHolder>() {
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        draftAndCompleteViewModel =
            ViewModelProvider(fragmentActivity).get(DraftAndCompleteViewModel::class.java)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        draftAndCompleteViewModel =
            ViewModelProvider(fragmentActivity).get(DraftAndCompleteViewModel::class.java)
        val size:Int
        if (isDraft) {
            size=draftAndCompleteViewModel.returnDraftListSize()
        }
        else{
            size=draftAndCompleteViewModel.returnCompleteListSize()
        }
        return size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem:News
        if(isDraft){
            currentItem=draftAndCompleteViewModel.getDraftListItem(position)
        }
        else{
            currentItem=draftAndCompleteViewModel.getCompleteListItem(position)
        }
        holder.header.text = currentItem.Heading
        holder.body.text = currentItem.Body
        holder.itemView.setOnClickListener {
            itemClick.onClickItemListener(holder.adapterPosition, currentItem.index)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.newsHeading)
        val body: TextView = itemView.findViewById(R.id.newsBody)
    }

}
