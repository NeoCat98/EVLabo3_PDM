package com.example.moviedex.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedex.R
import kotlinx.android.synthetic.main.fragment_search_bar.*
import kotlinx.android.synthetic.main.fragment_search_bar.view.*

class SearchFragment: Fragment() {
    private var listener: onSearchListener? = null

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_bar, container, false)
        view.btn_search.setOnClickListener {
            listener?.searchMovie(view.et_ref.text.toString())
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onSearchListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTabListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface onSearchListener {
        fun searchMovie(ref: String)
    }



}