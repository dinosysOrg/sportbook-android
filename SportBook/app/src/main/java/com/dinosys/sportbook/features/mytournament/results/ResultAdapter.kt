package com.dinosys.sportbook.features.mytournament.results

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.dinosys.sportbook.R

class ResultAdapter(): BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return listPosition.toString()
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_mytournament_result_child, null)
        }

        return view!!
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return 5
    }

    override fun getGroup(listPosition: Int): Any {
        return "Group ${listPosition}"
    }

    override fun getGroupCount(): Int {
        return 5
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listTitle = getGroup(listPosition) as String
        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_mytournament_result_header, null)
        }
        val listTitleTextView = view!!.findViewById(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return view
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}