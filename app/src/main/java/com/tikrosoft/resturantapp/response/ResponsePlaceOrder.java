package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelPlaceOrder;
import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponsePlaceOrder extends  DataObservor{




    ModelPlaceOrder response;

    public ModelPlaceOrder getResponse() {
        return response;
    }
    public void getResturants(Context context) {


        String url = context.getResources().getString(R.string.url)
                + "getrests.php";

        Log.d("URL String:", url);
        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
        GsonRequest<ModelPlaceOrder> request = new GsonRequest<ModelPlaceOrder>(
                Request.Method.GET, url, ModelPlaceOrder.class, null,
                successListener(), errorListener());
        requestQueue.add(request);
    }


    private Response.Listener<ModelPlaceOrder> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelPlaceOrder>() {

            @Override
            public void onResponse(ModelPlaceOrder response) {
                // TODO Auto-generated method stub
                try{
                    ResponsePlaceOrder.this.response=response;
                }catch(Exception e)
                {
                    ResponsePlaceOrder.this.response=new ModelPlaceOrder();

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
                ResponsePlaceOrder.this.response= new ModelPlaceOrder();

                triggerObservers();

            }
        };
    }


}
