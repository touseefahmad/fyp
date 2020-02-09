package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.item.ModelItems;
import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponseItemsById extends DataObservor {
    ModelItems response;

    public ModelItems getResponse() {
        return response;
    }
    public void getItmesById(Context context,String restId) {


        String url = context.getResources().getString(R.string.url)
                + "/getitemsbyid.php?rest_id="+restId;

        Log.d("URL String:", url);
        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
        GsonRequest<ModelItems> request = new GsonRequest<ModelItems>(
                Request.Method.GET, url, ModelItems.class, null,
                successListener(), errorListener());
        requestQueue.add(request);
    }


    private Response.Listener<ModelItems> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelItems>() {

            @Override
            public void onResponse(ModelItems response) {
                // TODO Auto-generated method stub
                try{
                    ResponseItemsById.this.response=response;
                }catch(Exception e)
                {
                    ResponseItemsById.this.response=new ModelItems();

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
                ResponseItemsById.this.response= new ModelItems();

                triggerObservers();

            }
        };
    }
}
