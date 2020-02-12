package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tikrosoft.resturantapp.pojo.ModelAddRest;
import com.tikrosoft.resturantapp.response.ResponseAddRest;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.Observable;
import java.util.Observer;

public class ActivityAdmin extends AppCompatActivity implements View.OnClickListener, Observer {

    TextView tvadminrest;
    TextView tvadminOpeningTime;
    TextView tvadminClosingTime;
    Button btnAddRest;

    ResponseAddRest response;
    ModelAddRest modle;

    ConnectionDetector cd;
    ProgressDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }
    public void init(){
        tvadminrest = findViewById(R.id.tvadminrest);
        tvadminOpeningTime= findViewById(R.id.tvadminOpeningTime);
        tvadminClosingTime = findViewById(R.id.tvadminClosingTime);
        btnAddRest = findViewById(R.id.btnAddRest);
        btnAddRest.setOnClickListener(this);


        response=new ResponseAddRest();
        response.addObserver(this);
        modle=new ModelAddRest();
        cd = new ConnectionDetector(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnAddRest){

            String rest = tvadminrest.getText().toString();
            String opening = tvadminOpeningTime.getText().toString();
            String closign = tvadminClosingTime.getText().toString();

            if(rest.compareTo("")==0 || opening.compareTo("")==0 || closign.compareTo("")==0){
                Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
            }else{

                if (!cd.isConnectionToInternet()) {
                    Toast.makeText(getApplicationContext(), "Connect to Internet",
                            Toast.LENGTH_SHORT).show();
                } else {
                    pd= ProgressDialog.show(this, "wait", "Processing data");


                    response.addrest(this,rest,opening,closign);

                }
            }


        }

    }

    @Override
    public void update(Observable observable, Object o) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (response.getResponse().getSuccess()) {


                Toast.makeText(getApplicationContext(),response.getResponse().getMsg()+"",Toast.LENGTH_SHORT).show();
               // Intent Home = new Intent(ActivityLogin.this,ActivityHome.class);
                //startActivity(Home);
                Intent browseRest = new Intent(ActivityAdmin.this,ActivityResturantList.class);
                startActivity(browseRest);


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
