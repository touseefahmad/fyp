package com.tikrosoft.resturantapp;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApplicationClass extends Application {
    public static final String TAG="VolleyPatterns";
    private static final String PREFS_NAME = "Preference";
    public static SharedPreferences settings;
    //public static Boolean LOGIN_STATUS;
    /**
     * Global request Queue for volley
     */
    private RequestQueue mRequestQueue;
    Context context;
    SharedPreferences prefs;

    private static ApplicationClass mInstance;
    /* (non-Javadoc)
     * @see android.app.Application#onCreate()
     */

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mInstance=this;
        context=this;

    }

    public static synchronized ApplicationClass getInstance(){
        return mInstance;
    }

    /**
     *
     * @return Request queue if not null else generates one and return
     */
    public RequestQueue getRequestQueue(){
        // First time initializes the request queue
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    /**
     * if tag is specified uses this if tag is not specified set default tag
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag){
        // Set default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG:tag);
        Log.d("Volleyrequest Queue",req.getUrl());
        //add to requset queue
        getRequestQueue().add(req);

    }
    public <T> void addToRequestQueue(Request<T>req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

}
