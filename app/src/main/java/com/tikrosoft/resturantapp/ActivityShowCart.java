package com.tikrosoft.resturantapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.tikrosoft.resturantapp.adapters.AdapterCart;
import com.tikrosoft.resturantapp.utility.ConstantKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.tikrosoft.resturantapp.ActivityHome.cartList;
import static com.tikrosoft.resturantapp.utility.ConstantKeys.KEYID;
import static com.tikrosoft.resturantapp.utility.ConstantKeys.PREF_NAME;

public class ActivityShowCart extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerShowCart;
<<<<<<< HEAD
    RelativeLayout payment;
    LinearLayout cardDetailsForm;
    RadioGroup paymentType;
    RadioButton cod, cardDetails;
    Button cardDone;
    EditText cvv, cardno, expiry;
    int payment_type=0;

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
   public static TextView tvTotalPrice;
    Button btnClearCart;
    Button btnPlaceOrder;
    AdapterCart adapterCart;
   public static int TOTAL_PRICE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    public void init(){
        recyclerShowCart = (RecyclerView)findViewById(R.id.recyclerShowCart);
        tvTotalPrice = (TextView)findViewById(R.id.tvTotalPrice);
        btnClearCart = (Button)findViewById(R.id.btnClearCart);
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);
        btnClearCart.setOnClickListener(this);
        btnPlaceOrder.setOnClickListener(this);
<<<<<<< HEAD
        payment = (RelativeLayout) findViewById(R.id.payments);
        cardDetailsForm = (LinearLayout)findViewById(R.id.cardDetailsForm);
        paymentType  = (RadioGroup) findViewById(R.id.paymentType);
        cod = (RadioButton) findViewById(R.id.cod);
        cod.setChecked(true);
        cardDetails = (RadioButton)findViewById(R.id.cc);

        cardno = (EditText)findViewById(R.id.cardno);
        expiry= (EditText)findViewById(R.id.expiry);
        cvv = (EditText)findViewById(R.id.cvv);

        paymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(!cod.isChecked()){
                    cardDetailsForm.setVisibility(View.VISIBLE);
                }else{
                    cardDetailsForm.setVisibility(View.GONE);
                }
            }
        });
        cardDone = (Button)findViewById(R.id.cardDone);
        cardDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cod.isChecked()){
                    payment_type = 0;
                }else{
                    if(cvv.getText().toString().compareTo("")==0
                    || expiry.getText().toString().compareTo("")==0
                    || cardno.getText().toString().compareTo("")==0){
                        Toast.makeText(getApplicationContext(),"Fill all fields", Toast.LENGTH_SHORT).show();
                    }else{
                        //update card cardlist items status
                        payment_type=1;
                    }
                }
                placeOrder();
            }
        });


