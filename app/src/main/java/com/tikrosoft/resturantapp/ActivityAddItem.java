package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tikrosoft.resturantapp.pojo.ModelAddItemAdmin;
import com.tikrosoft.resturantapp.response.ResponseAddItemAdmin;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.Observable;
import java.util.Observer;

public class ActivityAddItem extends AppCompatActivity implements View.OnClickListener, Observer {
    EditText idItemNameAdmin;
    EditText idItemPriceAdmin;
    EditText idItemDescAdmin;
    Button btnAddItemAdmin;

    ResponseAddItemAdmin response;
    ModelAddItemAdmin modle;

    ConnectionDetector cd;
    ProgressDialog pd;

    String restId="0";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Intent intent = getIntent();
        restId = intent.getStringExtra("restid");
        init();

    }

    public void init(){
        idItemNameAdmin = findViewById(R.id.idItemNameAdmin);
        idItemPriceAdmin = findViewById(R.id.idItemPriceAdmin);
        idItemDescAdmin = findViewById(R.id.idItemDescAdmin);

        btnAddItemAdmin = findViewById(R.id.btnAddItemAdmin);
        btnAddItemAdmin.setOnClickListener(this);



        response=new ResponseAddItemAdmin();
        response.addObserver(this);
        modle=new ModelAddItemAdmin();
        cd = new ConnectionDetector(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnAddItemAdmin){

            if (!cd.isConnectionToInternet()) {
                Toast.makeText(getApplicationContext(), "Connect to Internet",
                        Toast.LENGTH_SHORT).show();
            } else {
                pd= ProgressDialog.show(this, "wait", "Processing data");


                response.addItemByadmin(this,restId,idItemNameAdmin.getText().toString(),
                        idItemPriceAdmin.getText().toString(),
                        idItemDescAdmin.getText().toString());

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



            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
