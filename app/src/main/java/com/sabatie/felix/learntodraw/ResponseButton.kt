package com.sabatie.felix.learntodraw

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sabatie.felix.learntodraw.game.Response


class ResponseButton : Fragment(), View.OnClickListener {
    lateinit var response: Response
    private var listener: OnResponseButtonClicked? = null
    private lateinit var responseTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflatedView = inflater.inflate(R.layout.fragment_response_button, container, false)

        responseTextView = inflatedView.findViewById(R.id.responseText)
        responseTextView.text = response.text
        arguments?.getInt("background")?.let { responseTextView.setBackgroundColor(it) }

        return inflatedView
    }

    override fun onClick(v: View?) {
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
