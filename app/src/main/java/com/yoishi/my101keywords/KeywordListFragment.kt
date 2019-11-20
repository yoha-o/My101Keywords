package com.yoishi.my101keywords

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [KeywordListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [KeywordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeywordListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyword_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kwsValues = MyKeywords.values()
        val keywords = mutableListOf<Keyword>()
        for (kv in kwsValues) {
            keywords.add(Keyword("キーワード" + Integer.toString(kv.keywordNo), kv.keyword))
        }

        val listView = view.findViewById<ListView>(R.id.keyword_list_view)
        listView.adapter = KeywordAdapter(this.context!!, keywords)

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, KeywordFragment.newInstance(position + 1))
            transaction?.addToBackStack(null)
            transaction?.commit()
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
         * @return A new instance of fragment KeywordListFragment.
         */
        @JvmStatic
        fun newInstance() =
            KeywordListFragment().apply {}
    }
}
