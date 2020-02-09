package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.R;
import com.tikrosoft.resturantapp.adapters.AdapterRest;
import com.tikrosoft.resturantapp.pojo.resturants.ModelResturants;
import com.tikrosoft.resturantapp.pojo.resturants.Rest;
import com.tikrosoft.resturantapp.pojo.signup.SignupModel;
import com.tikrosoft.resturantapp.response.ResponseResturants;
import com.tikrosoft.resturantapp.response.ResponseSignup;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ActivityResturantList extends AppCompatActivity implements View.OnClickListener, Observer {

    RecyclerView recyclerRest;


    ResponseResturants response;
    ModelResturants modle;

    ConnectionDetector cd;
    ProgressDialog pd;
    AdapterRest adapterRest;
    private List<Rest> restlist = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturants);
        init();
    }

    public void init(){
        recyclerRest = findViewById(R.id.recyclerRest);
        response=new ResponseResturants();
        response.addObserver(this);
        modle=new ModelResturants();
        cd = new ConnectionDetector(this);
        getResturants();
    }
    public void getResturants(){
        if (!cd.isConnectionToInternet()) {
            Toast.makeText(getApplicationContext(), "Connect to Internet",
                    Toast.LENGTH_SHORT).show();
        } else {
            pd= ProgressDialog.show(this, "wait", "Processing data");
            response.getResturants(this);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void update(Observable observable, Object o) {

        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (null!= response.getResponse().getRest()) {

               List<Rest> restList = response.getResponse().getRest();
               if(restList!=null){

                   adapterRest = new AdapterRest(this, restList);
                   recyclerRest.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

                   recyclerRest.setAdapter(adapterRest);
               }


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
