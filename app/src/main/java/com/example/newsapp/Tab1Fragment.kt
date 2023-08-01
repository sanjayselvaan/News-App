package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab1Fragment : Fragment() {
    private lateinit var recycler:RecyclerView
    private lateinit var newsList:ArrayList<News>
    private lateinit var headingList:ArrayList<String>
    private lateinit var bodylist:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headingList= arrayListOf("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")
        bodylist= arrayListOf("ASDFDAfduojnwbfibsbdfjnsdjlnf","ahbfiubdgifbdafiabkf","badkbfbudbfbdabfdfuobdf","fkadbfubdbfbdf","ndbufbdbdsvjbvd","dajbfnudbfdfb","ndndfnudhf","kabhdbhkaksdbabsd","tdfttfthfhff","gtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygg","ASDFDAfduojnwbfibsbdfjnsdjlnf","ahbfiubdgifbdafiabkf","badkbfbudbfbdabfdfuobdf","fkadbfubdbfbdf","ndbufbdbdsvjbvd","dajbfnudbfdfb","ndndfnudhf","kabhdbhkaksdbabsd","tdfttfthfhff","gtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygggtfctftgvjfjgdddhtdhfgjfgjfvhjghgfyudrsesesfggnfhjfuyfyfhvhhfgyfgyljvjhffyjfjvfyjfjfjktkfkhffgvhvhmvhjvhjvjhggygg")
        recycler=view.findViewById(R.id.recyclerViewTab1)
        recycler.layoutManager=LinearLayoutManager(context)
        newsList= arrayListOf<News>()
        getData()
    }
    private fun getData(){
        for(i in bodylist.indices){
            val news = News(headingList[i],bodylist[i])
            newsList.add(news)
        }
        recycler.adapter=TabRecyclerViewAdapter(newsList)
    }



}