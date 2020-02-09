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

import com.tikrosoft.resturantapp.pojo.Cart;
import com.tikrosoft.resturantapp.pojo.IntroModel;
import com.tikrosoft.resturantapp.response.ResponseIntro;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener, Observer {

    public static List<Cart> cartList;

    TextView tvHIntor;
    Button btnShowCart;
    Button btnBrwHist;
    Button btnBrwRest;

    ResponseIntro response;
    IntroModel modle;

    ConnectionDetector cd;
    ProgressDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        if (!cd.isConnectionToInternet()) {
            Toast.makeText(getApplicationContext(), "Connect to Internet",
                    Toast.LENGTH_SHORT).show();
        } else {
            pd= ProgressDialog.show(this, "wait", "Processing data");
            response.getIntro(getApplicationContext());

        }
    }
    public void init(){
        tvHIntor = findViewById(R.id.tvHIntor);
        btnShowCart = findViewById(R.id.btnShowCartHome);
        btnBrwHist = findViewById(R.id.btnBrwHist);
        btnBrwRest = findViewById(R.id.btnBrwRest);

        btnShowCart.setOnClickListener(this);
        btnBrwHist.setOnClickListener(this);
        btnBrwRest.setOnClickListener(this);


        response=new ResponseIntro();
        response.addObserver(this);
        modle=new IntroModel();
        cd = new ConnectionDetector(this);
        if(cartList==null)
        cartList = new ArrayList<Cart>();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnBrwRest){
            Intent browseRest = new Intent(ActivityHome.this,ActivityResturantList.class);
            startActivity(browseRest);
        }
        if(view.getId() == R.id.btnShowCartHome){

                Intent showCart = new Intent(this,ActivityShowCart.class);
                startActivity(showCart);

        }

    }

    @Override
    public void update(Observable observable, Object o) {


        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (response.getResponse().getSuccess()) {
                tvHIntor.setText(response.getResponse().getMsg().getIntro());


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }




    }
}
