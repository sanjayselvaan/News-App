package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.TimeUnit

class DisplayFragment(
    private val DraftFlag: Boolean,
    private val timeHashMap: MutableMap<Int, Long>
) : Fragment() {
    private lateinit var showTimeTextView: TextView
    private lateinit var bodyTextView: TextView
    private lateinit var editText: EditText
    private lateinit var sampleViewModel: SampleViewModel
    private var position: Int = 0
    private var startTimeInResume: Long = 0
    private var endTimeInPause: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onPause() {
        super.onPause()
        endTimeInPause = System.currentTimeMillis()
        println(endTimeInPause)
        if (timeHashMap.containsKey(position)) {
            timeHashMap[position] = timeHashMap[position]!! + (endTimeInPause - startTimeInResume)
        } else {
            timeHashMap[position] = endTimeInPause - startTimeInResume
        }
    }

    override fun onResume() {
        super.onResume()
        startTimeInResume = System.currentTimeMillis()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleViewModel = ViewModelProvider(this)[SampleViewModel::class.java]
        println(startTimeInResume)
        position = arguments?.getInt("position")!!
        if (DraftFlag) {
            bodyTextView = view.findViewById(R.id.completedTextView)
            bodyTextView.visibility = View.GONE
            editText = view.findViewById(R.id.completedEditText)
            editText.visibility = View.VISIBLE
            editText.setText(arguments?.getString("body"))
            showTimeTextView = view.findViewById(R.id.timeTakenTextView)
            showTimeTextView.visibility = View.VISIBLE
            if (timeHashMap.containsKey(position)) {
                showTimeTextView.text = convertMillisToHMS(timeHashMap[position]!!)
            }

        } else {
            bodyTextView = view.findViewById(R.id.completedTextView)
            bodyTextView.visibility = View.VISIBLE
            editText = view.findViewById(R.id.completedEditText)
            editText.visibility = View.GONE
            bodyTextView.text = arguments?.getString("body")
        }
    }

    private fun convertMillisToHMS(milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


}