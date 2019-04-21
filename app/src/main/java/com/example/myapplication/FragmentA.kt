package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.lang.RuntimeException

class FragmentA : Fragment() {

    private var listener: CounterClickListener? = null
    private var clickBtn: Button? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CounterClickListener){
            listener = context
        } else {
            throw RuntimeException("Host activity must implement CounterClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickBtn = view.findViewById(R.id.click_btn)

    }

    override fun onStart() {
        super.onStart()
        clickBtn?.setOnClickListener {
            listener?.onCounterClicked()
        }
    }
}
