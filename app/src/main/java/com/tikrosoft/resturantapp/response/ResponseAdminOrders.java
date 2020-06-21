package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelAllOrders;
import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponseAdminOrders extends  DataObservor {
    ModelAllOrders response;

    public ModelAllOrders getResponse() {
        return response;
    }
    public void getAdminOrders(Context context) {


        String url = context.getResources().getString(R.string.url)
                + "getallorders.php";

        Log.d("URL String:", url);
        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
        GsonRequest<ModelAllOrders> request = new GsonRequest<ModelAllOrders>(
                Request.Method.GET, url, ModelAllOrders.class, null,
                successListener(), errorListener());
        requestQueue.add(request);
    }


    private Response.Listener<ModelAllOrders> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelAllOrders>() {

            @Override
            public void onResponse(ModelAllOrders response) {
                // TODO Auto-generated method stub
                try{
                    ResponseAdminOrders.this.response=response;
                }catch(Exception e)
                {
                    ResponseAdminOrders.this.response=new ModelAllOrders();

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
                ResponseAdminOrders.this.response= new ModelAllOrders();

                triggerObservers();

            }
        };
    }

}
