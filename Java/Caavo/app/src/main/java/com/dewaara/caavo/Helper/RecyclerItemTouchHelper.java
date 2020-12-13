package com.dewaara.caavo.Helper;


import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.dewaara.caavo.Adapter.CardListAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs,RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        if(listener !=null)
            listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        View foregroundView = ((CardListAdapter.MyViewHolder)viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

   @Override
   public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
   }



    public void onSelectedChangeed(RecyclerView.ViewHolder viewHolder, int actionState ) {
        if (viewHolder != null) {
            View foregroundView = ((CardListAdapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((CardListAdapter.MyViewHolder)viewHolder).viewForeground;
        getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((CardListAdapter.MyViewHolder)viewHolder).viewForeground;
        getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }
}
