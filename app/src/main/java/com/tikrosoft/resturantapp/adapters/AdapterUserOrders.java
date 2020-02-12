package com.tikrosoft.resturantapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;

import java.util.Arrays;
import java.util.List;

public class AdapterUserOrders extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;




    private final List<ModelCustomOrder> mRecyclerViewItems;

    public AdapterUserOrders(Context context, List<ModelCustomOrder> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);





    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        TextView tvcartName;
//        TextView tvcartPrice;
//        NumberPicker cartqty_picker;
//        Button btnRemItem;

        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        ModelCustomOrder detail = (ModelCustomOrder) mRecyclerViewItems.get(position);
        String[] items = new String[detail.getItems().length];
        items= Arrays.copyOf(detail.getItems(),detail.getItems().length);
        String[] qty = new String[detail.getQty().length];
        qty= Arrays.copyOf(detail.getQty(),detail.getQty().length);

        String[] unitPrice = new String[detail.getUnitPrice().length];
        unitPrice= Arrays.copyOf(detail.getUnitPrice(),detail.getUnitPrice().length);

        String itemNames="";
        String itemQtys="";
        String itemPrices="";
        for(int i =0 ;i<detail.getItems().length;i++){
            itemNames = itemNames+items[i]+"\n";
            itemQtys = itemQtys+qty[i]+"\n";
            itemPrices = itemPrices+unitPrice[i]+"\n";



        }

        myViewHolder.tvItemsName.setText(itemNames);
        myViewHolder.tvItemsqty.setText(itemQtys);
        myViewHolder.tvItemsPrice.setText(itemPrices);








    }

    @Override
    public int getItemCount() {

        return mRecyclerViewItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemsName;
        TextView tvItemsqty;
        TextView tvItemsPrice;



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemsName = (TextView) itemView.findViewById(R.id.tvitems);
            tvItemsqty = (TextView) itemView.findViewById(R.id.tvitemsqty);
            tvItemsPrice = (TextView)itemView.findViewById(R.id.tvitemsPrice);



        }
    }
}
