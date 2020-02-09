package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelAddRest;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseAddRest extends DataObservor{

    ModelAddRest response;

    public ModelAddRest getResponse(){
        return response;
    }
    public void addrest(Context context, String rest_name, String rest_opening_time, String rest_closing_time){





        Map<String, String> params = new HashMap<String, String>();
        params.put("rest_name", rest_name);
        params.put("rest_opening_time", rest_opening_time);
        params.put("rest_closing_time", rest_closing_time);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelAddRest> request = new GsonRequest<ModelAddRest>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "addrest.php",
                ModelAddRest.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<ModelAddRest> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelAddRest>() {

            @Override
            public void onResponse(ModelAddRest response) {
                // TODO Auto-generated method stub
                try{
                    ResponseAddRest.this.response=response;
                }catch(Exception e)
                {
                    ResponseAddRest.this.response=new ModelAddRest();
                    ResponseAddRest.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseAddRest.this.getResponse().toString());
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
                ResponseAddRest.this.response= new ModelAddRest();
                ResponseAddRest.this.response.setSuccess(false);
                ResponseAddRest.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }


}
