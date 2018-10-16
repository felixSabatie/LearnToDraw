package com.sabatie.felix.learntodraw.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.sabatie.felix.learntodraw.R
import com.sabatie.felix.learntodraw.game.Response


class ResponseButton : Fragment() {
    lateinit var response: Response
    private var listener: OnResponseButtonClicked? = null
    private lateinit var responseTextView: TextView
    private lateinit var containerLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflatedView = inflater.inflate(R.layout.fragment_response_button, container, false)

        containerLayout = inflatedView.findViewById(R.id.container)
        containerLayout.setOnClickListener { onResponseButtonClick() }

        responseTextView = inflatedView.findViewById(R.id.responseText)
        responseTextView.text = response.text
        arguments?.getInt("background")?.let { responseTextView.setBackgroundColor(it) }

        return inflatedView
    }

    private fun onResponseButtonClick() {
        listener?.onResponseClicked(response)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnResponseButtonClicked) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnResponseButtonClicked")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnResponseButtonClicked {
        fun onResponseClicked(response: Response)
    }
}
