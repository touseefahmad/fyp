package com.tikrosoft.resturantapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.ActivityShowCart;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.Cart;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.List;

import static com.tikrosoft.resturantapp.ActivityHome.cartList;

public class AdapterCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    OnDataChangedListener mOnDataChangeListener;
    public void setOnDataChangeListener(OnDataChangedListener onDataChangeListener){
        mOnDataChangeListener = onDataChangeListener;
    }



    private final List<Cart> mRecyclerViewItems;

    public AdapterCart(Context context, List<Cart> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);





    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        TextView tvcartName;
//        TextView tvcartPrice;
//        NumberPicker cartqty_picker;
//        Button btnRemItem;

        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        Cart detail = (Cart) mRecyclerViewItems.get(position);

        myViewHolder.tvcartName.setText(detail.getItemName());
        myViewHolder.tvcartPrice.setText(detail.getItemPrice());
        myViewHolder.cartqty_picker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                cartList.get(position).setQuantiry(Integer.toString(value));
                int totalPrice = Integer.parseInt(cartList.get(position).getUnitPrice().replaceAll("[A-z]+", ""))*value;
                myViewHolder.tvcartPrice.setText(Integer.toString(totalPrice));
                cartList.get(position).setQuantiry(Integer.toString(value));
                cartList.get(position).setItemPrice(Integer.toString(totalPrice));
                ActivityShowCart.updateTotalPrice();
            }
        });
        int val = Integer.parseInt(cartList.get(position).getQuantiry().replaceAll("[A-z]+", ""));


        myViewHolder.cartqty_picker.setValue(val);

        myViewHolder.btnRemItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartList.remove(position);
                ActivityShowCart.updateTotalPrice();
                notifyDataSetChanged();
            }
        });






    }

    @Override
    public int getItemCount() {

        return mRecyclerViewItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvcartName;
        TextView tvcartPrice;
        NumberPicker cartqty_picker;
        Button btnRemItem;



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvcartName = (TextView) itemView.findViewById(R.id.tvcartName);
            tvcartPrice = (TextView) itemView.findViewById(R.id.tvcartPrice);
            cartqty_picker = (NumberPicker) itemView.findViewById(R.id.cartqty_picker);
            btnRemItem = (Button)itemView.findViewById(R.id.btnRemItem);


        }
    }


}
