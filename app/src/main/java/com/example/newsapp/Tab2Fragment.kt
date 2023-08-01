package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Tab2Fragment : Fragment(){
    private lateinit var recycler: RecyclerView
    private lateinit var newsList:MutableList<News>
    private lateinit var headingList:MutableList<String>
    private lateinit var bodyList:MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headingList= mutableListOf("11111","22222","33333","44444","55555","66666","77777","88888","99999","101010")
        bodyList= mutableListOf("ASDFDAfduojnwbfibsbdfjnsdjlnf","ahbfiubdgifbdafiabkf","badkbfbudbfbdabfdfuobdf","fkadbfubdbfbdf","ndbufbdbdsvjbvd","dajbfnudbfdfb","ndndfnudhf","kabhdbhkaksdbabsd","tdfttfthfhff","gtfctftgv")
        recycler=view.findViewById(R.id.recyclerViewTab2)
        recycler.layoutManager= LinearLayoutManager(activity)
        getData()
    }
    private fun getData(){
        newsList= mutableListOf()
        for(i in bodyList.indices){
            val news = News(headingList[i],bodyList[i])
            newsList.add(news)
        }
        recycler.adapter=TabRecyclerViewAdapter(newsList)
    }


}