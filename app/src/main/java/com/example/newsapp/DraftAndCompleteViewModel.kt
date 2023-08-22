package com.example.newsapp

import androidx.lifecycle.ViewModel

class DraftAndCompleteViewModel : ViewModel() {
    private var draftList = mutableListOf<News>()
    private var completedList = mutableListOf<News>()
    fun populateDataInDraftList() {
        val headingList = mutableListOf(
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
        val bodyList = mutableListOf(
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
        for (i in bodyList.indices) {
            val news = News(i, headingList[i], bodyList[i], 0)
            draftList.add(i, news)
        }
    }

    fun addItemInCompleteList(newsItem: News) {
        completedList.add(newsItem)
    }

    fun removeItemFromDraftList(position: Int) {
        draftList.removeAt(position)
    }

    fun getDraftListItem(position: Int): News {
        return draftList[position]
    }

    fun getCompleteListItem(position: Int): News {
        return completedList[position]
    }
    fun addItemInDraftList(newsItem: News) {
        draftList.add(newsItem)
    }
    fun removeItemFromCompleteList(position: Int) {
        completedList.removeAt(position)
    }
    fun returnRespectiveItemPositionInDraftList(position: Int): Int? {
        var returnValue: Int? = null
        for ((count, item) in draftList.withIndex()) {
            if (item.index == position) {
                returnValue = count
            }
        }
        return returnValue
    }

    fun returnRespectiveItemPositionInCompletedList(position: Int): Int? {
        var returnValue: Int? = null
        for ((count, item) in completedList.withIndex()) {
            if (item.index == position) {
                returnValue = count
            }
        }
        return returnValue

    }
    fun returnDraftListSize():Int{
        return draftList.size
    }
    fun returnCompleteListSize():Int{
        return completedList.size
    }
    fun replaceItemInDraftList(newItem:News,position: Int){
        draftList[position]=newItem
    }
    fun replaceItemInCompleteList(newItem:News,position: Int){
        completedList[position]=newItem
    }

}