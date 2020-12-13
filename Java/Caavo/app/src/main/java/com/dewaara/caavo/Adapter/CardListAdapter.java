package com.dewaara.caavo.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dewaara.caavo.R;
import com.dewaara.caavo.Model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {

   private Context context;
   private List<Item> list;

   public CardListAdapter(Context context, List<Item> list) {
       this.context = context;
       this.list = list;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.card_list_item,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

       final Item item = list.get(position);
       holder.name.setText(item.getName());
       holder.description.setText(item.getDescription());
       holder.price.setText(item.getPrice());
        Picasso.with(context)
                .load(item.getImage())
                .into(holder.thumbnail);


        holder.img_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "-1", Toast.LENGTH_SHORT).show();

            }
        });

        holder.img_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "+1", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Item item, int position)
    {
        list.add(position,item);
        notifyItemInserted(position);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,description,price;
        public ImageView thumbnail;
        public RelativeLayout viewBackground,viewForeground;

        public ImageView img_decrease,img_increase;
        public TextView txt_quantity;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            txt_quantity = (TextView)itemView.findViewById(R.id.txt_cart_quantity);

            img_decrease = (ImageView) itemView.findViewById(R.id.img_decrease);
            img_increase = (ImageView) itemView.findViewById(R.id.img_increase);

        }
    }
}
