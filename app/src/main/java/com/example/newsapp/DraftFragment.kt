package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DraftFragment() : Fragment(), RecyclerViewItemClick {
    private lateinit var recycler: RecyclerView
    private lateinit var displayFragment: DisplayFragment
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_draft, container, false)
        recycler = view.findViewById(R.id.recyclerViewTab1)
        draftAndCompleteViewModel=
            ViewModelProvider(requireActivity()).get(DraftAndCompleteViewModel::class.java)
        recycler.itemAnimator = null
        recycler.adapter = TabRecyclerViewAdapter(true,draftAndCompleteViewModel, this)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            draftAndCompleteViewModel.populateDataInDraftList()
        }
    }

    override fun onClickItemListener(position: Int, itemIndex: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putInt("itemIndex", itemIndex)
        bundle.putBoolean("draftFlag", true)
        displayFragment = DisplayFragment()
        displayFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, displayFragment, fragmentDraftKey)
            .addToBackStack(fragmentDraftKey).commit()
    }

    companion object {
        const val fragmentDraftKey = "fragment_1"
    }

    fun notifyRecyclerAdapter(position: Int?, isRemoved: Boolean) {
        if (position != null) {
            if (isRemoved) {
                recycler.adapter?.notifyItemRemoved(position)
            } else {
                recycler.adapter?.notifyItemChanged(position)
            }
        }
    }

}
