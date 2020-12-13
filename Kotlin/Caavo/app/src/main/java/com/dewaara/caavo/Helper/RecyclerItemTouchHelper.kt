package com.dewaara.caavo.Helper

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.dewaara.caavo.Adapter.CardListAdapter.MyViewHolder

class RecyclerItemTouchHelper(dragDirs: Int, swipeDirs: Int, private val listener: RecyclerItemTouchHelperListener?) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener?.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView: View? = (viewHolder as MyViewHolder).viewForeground
        getDefaultUIUtil().clearView(foregroundView)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    fun onSelectedChangeed(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView: View? = (viewHolder as MyViewHolder).viewForeground
            getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView: View? = (viewHolder as MyViewHolder).viewForeground
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView: View? = (viewHolder as MyViewHolder).viewForeground
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }
}