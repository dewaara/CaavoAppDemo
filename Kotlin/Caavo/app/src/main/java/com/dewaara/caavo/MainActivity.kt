package com.dewaara.caavo

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.dewaara.caavo.Adapter.CardListAdapter
import com.dewaara.caavo.Adapter.CardListAdapter.MyViewHolder
import com.dewaara.caavo.Helper.Common
import com.dewaara.caavo.Helper.RecyclerItemTouchHelper
import com.dewaara.caavo.Helper.RecyclerItemTouchHelperListener
import com.dewaara.caavo.Model.Item
import com.dewaara.caavo.Remote.IMenuRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), RecyclerItemTouchHelperListener {
    private val URL_API = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/reciped9d7b8c.json"
    private var recyclerView: RecyclerView? = null
    private var list: MutableList<Item>? = null
    private var adapter: CardListAdapter? = null
    private var rootLayout: CoordinatorLayout? = null
    var mService: IMenuRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mService = Common.menuRequest


        //Setup Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "<< Left Swipe <<"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        rootLayout = findViewById<View>(R.id.rootLayout) as CoordinatorLayout
        list = ArrayList()
        adapter = CardListAdapter(this, list as ArrayList<Item>)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(baseContext, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView!!.adapter = adapter
        val itemTouchHelperCallBack: ItemTouchHelper.SimpleCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)

        // request API
        addItemToCard()
    }

    private fun addItemToCard() {
        mService!!.getMenuList(URL_API)
                .enqueue(object:Callback<List<Item>?> {
                    override fun onResponse(call: Call<List<Item>?>, response: Response<List<Item>?>) {
                        list!!.clear() // remove old item
                        list!!.addAll(response.body()!!)
                        adapter!!.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<List<Item>?>, t: Throwable) {}
                })
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is MyViewHolder) {
            val name = list!![viewHolder.getAdapterPosition()].name
            val deletedItem = list!![viewHolder.getAdapterPosition()]
            val deleteIndex = viewHolder.getAdapterPosition()
            adapter!!.removeItem(deleteIndex)
            val snackbar = Snackbar.make(rootLayout!!, name + "removed from MDH card", Snackbar.LENGTH_LONG)
            snackbar.setAction("UNDO") { adapter!!.restoreItem(deletedItem, deleteIndex) }
            snackbar.setActionTextColor(Color.YELLOW)
            snackbar.show()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<Item>?>) {


}
