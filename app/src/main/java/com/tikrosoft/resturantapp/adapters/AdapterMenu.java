package com.tikrosoft.resturantapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.ActivityAddToCart;
import com.tikrosoft.resturantapp.ActivityItemsList;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.item.Item;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


        Context context;



        private final List<Item> mRecyclerViewItems;

        public AdapterMenu(Context context, List<Item> recyclerViewItems) {
            this.context = context;
            this.mRecyclerViewItems = recyclerViewItems;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
            return new MyViewHolder(view);





        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Item detail = (Item) mRecyclerViewItems.get(position);

            myViewHolder.tvMenuName.setText(detail.getMenuItemName());
            myViewHolder.tvMenuDesc.setText(detail.getMenuItemDescription());
            myViewHolder.tvMenuPrice.setText(detail.getMenuItemPrice());
           /* holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Log.e("ID: ",mRecyclerViewItems.get(position).getMenuId()+"");
                }
            });*/
            myViewHolder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cart = new Intent(context, ActivityAddToCart.class);
                    cart.putExtra("Item",mRecyclerViewItems.get(position));
                    context.startActivity(cart);
                }
            });






        }

        @Override
        public int getItemCount() {

            return mRecyclerViewItems.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvMenuName;
            TextView tvMenuDesc;
            TextView tvMenuPrice;
            Button btnAddToCart;



            MyViewHolder(@NonNull View itemView) {
                super(itemView);

                tvMenuName = (TextView) itemView.findViewById(R.id.tvMenuName);
                tvMenuDesc = (TextView) itemView.findViewById(R.id.tvMenuDesc);
                tvMenuPrice = (TextView) itemView.findViewById(R.id.tvMenuPrice);
                btnAddToCart = (Button)itemView.findViewById(R.id.btnAddToCart);


            }
        }


    }
