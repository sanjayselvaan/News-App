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

class DisplayFragment() : Fragment() {
    private lateinit var timeSpentTextView: TextView
    private lateinit var bodyTextView: TextView
    private lateinit var editText: EditText
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
    private lateinit var saveButton: Button
    private lateinit var finishButton: Button
    private var draftFlag: Boolean = false
    private var position: Int = 0
    private var itemIndex: Int = 0
    private var startTimeInResume: Long = 0
    private var endTimeInPause: Long = 0
    private var draftFragment: DraftFragment?=null
    private var completeFragment: CompleteFragment?=null
    private lateinit var textWatcher: TextWatcher
    private lateinit var textToBeSet:String
    private var textChanged:Boolean=false
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        position = arguments?.getInt("position")!!
        itemIndex = arguments?.getInt("itemIndex")!!
        editText = view.findViewById(R.id.completedEditText)
        timeSpentTextView = view.findViewById(R.id.timeTakenTextView)
        saveButton = view.findViewById(R.id.saveButton)
        finishButton = view.findViewById(R.id.finishButton)
        bodyTextView = view.findViewById(R.id.completedTextView)
        draftFlag = requireArguments().getBoolean("draftFlag")
        draftFragment = parentFragmentManager.findFragmentByTag("f${0}") as DraftFragment
        completeFragment= parentFragmentManager.findFragmentByTag("f${1}") as CompleteFragment?
        draftAndCompleteViewModel =
            ViewModelProvider(requireActivity())[DraftAndCompleteViewModel::class.java]
        activity=requireActivity() as MainActivity
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
        super.onViewCreated(view, savedInstanceState)
        if (draftFlag) {
            activity.changeActionBarTitle(1)
            setUpForDraftFragment()

        } else {
            activity.changeActionBarTitle(2)
            setUpForCompleteFragment()
        }
    }

    private fun setUpForDraftFragment() {
        val newPositionInDraftList =
            draftAndCompleteViewModel.returnRespectiveItemPositionInDraftList(itemIndex)
        val newPositionDraftItem =
            draftAndCompleteViewModel.getDraftListItem(newPositionInDraftList!!)
        bodyTextView.visibility = View.GONE
        timeSpentTextView.visibility=View.GONE
        editText.visibility = View.VISIBLE
        saveButton.visibility=View.VISIBLE
        finishButton.visibility=View.VISIBLE
        editText.setText(newPositionDraftItem.Body)
        saveButton.visibility = View.VISIBLE
        textWatcher = object : TextWatcher {
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
                textChanged=true
                textToBeSet = s.toString()
            }
        }
        editText.addTextChangedListener(textWatcher)

        saveButton.setOnClickListener {
            if(textChanged){
            newPositionDraftItem.Body=textToBeSet
            }
            draftFragment?.notifyRecyclerAdapter(position, isRemoved = false)
            parentFragmentManager.popBackStack()
        }

        finishButton.setOnClickListener {
            if(textChanged){
                newPositionDraftItem.Body=textToBeSet
            }
            draftAndCompleteViewModel.addItemInCompleteList(newPositionDraftItem)
            draftAndCompleteViewModel.removeItemFromDraftList(newPositionInDraftList)
            draftFragment?.notifyRecyclerAdapter(position, isRemoved = true)
            if (completeFragment != null) {
                completeFragment?.notifyRecyclerAdapter()
            }
            parentFragmentManager.popBackStack()
        }
    }

    private fun setUpForCompleteFragment() {
        val newPositionInCompleteList =
            draftAndCompleteViewModel.returnRespectiveItemPositionInCompletedList(itemIndex)
        val newPositionCompleteItem =
            draftAndCompleteViewModel.getCompleteListItem(newPositionInCompleteList!!)
        bodyTextView.visibility = View.VISIBLE
        timeSpentTextView.visibility = View.VISIBLE
        editText.visibility = View.GONE
        saveButton.visibility=View.GONE
        finishButton.visibility=View.GONE
        timeSpentTextView.text =
            convertMillisToHMS(newPositionCompleteItem.timeSpent)
        bodyTextView.text =
            newPositionCompleteItem.Body

    }

    private fun convertMillisToHMS(milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun updateTimeTaken() {
        val newPositionInDraftList =
            draftAndCompleteViewModel.returnRespectiveItemPositionInDraftList(itemIndex)
        val newPositionDraftItem =
            newPositionInDraftList?.let { draftAndCompleteViewModel.getDraftListItem(it) }
        val newPositionInCompleteList =
            draftAndCompleteViewModel.returnRespectiveItemPositionInCompletedList(itemIndex)
        val newPositionCompleteItem =
            newPositionInCompleteList?.let { draftAndCompleteViewModel.getCompleteListItem(it) }
        if (draftFlag) {
            if (newPositionInDraftList == null) {
                if (newPositionInCompleteList != null) {
                    newPositionCompleteItem?.timeSpent = newPositionCompleteItem?.timeSpent!! + (endTimeInPause - startTimeInResume)
                    draftAndCompleteViewModel.replaceItemInCompleteList(newPositionCompleteItem!!,newPositionInCompleteList)
                }
            } else {
                newPositionDraftItem!!.timeSpent = newPositionDraftItem?.timeSpent!! + (endTimeInPause - startTimeInResume)
                draftAndCompleteViewModel.replaceItemInDraftList(newPositionDraftItem!!,newPositionInDraftList)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity.changeActionBarTitle(0)
    }
}



