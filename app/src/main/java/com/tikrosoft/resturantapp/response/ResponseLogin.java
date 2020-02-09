package com.tikrosoft.resturantapp.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tikrosoft.resturantapp.ApplicationClass;
import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.pojo.login.ModelLogin;
import com.tikrosoft.resturantapp.utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

public class ResponseLogin extends DataObservor {


    ModelLogin response;

    public ModelLogin getResponse(){
        return response;
    }
    public void login(Context context, String uemail, String upassword,String utype){





        Map<String, String> params = new HashMap<String, String>();

        params.put("uemail", uemail);

        params.put("upassword", upassword);
        params.put("utype", utype);


        RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();

        GsonRequest<ModelLogin> request = new GsonRequest<ModelLogin>(
                com.android.volley.Request.Method.POST, context.getResources()
                .getString(R.string.url) + "login.php",
                ModelLogin.class, params, successListener(),
                errorListener());
        requestQueue.add(request);

    }
    private Response.Listener<ModelLogin> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<ModelLogin>() {

            @Override
            public void onResponse(ModelLogin response) {
                // TODO Auto-generated method stub
                try{
                    ResponseLogin.this.response=response;
                }catch(Exception e)
                {
                    ResponseLogin.this.response=new ModelLogin();
                    ResponseLogin.this.response.setSuccess(true);
                    Log.d("SignupResponse", ResponseLogin.this.getResponse().toString());
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
                ResponseLogin.this.response= new ModelLogin();
                ResponseLogin.this.response.setSuccess(false);
                ResponseLogin.this.response.setMsg(error.getMessage());

                triggerObservers();

            }
        };
    }





}
