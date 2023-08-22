package com.example.newsapp

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CompleteFragment : Fragment(), RecyclerViewItemClick{
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var displayFragment: DisplayFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_complete, container, false)
        recycler = view.findViewById(R.id.recyclerViewTab2)
        draftAndCompleteViewModel =
            ViewModelProvider(requireActivity()).get(DraftAndCompleteViewModel::class.java)
        recycler.adapter = TabRecyclerViewAdapter(false,draftAndCompleteViewModel,this)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        return view
    }

    override fun onClickItemListener(position: Int,itemIndex:Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putInt("itemIndex", itemIndex)
        bundle.putBoolean("draftFlag", false)
        displayFragment=DisplayFragment()
        displayFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, displayFragment, fragmentCompleteKey)
            .addToBackStack("fragment_2").commit()
    }

    companion object {
        const val fragmentCompleteKey = "fragment_2"
    }

    fun notifyRecyclerAdapter() {
        if (isAdded) {
            recycler.adapter?.notifyItemInserted(recycler?.size!!)
        }
    }
}