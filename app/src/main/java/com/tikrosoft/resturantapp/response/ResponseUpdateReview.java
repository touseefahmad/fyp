package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.ModelUpdateReview;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseUpdateReview extends  DataObservor{
    ModelUpdateReview response;

    public ModelUpdateReview getResponse(){
        return response;
    }
    public void updateReview(Context context, String orderid, String review){

        Map<String, String> params = new HashMap<String, String>();
        params.put("orderid", orderid);
        params.put("review", review);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelUpdateReview> request = new GsonRequest<ModelUpdateReview>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "updatereview.php?",
                ModelUpdateReview.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<ModelUpdateReview> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelUpdateReview>() {

            @Override
            public void onResponse(ModelUpdateReview response) {
                // TODO Auto-generated method stub
                try{
                    ResponseUpdateReview.this.response=response;
                }catch(Exception e)
                {
                    ResponseUpdateReview.this.response=new ModelUpdateReview();
                    ResponseUpdateReview.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseUpdateReview.this.getResponse().toString());
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
                ResponseUpdateReview.this.response= new ModelUpdateReview();
                ResponseUpdateReview.this.response.setSuccess(false);
                ResponseUpdateReview.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }




}
