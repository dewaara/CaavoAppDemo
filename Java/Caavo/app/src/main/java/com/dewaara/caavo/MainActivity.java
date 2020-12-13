package com.dewaara.caavo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.dewaara.caavo.Adapter.CardListAdapter;
import com.dewaara.caavo.Helper.Common;
import com.dewaara.caavo.Helper.RecyclerItemTouchHelper;
import com.dewaara.caavo.Helper.RecyclerItemTouchHelperListener;
import com.dewaara.caavo.Model.Item;
import com.dewaara.caavo.Remote.IMenuRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {
    private final String URL_API = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/reciped9d7b8c.json";
    private RecyclerView recyclerView;
    private List<Item> list;
    private CardListAdapter adapter;
    private CoordinatorLayout rootLayout;
    private static final String TAG = "MainActivity";

    IMenuRequest mService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );



    mService = Common.getMenuRequest();


        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "<< Left Swipe <<" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        rootLayout = (CoordinatorLayout) findViewById( R.id.rootLayout );
        list = new ArrayList<>();
        adapter = new CardListAdapter( this, list );

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 2 );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );
        recyclerView.setAdapter( adapter );


        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerItemTouchHelper( 0, ItemTouchHelper.LEFT, this );
        new ItemTouchHelper( itemTouchHelperCallBack ).attachToRecyclerView( recyclerView );

        // request API
        addItemToCard();
    }

    private void addItemToCard() {
        mService.getMenuList( URL_API )
                .enqueue( new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        list.clear();  // remove old item
                        list.addAll( response.body() );
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {

                    }
                } );
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CardListAdapter.MyViewHolder) {
            String name = list.get( viewHolder.getAdapterPosition() ).getName();

            final Item deletedItem = list.get( viewHolder.getAdapterPosition() );
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem( deleteIndex );

            Snackbar snackbar = Snackbar.make( rootLayout, name + "removed from MDH card", Snackbar.LENGTH_LONG );
            snackbar.setAction( "UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem( deletedItem, deleteIndex );
                }
            } );
            snackbar.setActionTextColor( Color.YELLOW );
            snackbar.show();
        }
    }


}

