package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class DisplayFragment(private val DraftFlag:Boolean) : Fragment() {
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(DraftFlag){
            textView=view.findViewById(R.id.completedTextView)
            textView.visibility=View.GONE
            editText=view.findViewById(R.id.completedEditText)
            editText.visibility=View.VISIBLE
            editText.setText(arguments?.getString("body"))
        }
        else{
            textView=view.findViewById(R.id.completedTextView)
            textView.visibility=View.VISIBLE
            editText=view.findViewById(R.id.completedEditText)
            editText.visibility=View.GONE
            textView.text = arguments?.getString("body")
        }
    }
}