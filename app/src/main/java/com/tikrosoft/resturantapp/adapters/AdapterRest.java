package com.tikrosoft.resturantapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.ActivityItemsList;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.resturants.Rest;

import java.util.List;

public class AdapterRest extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    Context context;
    //List<Rest> mylist;
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;

    // An Activity's Context.

    // The list of Native ads and menu items.
    private final List<Rest> mRecyclerViewItems;

    public AdapterRest(Context context, List<Rest> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_resturant, parent, false);
        return new MyViewHolder(view);





    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



                MyViewHolder myViewHolder = (MyViewHolder) holder;
                Rest detail = (Rest) mRecyclerViewItems.get(position);

                myViewHolder.tvItemRestName.setText(detail.getRestName());
                myViewHolder.tvOpeningTime.setText(detail.getRestOpeningTime());
                myViewHolder.tvClosingTime.setText(detail.getRestClosingTime());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent items = new Intent(context, ActivityItemsList.class);
                        items.putExtra("restid",mRecyclerViewItems.get(position).getRestId());
                        context.startActivity(items);
                        Log.e("ID: ",mRecyclerViewItems.get(position).getRestId()+"");
                    }
                });






    }

    @Override
    public int getItemCount() {

        return mRecyclerViewItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemRestName;
        TextView tvOpeningTime;
        TextView tvClosingTime;



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemRestName = (TextView) itemView.findViewById(R.id.tvItemRestName);
            tvOpeningTime = (TextView) itemView.findViewById(R.id.tvOpeningTime);
            tvClosingTime = (TextView) itemView.findViewById(R.id.tvClosingTime);

        }
    }




}