package com.sabatie.felix.learntodraw.fragments

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sabatie.felix.learntodraw.R

class ResponseDialog : DialogFragment() {
    private var listener: OnNextButtonClicked? = null

    lateinit var dialogText: TextView
    lateinit var nextButton: Button
    lateinit var resultImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_response_dialog, container, false)
        isCancelable = false

        dialogText = v.findViewById(R.id.dialogText)
        nextButton = v.findViewById(R.id.nextButton)
        resultImage = v.findViewById(R.id.resultImage)
        arguments?.getString("resultText")?.let { dialogText.text = it }
        arguments?.getInt("resultImage")?.let { resultImage.setImageResource(it) }
        nextButton.setOnClickListener { onNextClick() }

        return v
    }

    private fun onNextClick() {
        dismiss()
        listener?.onNextClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextButtonClicked) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnNextButtonClicked")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnNextButtonClicked {
        fun onNextClick()
    }

}
