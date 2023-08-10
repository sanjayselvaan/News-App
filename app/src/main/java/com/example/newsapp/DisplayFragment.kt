package com.example.newsapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.TimeUnit

class DisplayFragment : Fragment() {
    private lateinit var showTimeTextView: TextView
    private lateinit var bodyTextView: TextView
    private lateinit var editText: EditText
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    private lateinit var saveButton: Button
    private var position: Int = 0
    private var startTimeInResume: Long = 0
    private var endTimeInPause: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        position = arguments?.getInt("position")!!
        editText = view.findViewById(R.id.completedEditText)
        draftAndCompleteViewModel =
            ViewModelProvider(requireActivity())[DraftAndCompleteViewModel::class.java]
        return view
    }

    override fun onPause() {
        super.onPause()
        endTimeInPause = System.currentTimeMillis()
        updateTimeTaken()
    }

    override fun onResume() {
        super.onResume()
        startTimeInResume = System.currentTimeMillis()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showTimeTextView = view.findViewById(R.id.timeTakenTextView)
        saveButton = view.findViewById<Button>(R.id.saveButton)
        bodyTextView = view.findViewById(R.id.completedTextView)
        super.onViewCreated(view, savedInstanceState)
        val draftFlag = requireArguments().getBoolean("draftFlag")
        if (draftFlag) {
            editText.text.clear()
            bodyTextView = view.findViewById(R.id.completedTextView)
            bodyTextView.visibility = View.GONE
            editText.visibility = View.VISIBLE
            editText.setText(draftAndCompleteViewModel.draftList[position].Body)
            saveButton.visibility = View.VISIBLE
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    draftAndCompleteViewModel.draftList[position].Body = s.toString()
                    draftAndCompleteViewModel.ToNotifyTab1 = true
                    draftAndCompleteViewModel.positionForTab1 = position
                }
            }
            editText.addTextChangedListener(textWatcher)
            saveButton.setOnClickListener {

                val newPosition = returnRespectiveItemPositionInCompletedList(position)
                if (newPosition != null) {
                    draftAndCompleteViewModel.completedList[newPosition] =
                        News(
                            position,
                            draftAndCompleteViewModel.draftList[position].Heading,
                            editText.text.toString(),
                            draftAndCompleteViewModel.draftList[position].timeSpent
                        )
                    draftAndCompleteViewModel.ToNotifyTab1 = true
                    draftAndCompleteViewModel.ToNotifyTab2 = true
                    draftAndCompleteViewModel.positionForTab1 = position
                    draftAndCompleteViewModel.positionForTab2 = newPosition

                } else {

                    draftAndCompleteViewModel.completedList.add(
                        News(
                            position,
                            draftAndCompleteViewModel.draftList[position].Heading,
                            editText.text.toString(),
                            draftAndCompleteViewModel.draftList[position].timeSpent
                        )
                    )
                    draftAndCompleteViewModel.ToNotifyTab1 = true
                    draftAndCompleteViewModel.ToNotifyTab2 = true
                    draftAndCompleteViewModel.positionForTab1 = position
                    draftAndCompleteViewModel.positionForTab2 = null

                }
                parentFragmentManager.popBackStack()
            }
        } else {
            position = arguments?.getInt("position")!!
            bodyTextView = view.findViewById(R.id.completedTextView)
            bodyTextView.visibility = View.VISIBLE
            editText.visibility = View.GONE
            showTimeTextView = view.findViewById(R.id.timeTakenTextView)
            showTimeTextView.visibility = View.VISIBLE
            showTimeTextView.text =
                convertMillisToHMS(draftAndCompleteViewModel.completedList[position].timeSpent)
            bodyTextView.text = draftAndCompleteViewModel.completedList[position].Body
        }

    }

    private fun returnRespectiveItemPositionInCompletedList(position: Int): Int? {
        var returnValue: Int? = null
        for ((count, item) in draftAndCompleteViewModel.completedList.withIndex()) {
            if (item.index == position) {
                returnValue = count
            }
        }
        return returnValue

    }

    private fun convertMillisToHMS(milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun updateTimeTaken() {
        draftAndCompleteViewModel.draftList[position].timeSpent =
            draftAndCompleteViewModel.draftList[position].timeSpent + (endTimeInPause - startTimeInResume)
        val completedListPosition = returnRespectiveItemPositionInCompletedList(position)
        if (completedListPosition != null && draftAndCompleteViewModel.completedList.size > 0) {
            draftAndCompleteViewModel.completedList[completedListPosition] =
                draftAndCompleteViewModel.draftList[position]
        }

    }
}


