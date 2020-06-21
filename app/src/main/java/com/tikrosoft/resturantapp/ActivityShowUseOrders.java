package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.adapters.AdapterUserOrders;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;
import com.tikrosoft.resturantapp.pojo.OrdersModel.ModelOrder;
import com.tikrosoft.resturantapp.pojo.OrdersModel.Order;
import com.tikrosoft.resturantapp.response.DataObservor;
import com.tikrosoft.resturantapp.response.ResponseOrder;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;
import com.tikrosoft.resturantapp.utility.ConstantKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.tikrosoft.resturantapp.utility.ConstantKeys.KEYID;
import static com.tikrosoft.resturantapp.utility.ConstantKeys.PREF_NAME;

public class ActivityShowUseOrders extends AppCompatActivity implements View.OnClickListener, Observer {
    RecyclerView recOrders;
    ResponseOrder response;
    ModelOrder modle;

    ConnectionDetector cd;
    ProgressDialog pd;
    AdapterUserOrders adapterUserOrders;
    private List<Order> ordersList = new ArrayList<>();
    private List<ModelCustomOrder> customOrdersList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_userorders);

        init();

    }
    public void init(){
        recOrders = findViewById(R.id.recOrders);

        response=new ResponseOrder();
        response.addObserver(this);
        modle=new ModelOrder();
        cd = new ConnectionDetector(this);
        getOrders();
    }
    public void getOrders(){
        if (!cd.isConnectionToInternet()) {
            Toast.makeText(getApplicationContext(), "Connect to Internet",
                    Toast.LENGTH_SHORT).show();
        } else {

           // SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);

            SharedPreferences shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            String uid = (shared.getString(KEYID, ""));
            if(uid.compareTo("guest")==0){
                Toast.makeText(getApplicationContext(),"You are logged in as a guest",Toast.LENGTH_SHORT).show();
            }else {
           /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String uid = prefs.getString(ConstantKeys.KEYID, "");*/
                pd= ProgressDialog.show(this, "wait", "Processing data");
                response.getOrders(this, uid);
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void update(Observable observable, Object o) {

        if (pd.isShowing()) {
            pd.dismiss();
        }

        if(null ==response.getResponse().getOrders()){
            return;
        }
        if (response.getResponse().getOrders().size()>1) {

            for(int i=0;i<response.getResponse().getOrders().size();i++){
                Order order = response.getResponse().getOrders().get(i);
                ordersList.add(order);
            }

            for(int i=0; i<ordersList.size();i++){
                ModelCustomOrder  customOrder =new ModelCustomOrder();
                 String oid = ordersList.get(i).getOid();
                 if(null!= customOrdersList){

                        /* List<String> a = new ArrayList<String>();
                         a.add("kk");
                         a.add("pp");*/

//                         String[] myArray = new String[a.size()];
//                         a.toArray(myArray);


                         customOrder.setOid(oid);
<<<<<<< HEAD
                         customOrder.setStatus(ordersList.get(i).getOrderStatus());
                         customOrder.setPaymentType(ordersList.get(i).getPaymentType());
                         customOrder.setReview(ordersList.get(i).getReview());
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
                         List<String> items = new ArrayList<String>();
                         List<String> qty = new ArrayList<String>();
                         List<String> prices= new ArrayList<String>();

                         for(int ii = 0; ii<ordersList.size();ii++){
                             Order order =new Order();
                             order = ordersList.get(ii);
                             if(order.getOid().compareTo(oid)==0){

                                 items.add(order.getItemname());
                                 qty.add(order.getQty());
                                 prices.add(order.getUnitprice());

                             }
                         }




                         if(null != items){
                             if(items.size()>0){
                                 String[] itemsArray = new String[items.size()];
                                 items.toArray(itemsArray);
                                 String[] qtyArray = new String[qty.size()];
                                 qty.toArray(qtyArray);
                                 String[] unitPriceArray = new String[prices.size()];
                                 prices.toArray(unitPriceArray);
                                 customOrder.setItems(itemsArray);
                                 customOrder.setQty(qtyArray);
                                 customOrder.setUnitPrice(unitPriceArray);

<<<<<<< HEAD

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
                                 if(!oidExist(oid)) {
                                     customOrdersList.add(customOrder);
                                 }
                             }
                         }else{
                             Toast.makeText(getApplicationContext(),"No items to add",Toast.LENGTH_SHORT).show();
                         }

                     }

            }


            if(customOrdersList!=null){

                adapterUserOrders = new AdapterUserOrders(this, customOrdersList);
                recOrders.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

                recOrders.setAdapter(adapterUserOrders);
            }




        }else
        {
            Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
        }

    }


    public boolean oidExist(String oid){
        if(null!= customOrdersList){
            if(customOrdersList.size()>0){
                for (ModelCustomOrder order:customOrdersList
                     ) {
                    if(order.getOid().compareTo(oid)==0){
                        return true;
                    }
                }
            }else{
                return false;
            }
        }else{
            return true;
        }
        return  false;
    }
}
