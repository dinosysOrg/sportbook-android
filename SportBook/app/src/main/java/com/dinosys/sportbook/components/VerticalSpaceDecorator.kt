package com.dinosys.sportbook.components

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class VerticalSpaceDecorator(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
    }

}