=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
       updateTotalPrice();
        populateList();
    }
    public static void updateTotalPrice(){
        if(cartList!=null) {
            TOTAL_PRICE = 0;
            if (cartList.size() > 0) {
                for (int i = 0; i < cartList.size() ; i++) {
                    String itemPrice = cartList.get(i).getItemPrice();

                    TOTAL_PRICE += Integer.parseInt(cartList.get(i).getItemPrice().replaceAll("[A-z]+", ""));
                    tvTotalPrice.setText(Integer.toString(TOTAL_PRICE));
                }
            }else{
                tvTotalPrice.setText("0");
            }
        }
    }
    public void populateList(){
        adapterCart = new AdapterCart(this, cartList);
        recyclerShowCart.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        recyclerShowCart.setAdapter(adapterCart);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnClearCart){
            if(cartList != null){
                for(int i = cartList.size()-1; i>=0; i--){
                    cartList.remove(i);
                }
            }
            adapterCart.notifyDataSetChanged();
            tvTotalPrice.setText("0");
        }
        if(view.getId() == R.id.btnPlaceOrder){
<<<<<<< HEAD
            setPaymentDetails();

=======
            placeOrder();
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
        }

    }

    public void placeOrder() {
<<<<<<< HEAD
        String URL = "http://23.92.28.13/rest/placeorderupdated.php?";
=======
        String URL = getResources().getString(R.string.url)+"placeorder.php?";
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d

        RequestQueue queue = Volley.newRequestQueue(this);

        //Create json array for filter
        JSONArray array = new JSONArray();
        final JSONObject jsonOBJ = new JSONObject();

        //Create json objects for two filter Ids
        JSONObject jsonParam;
        if(cartList!=null){
            for(int i=0;i<cartList.size();i++){
                jsonParam = new JSONObject();

                try {
                    /*{"arr":[
                        {
                            "orderid":"",
                                "userid":"1",
                                "itemid":"2",
                                "itemname":"item2",
                                "qty":"10",
                                "unitprice":"100"
                        },
                        {
                            "orderid":"",
                                "userid":"1",
                                "itemid":"2",
                                "itemname":"item2",
                                "qty":"10",
                                "unitprice":"100"
                        }
]
                    }*/



                    //Add string params
                    SharedPreferences shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                    String uid = (shared.getString(KEYID, ""));
                    jsonParam.put("orderid", "0");
                    jsonParam.put("userid",uid);
                    jsonParam.put("itemid",cartList.get(i).getItemId() );
                    jsonParam.put("itemname", cartList.get(i).getItemName());//"cartList.get(i).getItemName());
                    jsonParam.put("qty", cartList.get(i).getQuantiry());
                    jsonParam.put("unitprice", cartList.get(i).getUnitPrice());
<<<<<<< HEAD
                    jsonParam.put("payment_type", Integer.toString(payment_type));
                    jsonParam.put("order_status", "0");
                    array.put(jsonParam);
//                    jsonParam.put("review", "");
=======
                    array.put(jsonParam);
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }


            try {
<<<<<<< HEAD
                jsonOBJ.put("data", array);
=======
                jsonOBJ.put("arr", array);
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
                Log.e("val","VAL");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
//        JSONObject jsonParam = new JSONObject();
//        JSONObject jsonParam1 = new JSONObject();

       /* try {
            //Add string params
            jsonParam.put("NAME", "XXXXXXXXXXXXXX");
            jsonParam.put("USERNAME", "XXXXXXXXXXXXXX");
            jsonParam.put("PASSWORD", "XXXXXXXXXXXX");
            jsonParam1.put("NAME", "XXXXXXXXXXXXXX");
            jsonParam1.put("USERNAME", "XXXXXXXXXXXXXX");
            jsonParam1.put("PASSWORD", "XXXXXXXXXXXX");
        } catch (
                JSONException e) {
            e.printStackTrace();
        }*/

       // array.put(jsonParam);
       // array.put(jsonParam1);


        JsonObjectRequest requestJson = new JsonObjectRequest(Request.Method.POST, URL, jsonOBJ, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("ORDERRESP:",response.toString());
             //   Toast.makeText()

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("String",jsonOBJ.toString());
                Log.e("ORDERRESP:",error.getMessage());
            }

        });
        queue.add(requestJson);


//        JsonObjectRequest request_json = new JsonObject(Request.Method.POST, URL, jsonOBJ,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JsonObject response) {
//                        //Get Final response
//                        try {
//
//                            JSONObject jObj = new JSONObject();
//                            jsonOBJ = response;
//                          //  boolean resp  = jObj.getBoolean("success");
//                          //  Toast.makeText(getApplicationContext(),"Resp:"+jObj.getString("msg"),Toast.LENGTH_SHORT).show();
//
//                           /* if(!resp){
//                                 Toast.makeText(getApplicationContext(),"Resp:"+jObj.getString("msg"),Toast.LENGTH_SHORT).show();
//                             }*/
//
//                        } catch (JSONException e) {
//                            Log.e("MYAPP", "unexpected JSON exception", e);
//                          //  Toast.makeText(getApplicationContext(),"Resp:"+"Exception Thrown",Toast.LENGTH_SHORT).show();
//
//                            // Do something to recover ... or kill the app.
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                VolleyLog.e("Error: ", volleyError.getMessage());
//
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                // Add headers
//                return headers;
//            }
//
//            //Important part to convert response to JSON Array Again
//            @Override
//            protected Response<JsonObject> parseNetworkResponse(NetworkResponse response) {
//                String responseString;
//                JSONArray array = new JSONArray();
//                if (response != null) {
//
//                    try {
//                        responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//                        JSONObject obj = new JSONObject(responseString);
//                        (array).put(obj);
//                    } catch (Exception ex) {
//                    }
//                }
//                //return array;
//                return Response.success(jsonOBJ, HttpHeaderParser.parseCacheHeaders(response));
//            }
//        };
//        queue.add(request_json);
    }

<<<<<<< HEAD
    public void setPaymentDetails(){
        payment.setVisibility(View.VISIBLE);
    }

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d




}
