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

public class ResponseReviewUpdate extends  DataObservor {
    ModelUpdateReview response;

    public ModelUpdateReview getResponse(){
        return response;
    }
    public void updatereview(Context context, String orderid, String review){

        Map<String, String> params = new HashMap<String, String>();
        params.put("orderid", orderid);
        params.put("review", review);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelUpdateReview> request = new GsonRequest<ModelUpdateReview>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "updatereview.php",
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
                    ResponseReviewUpdate.this.response=response;
                }catch(Exception e)
                {
                    ResponseReviewUpdate.this.response=new ModelUpdateReview();
                    ResponseReviewUpdate.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseReviewUpdate.this.getResponse().toString());
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
                ResponseReviewUpdate.this.response= new ModelUpdateReview();
                ResponseReviewUpdate.this.response.setSuccess(false);
                ResponseReviewUpdate.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }


}
