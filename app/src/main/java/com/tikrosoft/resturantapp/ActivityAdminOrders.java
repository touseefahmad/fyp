package com.tikrosoft.resturantapp;

import android.app.AppComponentFactory;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.adapters.AdapterAdminOrders;
import com.tikrosoft.resturantapp.adapters.AdapterUserOrders;
import com.tikrosoft.resturantapp.pojo.ModelAllOrders;
import com.tikrosoft.resturantapp.pojo.ModelCustomAdminOrder;
import com.tikrosoft.resturantapp.pojo.ModelCustomOrder;
import com.tikrosoft.resturantapp.pojo.OrdersModel.ModelOrder;
import com.tikrosoft.resturantapp.pojo.OrdersModel.Order;
import com.tikrosoft.resturantapp.response.ResponseAdminOrders;
import com.tikrosoft.resturantapp.response.ResponseOrder;
import com.tikrosoft.resturantapp.response.Rest;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.tikrosoft.resturantapp.utility.ConstantKeys.KEYID;
import static com.tikrosoft.resturantapp.utility.ConstantKeys.PREF_NAME;

public class ActivityAdminOrders extends AppCompatActivity implements View.OnClickListener, Observer {
    RecyclerView recOrders;
    ResponseAdminOrders response;
    ModelAllOrders modle;

    ConnectionDetector cd;
    ProgressDialog pd;
    AdapterAdminOrders adapterUserOrders;
    private List<Rest> ordersList = new ArrayList<>();
    private List<ModelCustomAdminOrder> customOrdersList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        init();

    }
    public void init(){
        recOrders = findViewById(R.id.adminOrders);

        response=new ResponseAdminOrders();
        response.addObserver(this);
        modle=new ModelAllOrders();
        cd = new ConnectionDetector(this);
        getOrders();
    }
    public void getOrders(){
        if (!cd.isConnectionToInternet()) {
            Toast.makeText(getApplicationContext(), "Connect to Internet",
                    Toast.LENGTH_SHORT).show();
        } else {

            // SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);


           /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String uid = prefs.getString(ConstantKeys.KEYID, "");*/
                pd= ProgressDialog.show(this, "wait", "Processing data");
                response.getAdminOrders(this);


        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void update(Observable observable, Object o) {

        if (pd.isShowing()) {
            pd.dismiss();
        }

        if(null ==response.getResponse().getRest()){
            return;
        }
        if (response.getResponse().getRest().size()>1) {

            for(int i=0;i<response.getResponse().getRest().size();i++){
                Rest order = response.getResponse().getRest().get(i);
                ordersList.add(order);
            }

            for(int i=0; i<ordersList.size();i++){
                ModelCustomAdminOrder  customOrder =new ModelCustomAdminOrder();
                String oid = ordersList.get(i).getOid();
                if(null!= customOrdersList){

                        /* List<String> a = new ArrayList<String>();
                         a.add("kk");
                         a.add("pp");*/

//                         String[] myArray = new String[a.size()];
//                         a.toArray(myArray);


                    customOrder.setOid(oid);
                    List<String> items = new ArrayList<String>();
                    List<String> qty = new ArrayList<String>();
                    List<String> prices= new ArrayList<String>();
                    customOrder.setPaymentType(ordersList.get(i).getPaymentType());
                    customOrder.setStatus(ordersList.get(i).getOrderStatus());
                    customOrder.setReview(ordersList.get(i).getReview());

                    for(int ii = 0; ii<ordersList.size();ii++){
                        Rest order =new Rest();
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

                adapterUserOrders = new AdapterAdminOrders(this, customOrdersList);
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
                for (ModelCustomAdminOrder order:customOrdersList
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
