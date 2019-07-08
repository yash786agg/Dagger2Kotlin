package com.app.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration constructor(verticalSpaceHeight: Int) : RecyclerView.ItemDecoration()
{
    private var verticalSpaceHeight : Int = 0

    init {
        this.verticalSpaceHeight = verticalSpaceHeight
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
    {
        outRect.bottom = verticalSpaceHeight
    }
}