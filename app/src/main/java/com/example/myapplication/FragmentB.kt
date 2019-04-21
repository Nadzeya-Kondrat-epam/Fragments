package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A fragment implements FragmentSettable and
 * contains [tvCounter].
 *
 *  @author Sergey Shtukin
 */

class FragmentB : Fragment() {

    private var counterLbl: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        counterLbl = view.findViewById(R.id.tvCounter)
        arguments?.getInt(COUNTER_KEY)?.let {
            counterLbl?.setText(it.toString())
        }
    }

    companion object {
        private val COUNTER_KEY = "1002"

        fun newInstance(count: Int) = FragmentB().apply {
            arguments = Bundle().apply {
                putInt(COUNTER_KEY, count)
            }
        }
    }
}
