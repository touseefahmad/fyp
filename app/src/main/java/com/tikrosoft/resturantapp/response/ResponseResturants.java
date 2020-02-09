package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.resturants.ModelResturants;

import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponseResturants extends  DataObservor{


    ModelResturants response;

    public ModelResturants getResponse() {
        return response;
    }
    public void getResturants(Context context) {


        String url = context.getResources().getString(R.string.url)
                + "getrests.php";

        Log.d("URL String:", url);
        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
        GsonRequest<ModelResturants> request = new GsonRequest<ModelResturants>(
                Request.Method.GET, url, ModelResturants.class, null,
                successListener(), errorListener());
        requestQueue.add(request);
    }


    private Response.Listener<ModelResturants> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelResturants>() {

            @Override
            public void onResponse(ModelResturants response) {
                // TODO Auto-generated method stub
                try{
                    ResponseResturants.this.response=response;
                }catch(Exception e)
                {
                    ResponseResturants.this.response=new ModelResturants();

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
                ResponseResturants.this.response= new ModelResturants();

                triggerObservers();

            }
        };
    }
}
