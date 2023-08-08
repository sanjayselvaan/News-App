package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab1Fragment() : Fragment(), RecyclerViewItemClick {
    private lateinit var recycler: RecyclerView
    private lateinit var newsList: MutableList<News>
    private lateinit var headingList: MutableList<String>
    private lateinit var bodyList: MutableList<String>
    private lateinit var displayFragment: DisplayFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerViewTab1)
        recycler.visibility=View.VISIBLE
        recycler.layoutManager = LinearLayoutManager(context)
        getData()
        recycler.adapter = TabRecyclerViewAdapter(newsList, this)
        displayFragment=DisplayFragment(true)
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
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten"
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
            "gtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygg"
        )
        newsList = mutableListOf()
        for (i in bodyList.indices) {
            val news = News(headingList[i], bodyList[i])
            newsList.add(news)
        }
    }


    override fun onClickItemListener(position:Int) {
        val bundle=Bundle()
        bundle.putString("body",newsList[position].Body)
        displayFragment.arguments=bundle
        parentFragmentManager.beginTransaction().replace(R.id.mainContainer, displayFragment)
            .addToBackStack("fragment_1").commit()
    }

}