package com.yoishi.my101keywords

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [KeywordFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [KeywordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeywordFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var keywordNo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            keywordNo = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyword, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keywordNoTextView = view.findViewById<TextView>(R.id.keyword_number_text)
        keywordNoTextView.text = "キーワード" + Integer.toString(keywordNo)

        val keywordTextView = view.findViewById<TextView>(R.id.keyword_text)
        keywordTextView.text = MyKeywords.values().filter { this.keywordNo == it.keywordNo }.first().keyword

        val previousButton = view.findViewById<Button>(R.id.previous_button)
        previousButton.setOnClickListener{
            if (keywordNo == 1) {
                keywordNo = MyKeywords.values().size
                keywordNoTextView.text = "キーワード" + Integer.toString(keywordNo)
                keywordTextView.text =
                    MyKeywords.values().filter { this.keywordNo == it.keywordNo }.first().keyword
            } else {
                keywordNo--
                keywordNoTextView.text = "キーワード" + Integer.toString(keywordNo)
                keywordTextView.text =
                    MyKeywords.values().filter { this.keywordNo == it.keywordNo }.first().keyword
            }
        }

        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener{
            if (keywordNo == MyKeywords.values().size) {
                keywordNo = 1
                keywordNoTextView.text = "キーワード" + Integer.toString(keywordNo)
                keywordTextView.text =
                    MyKeywords.values().filter { this.keywordNo == it.keywordNo }.first().keyword
            } else {
                keywordNo++
                keywordNoTextView.text = "キーワード" + Integer.toString(keywordNo)
                keywordTextView.text =
                    MyKeywords.values().filter { this.keywordNo == it.keywordNo }.first().keyword
            }
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment KeywordFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) =
            KeywordFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}
