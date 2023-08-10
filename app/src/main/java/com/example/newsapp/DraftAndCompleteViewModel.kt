package com.example.newsapp

import androidx.lifecycle.ViewModel

class DraftAndCompleteViewModel:ViewModel() {
    var draftList= mutableListOf<News>()
    var completedList= mutableListOf<News>()
    var ToNotifyTab1:Boolean=false
    var ToNotifyTab2:Boolean=false
    var positionForTab1:Int?=null
    var positionForTab2:Int?=null
}