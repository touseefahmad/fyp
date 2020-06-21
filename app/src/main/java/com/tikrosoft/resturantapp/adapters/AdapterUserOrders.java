package com.tikrosoft.resturantapp.adapters;

<<<<<<< HEAD
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
=======
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.tikrosoft.resturantapp.ActivityShowUseOrders;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;
import com.tikrosoft.resturantapp.pojo.ModelUpdateReview;
import com.tikrosoft.resturantapp.response.ResponseReviewUpdate;
import com.tikrosoft.resturantapp.response.ResponseUpdateReview;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AdapterUserOrders extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Observer {
    Context context;
    ResponseReviewUpdate response;
    ModelUpdateReview modle;

    ConnectionDetector cd;
    ProgressDialog pd;
=======
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;

import java.util.Arrays;
import java.util.List;

public class AdapterUserOrders extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;

>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d



    private final List<ModelCustomOrder> mRecyclerViewItems;

    public AdapterUserOrders(Context context, List<ModelCustomOrder> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;
<<<<<<< HEAD
        response=new ResponseReviewUpdate();
        response.addObserver(this);
        modle=new ModelUpdateReview();
        cd = new ConnectionDetector(context);
=======

>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
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
<<<<<<< HEAD
        final ModelCustomOrder detail = (ModelCustomOrder) mRecyclerViewItems.get(position);
=======
        ModelCustomOrder detail = (ModelCustomOrder) mRecyclerViewItems.get(position);
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
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
<<<<<<< HEAD
        myViewHolder.type_payment.setText(detail.getPaymentType());
        myViewHolder.status_order.setText(detail.getStatus());
        myViewHolder.btnchangeStatus.setVisibility(View.GONE);
        if(detail.getStatus().compareTo("1")==0){
            myViewHolder.review.setVisibility(View.VISIBLE);
            myViewHolder.btnchangeStatus.setText("Review");
            myViewHolder.btnchangeStatus.setVisibility(View.VISIBLE);
        }
        myViewHolder.btnchangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* post a comment*/
                if (!cd.isConnectionToInternet()) {
                    Toast.makeText(context, "Connect to Internet",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if(myViewHolder.review.getText().toString().compareTo("")!=0){
                    pd= ProgressDialog.show(context, "wait", "Processing data");


                    response. updatereview(context,detail.getOid(),myViewHolder.review.getText().toString());
                    }else{
                        Toast.makeText(context,"add review", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d








    }

    @Override
    public int getItemCount() {

        return mRecyclerViewItems.size();
    }

<<<<<<< HEAD
    @Override
    public void update(Observable observable, Object o) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        if (observable == response) {

            if (response.getResponse().getSuccess()) {
                Intent updateOrder =  new Intent(context, ActivityShowUseOrders.class);
                context.startActivity(updateOrder);
                ((Activity)context).finish();
            }
        }
    }

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemsName;
        TextView tvItemsqty;
        TextView tvItemsPrice;
<<<<<<< HEAD
        TextView type_payment;
        TextView status_order;
        EditText review;
        Button btnchangeStatus;
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemsName = (TextView) itemView.findViewById(R.id.tvitems);
            tvItemsqty = (TextView) itemView.findViewById(R.id.tvitemsqty);
            tvItemsPrice = (TextView)itemView.findViewById(R.id.tvitemsPrice);
<<<<<<< HEAD
            type_payment =(TextView)itemView.findViewById(R.id.type_payment);
            status_order = (TextView)itemView.findViewById(R.id.status_order);
            btnchangeStatus = (Button)itemView.findViewById(R.id.btnchangeStatus);
            review = (EditText)itemView.findViewById(R.id.edreview);

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d



        }
    }
}
