package com.dewaara.caavo.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.dewaara.caavo.Adapter.CardListAdapter.MyViewHolder
import com.dewaara.caavo.Model.Item
import com.dewaara.caavo.R
import com.squareup.picasso.Picasso

class CardListAdapter(private val context: Context, private val list: List<Item>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.description.text = item.description
        holder.price.text = item.price
        Picasso.with(context)
                .load(item.image)
                .into(holder.thumbnail)
        holder.img_decrease.setOnClickListener { Toast.makeText(context, "-1", Toast.LENGTH_SHORT).show() }
        holder.img_increase.setOnClickListener { Toast.makeText(context, "+1", Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Item, position: Int) {
        list.add(position, item)
        notifyItemInserted(position)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var description: TextView
        var price: TextView
        var thumbnail: ImageView
        var viewBackground: RelativeLayout
        var viewForeground: RelativeLayout
        var img_decrease: ImageView
        var img_increase: ImageView
        var txt_quantity: TextView

        init {
            name = itemView.findViewById(R.id.name)
            description = itemView.findViewById(R.id.description)
            price = itemView.findViewById(R.id.price)
            thumbnail = itemView.findViewById(R.id.thumbnail)
            viewBackground = itemView.findViewById(R.id.view_background)
            viewForeground = itemView.findViewById(R.id.view_foreground)
            txt_quantity = itemView.findViewById<View>(R.id.txt_cart_quantity) as TextView
            img_decrease = itemView.findViewById<View>(R.id.img_decrease) as ImageView
            img_increase = itemView.findViewById<View>(R.id.img_increase) as ImageView
        }
    }
}

private fun <E> List<E>.add(position: Int, item: E) {
    TODO("Not yet implemented")
}

private fun <E> List<E>.removeAt(position: Int) {
    TODO("Not yet implemented")
}


