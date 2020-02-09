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
import com.tikrosoft.resturantapp.utility.GsonRequest;

public class ResponseIntro extends DataObservor{
    IntroModel response;

    public IntroModel getResponse(){
        return response;
    }
    public void getIntro(Context context){



		 String url=context.getResources().getString(R.string.url)
					+"getintro.php";

		 Log.d("URL String:", url);
		 RequestQueue requestQueue = ApplicationClass.getInstance().getRequestQueue();
		 GsonRequest<IntroModel> request = new GsonRequest<IntroModel>(
					Request.Method.GET, url, IntroModel.class, null,
					successListener(), errorListener());
		 requestQueue.add(request);

        /*requestQueue.add(request);*/

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("email", email);
//        params.put("password", password);
//        params.put("token", token);
//        RequestQueue requestQueue = AssayTech.getInstance().getRequestQueue();
//
//        GsonRequest<AtLoginResponse> request = new GsonRequest<AtLoginResponse>(
//                com.android.volley.Request.Method.POST, context.getResources()
//                .getString(R.string.url) + "signIn",
//                AtLoginResponse.class, params, successListener(),
//                errorListener());
//        requestQueue.add(request);

    }
    private Response.Listener<IntroModel> successListener() {
        // TODO Auto-generated method stub
        return new Response.Listener<IntroModel>() {

            @Override
            public void onResponse(IntroModel response) {
                // TODO Auto-generated method stub
                try{
                    ResponseIntro.this.response=response;
                }catch(Exception e)
                {
                    ResponseIntro.this.response=new IntroModel();
                    ResponseIntro.this.response.setSuccess(true);
                    Log.d("IntroResponse", ResponseIntro.this.getResponse().toString());
//                    ResponseIntro.this.response.setMsg(e.getMessage());
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
                ResponseIntro.this.response= new IntroModel();
                ResponseIntro.this.response.setSuccess(false);
                Log.d("IntroResponse", ResponseIntro.this.getResponse().toString());

                triggerObservers();

            }
        };
    }




}
