package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelUpdateOrder;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseUpdateOrder extends  DataObservor {

    ModelUpdateOrder response;

    public ModelUpdateOrder getResponse(){
        return response;
    }
    public void updateOrderStatus(Context context, String orderid, String orderstatus){

        Map<String, String> params = new HashMap<String, String>();
        params.put("orderid", orderid);
        params.put("orderstatus", orderstatus);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelUpdateOrder> request = new GsonRequest<ModelUpdateOrder>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "updateorderstatus.php?",
                ModelUpdateOrder.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<ModelUpdateOrder> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelUpdateOrder>() {

            @Override
            public void onResponse(ModelUpdateOrder response) {
                // TODO Auto-generated method stub
                try{
                    ResponseUpdateOrder.this.response=response;
                }catch(Exception e)
                {
                    ResponseUpdateOrder.this.response=new ModelUpdateOrder();
                    ResponseUpdateOrder.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseUpdateOrder.this.getResponse().toString());
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
                ResponseUpdateOrder.this.response= new ModelUpdateOrder();
                ResponseUpdateOrder.this.response.setSuccess(false);
                ResponseUpdateOrder.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }




}
