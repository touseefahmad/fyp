package com.tikrosoft.resturantapp.adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;



        import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.ActivityAdminOrders;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelAddItemAdmin;
import com.tikrosoft.resturantapp.pojo.ModelCustomAdminOrder;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;
import com.tikrosoft.resturantapp.pojo.ModelUpdateOrder;
import com.tikrosoft.resturantapp.response.ResponseAddItemAdmin;
import com.tikrosoft.resturantapp.response.ResponseOrderUpdate;
import com.tikrosoft.resturantapp.response.ResponseUpdateOrder;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.Arrays;
        import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AdapterAdminOrders extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Observer {
    Context context;
    ResponseOrderUpdate response;
    ModelUpdateOrder modle;

    ConnectionDetector cd;
    ProgressDialog pd;




    private final List<ModelCustomAdminOrder> mRecyclerViewItems;

    public AdapterAdminOrders(Context context, List<ModelCustomAdminOrder> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;
        response=new ResponseOrderUpdate();
        response.addObserver(this);
        modle=new ModelUpdateOrder();
        cd = new ConnectionDetector(context);

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
        final ModelCustomAdminOrder detail = (ModelCustomAdminOrder) mRecyclerViewItems.get(position);
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
        String payment= "";
        String orderStatus="";
        if(detail.getPaymentType().compareTo("0")==0){
            payment ="COD";
        }else{
            payment = "Card";
        }
        if(detail.getStatus().compareTo("0")==0){
            orderStatus ="Pending";
            myViewHolder.review.setVisibility(View.GONE);
        }else{
            orderStatus = "Delivered";
            myViewHolder.review.setVisibility(View.VISIBLE);
            myViewHolder.review.setText(detail.getReview());
            myViewHolder.review.setEnabled(false);
            myViewHolder.review.setKeyListener(null);


        }

        myViewHolder.status_order.setText(orderStatus);
        myViewHolder.type_payment.setText(payment);
        myViewHolder.changeorderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cd.isConnectionToInternet()) {
                    Toast.makeText(context, "Connect to Internet",
                            Toast.LENGTH_SHORT).show();
                } else {
                    pd= ProgressDialog.show(context, "wait", "Processing data");


                    response. updateOrder(context,detail.getOid(),"1");

                }
            }
        });








    }

    @Override
    public int getItemCount() {

        return mRecyclerViewItems.size();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        if (observable == response) {

            if (response.getResponse().getSuccess()) {
                Intent updateOrder =  new Intent(context, ActivityAdminOrders.class);
                context.startActivity(updateOrder);
                ((Activity)context).finish();
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemsName;
        TextView tvItemsqty;
        TextView tvItemsPrice;
        TextView type_payment;
        TextView status_order;
        Button changeorderStatus;
        EditText review;



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemsName = (TextView) itemView.findViewById(R.id.tvitems);
            tvItemsqty = (TextView) itemView.findViewById(R.id.tvitemsqty);
            tvItemsPrice = (TextView)itemView.findViewById(R.id.tvitemsPrice);
            type_payment = (TextView)itemView.findViewById(R.id.type_payment);
            status_order =(TextView) itemView.findViewById(R.id.status_order);
            changeorderStatus=(Button)itemView.findViewById(R.id.btnchangeStatus);
            review = (EditText)itemView.findViewById(R.id.edreview);




        }
    }
}
