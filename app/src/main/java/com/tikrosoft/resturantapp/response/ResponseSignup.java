package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.IntroModel;
import com.tikrosoft.resturantapp.pojo.signup.SignupModel;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseSignup extends DataObservor{



    SignupModel response;

    public SignupModel getResponse(){
        return response;
    }
    public void signup(Context context,String uname, String uemail, String uphone, String upassword, String utype){





        Map<String, String> params = new HashMap<String, String>();
        params.put("uname", uname);
        params.put("uemail", uemail);
        params.put("uphone", uphone);
        params.put("upassword", upassword);
        params.put("utype", utype);

        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<SignupModel> request = new GsonRequest<SignupModel>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "registeruser.php",
                SignupModel.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<SignupModel> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<SignupModel>() {

            @Override
            public void onResponse(SignupModel response) {
                // TODO Auto-generated method stub
                try{
                    ResponseSignup.this.response=response;
                }catch(Exception e)
                {
                    ResponseSignup.this.response=new SignupModel();
                    ResponseSignup.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseSignup.this.getResponse().toString());
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
                ResponseSignup.this.response= new SignupModel();
                ResponseSignup.this.response.setSuccess(false);
                ResponseSignup.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }





}
