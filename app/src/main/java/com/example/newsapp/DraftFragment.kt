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
    private lateinit var newsList: MutableList<News>
    private lateinit var headingList: MutableList<String>
    private lateinit var bodyList: MutableList<String>
    private lateinit var displayFragment: DisplayFragment
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    private lateinit var recyclerViewAdapter: TabRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_draft, container, false)
        recycler = view.findViewById(R.id.recyclerViewTab1)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        draftAndCompleteViewModel =
            ViewModelProvider(requireActivity()).get(DraftAndCompleteViewModel::class.java)
        recyclerViewAdapter = TabRecyclerViewAdapter(draftAndCompleteViewModel.draftList, this)
        recycler.adapter = recyclerViewAdapter
        displayFragment = DisplayFragment()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            getData()
        }
    }

    override fun onResume() {
        super.onResume()
        if (draftAndCompleteViewModel.ToNotifyTab1) {
            draftAndCompleteViewModel.positionForTab1?.let {
                recyclerViewAdapter.notifyItemChanged(
                    it
                )
            }
            draftAndCompleteViewModel.ToNotifyTab1 = false
        }

    }

    private fun getData() {
        headingList = mutableListOf(
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fiveteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
            "Twenty"
        )
        bodyList = mutableListOf(
            "ASDFDAfduojnwbfibsbdfjnsdjlnf",
            "ahbfiubdgifbdafiabkf",
            "badkbfbudbfbdabfdfuobdf",
            "fkadbfubdbfbdf",
            "ndbufbdbdsvjbvd",
            "dajbfnudbfdfb",
            "ndndfnudhf",
            "kabhdbhkaksdbabsd",
            "tdfttfthfhff",
            "gtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygg",
            "ASDFDAfduojnwbfibsbdfjnsdjlnf",
            "ahbfiubdgifbdafiabkf",
            "badkbfbudbfbdabfdfuobdf",
            "fkadbfubdbfbdf",
            "ndbufbdbdsvjbvd",
            "dajbfnudbfdfb",
            "ndndfnudhf",
            "kabhdbhkaksdbabsd",
            "tdfttfthfhff",
            "gtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygg12312"
        )
        newsList = mutableListOf()
        for (i in bodyList.indices) {
            val news = News(i, headingList[i], bodyList[i], 0)
            draftAndCompleteViewModel.draftList.add(i, news)
        }
    }

    override fun onClickItemListener(position: Int) {
        displayFragment = DisplayFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putBoolean("draftFlag", true)
        displayFragment.arguments = null
        displayFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, displayFragment, fragmentDraftKey)
            .addToBackStack(fragmentDraftKey).commit()
    }

    companion object{
        const val fragmentDraftKey="fragment_1"
    }
}