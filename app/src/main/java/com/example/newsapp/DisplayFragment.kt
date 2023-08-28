package com.example.newsapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentDisplayBinding
import java.util.concurrent.TimeUnit

class DisplayFragment() : Fragment() {
    private lateinit var binding:FragmentDisplayBinding
    private lateinit var draftAndCompleteViewModel: DraftAndCompleteViewModel
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
        binding = FragmentDisplayBinding.inflate(inflater, container, false)
        position = arguments?.getInt("position")!!
        itemIndex = arguments?.getInt("itemIndex")!!
        draftFlag = requireArguments().getBoolean("draftFlag")
        draftFragment = parentFragmentManager.findFragmentByTag("f${0}") as DraftFragment
        completeFragment= parentFragmentManager.findFragmentByTag("f${1}") as CompleteFragment?
        draftAndCompleteViewModel =
            ViewModelProvider(requireActivity())[DraftAndCompleteViewModel::class.java]
        activity=requireActivity() as MainActivity
        return binding.root
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
        binding.completedTextView.visibility = View.GONE
        binding.timeTakenTextView.visibility=View.GONE
        binding.completedEditText.visibility = View.VISIBLE
        binding.saveButton.visibility=View.VISIBLE
        binding.finishButton.visibility=View.VISIBLE
        if(arguments?.getString("newTextInEditText")!=null){
            textChanged=true
            binding.completedEditText.setText(arguments?.getString("newTextInEditText"))
            textToBeSet=arguments?.getString("newTextInEditText")!!
        }
        else{
            binding.completedEditText.setText(newPositionDraftItem.Body)
        }
        binding.saveButton.visibility = View.VISIBLE
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
                arguments?.putString("newTextInEditText",textToBeSet)
            }
        }
        binding.completedEditText.addTextChangedListener(textWatcher)

        binding.saveButton.setOnClickListener {
            if(textChanged){
            newPositionDraftItem.Body=textToBeSet
            }
            draftFragment?.notifyRecyclerAdapter(position, isRemoved = false)
            parentFragmentManager.popBackStack()
        }

        binding.finishButton.setOnClickListener {
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
        binding.completedTextView.visibility = View.VISIBLE
        binding.timeTakenTextView.visibility = View.VISIBLE
        binding.completedEditText.visibility = View.GONE
        binding.saveButton.visibility=View.GONE
        binding.finishButton.visibility=View.GONE
        binding.timeTakenTextView.text =
            convertMillisToHMS(newPositionCompleteItem.timeSpent)
        binding.completedTextView.text =
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



