package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelAddItemAdmin;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseAddItemAdmin extends DataObservor {
    ModelAddItemAdmin response;

    public ModelAddItemAdmin getResponse(){
        return response;
    }
    public void addItemByadmin(Context context, String rest_id, String menu_item_name, String menu_item_price,String menu_item_description){





        Map<String, String> params = new HashMap<String, String>();
        params.put("rest_id", rest_id);
        params.put("menu_item_name", menu_item_name);
        params.put("menu_item_price", menu_item_price);
        params.put("menu_item_description", menu_item_description);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelAddItemAdmin> request = new GsonRequest<ModelAddItemAdmin>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "additembyrestit.php",
                ModelAddItemAdmin.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<ModelAddItemAdmin> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelAddItemAdmin>() {

            @Override
            public void onResponse(ModelAddItemAdmin response) {
                // TODO Auto-generated method stub
                try{
                    ResponseAddItemAdmin.this.response=response;
                }catch(Exception e)
                {
                    ResponseAddItemAdmin.this.response=new ModelAddItemAdmin();
                    ResponseAddItemAdmin.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseAddItemAdmin.this.getResponse().toString());
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
                ResponseAddItemAdmin.this.response= new ModelAddItemAdmin();
                ResponseAddItemAdmin.this.response.setSuccess(false);
                ResponseAddItemAdmin.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }
}
