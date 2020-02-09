package com.tikrosoft.resturantapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.android.volley.toolbox.Volley;
import com.tikrosoft.resturantapp.adapters.AdapterCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.tikrosoft.resturantapp.ActivityHome.cartList;

public class ActivityShowCart extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerShowCart;
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
       updateTotalPrice();
        populateList();
    }
    public static void updateTotalPrice(){
        if(cartList!=null) {
            TOTAL_PRICE = 0;
            if (cartList.size() > 0) {
                for (int i = 0; i < cartList.size() ; i++) {
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
        if(view.getId() == R.id.btnShowCart){
            placeOrder();
        }

    }

    public void placeOrder() {
        String URL = getResources().getString(R.string.url)+"placeorder.php?";

        RequestQueue queue = Volley.newRequestQueue(this);

        //Create json array for filter
        JSONArray array = new JSONArray();

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
                    jsonParam.put("orderid", "0");
                    jsonParam.put("userid","" );
                    jsonParam.put("itemid","" );

                    jsonParam.put("itemname", cartList.get(i).getItemName());
                    jsonParam.put("qty", cartList.get(i).getQuantiry());
                    jsonParam.put("unitprice", cartList.get(i).getUnitPrice());
                    array.put(jsonParam);

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
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
        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, URL, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        try {

                            JSONObject jObj = new JSONObject();
                            jObj = response.getJSONObject(0);
                             jObj.get("success");

                        } catch (JSONException e) {
                            Log.e("MYAPP", "unexpected JSON exception", e);
                            // Do something to recover ... or kill the app.
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("Error: ", volleyError.getMessage());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // Add headers
                return headers;
            }

            //Important part to convert response to JSON Array Again
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                String responseString;
                JSONArray array = new JSONArray();
                if (response != null) {

                    try {
                        responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        JSONObject obj = new JSONObject(responseString);
                        (array).put(obj);
                    } catch (Exception ex) {
                    }
                }
                //return array;
                return Response.success(array, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(request_json);
    }





}
