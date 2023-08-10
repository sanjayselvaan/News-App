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


class Tab2Fragment : Fragment(), RecyclerViewItemClick {
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var displayFragment: DisplayFragment
    private lateinit var recyclerViewAdapter: TabRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab2, container, false)
        recycler = view.findViewById(R.id.recyclerViewTab2)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        draftAndCompleteViewModel = ViewModelProvider(requireActivity()).get(DraftAndCompleteViewModel::class.java)
        recyclerViewAdapter = TabRecyclerViewAdapter(draftAndCompleteViewModel.completedList, this)
        recycler.adapter = recyclerViewAdapter
        displayFragment = DisplayFragment()
        return view
    }

    override fun onResume() {
        super.onResume()
        if(draftAndCompleteViewModel.ToNotifyTab2){
            if (draftAndCompleteViewModel.positionForTab2!=null){
                recyclerViewAdapter.notifyItemChanged(draftAndCompleteViewModel.positionForTab2!!)
            }
            else{
                recyclerViewAdapter.notifyItemChanged(recycler.size-1)
            }
            draftAndCompleteViewModel.ToNotifyTab2=false
        }
    }
    override fun onClickItemListener(position: Int) {
        displayFragment=DisplayFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putBoolean("draftFlag", false)
        displayFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, displayFragment, "fragment_2")
            .addToBackStack("fragment_2").commit()

    }


}