package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.OrdersModel.ModelOrder;
import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponseOrder extends DataObservor {



    ModelOrder response;

    public ModelOrder getResponse() {
        return response;
    }
    public void getOrders(Context context,String uid) {


        String url = context.getResources().getString(R.string.url)
                + "getordersbyid.php?rest_id="+uid;

        Log.d("URL String:", url);
        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
        GsonRequest<ModelOrder> request = new GsonRequest<ModelOrder>(
                Request.Method.GET, url, ModelOrder.class, null,
                successListener(), errorListener());
        requestQueue.add(request);
    }


    private Response.Listener<ModelOrder> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelOrder>() {

            @Override
            public void onResponse(ModelOrder response) {
                // TODO Auto-generated method stub
                try{
                    ResponseOrder.this.response=response;
                }catch(Exception e)
                {
                    ResponseOrder.this.response=new ModelOrder();

                }
                triggerObservers();
            }
        };
    }
    private Response.ErrorListener errorListener() {
        // TODO Auto-generated method stub
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                ResponseOrder.this.response= new ModelOrder();

                triggerObservers();

            }
        };
    }

}
