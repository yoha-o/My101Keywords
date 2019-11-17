package com.yoishi.my101keywords

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_keyword.view.*

class KeywordAdapter(private val context: Context, private val keywords: List<Keyword>): BaseAdapter() {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return keywords.count()
    }

    override fun getItem(position: Int): Keyword {
        return keywords[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = layoutInflater.inflate(R.layout.row_keyword, parent, false)
        view.kwNo.text = keywords[position].kwNo
        view.kw.text = keywords[position].kw
        return view
    }
}