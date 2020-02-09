package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tikrosoft.resturantapp.adapters.AdapterMenu;
import com.tikrosoft.resturantapp.pojo.item.Item;
import com.tikrosoft.resturantapp.pojo.item.ModelItems;
import com.tikrosoft.resturantapp.response.ResponseItemsById;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ActivityItemsList extends AppCompatActivity implements View.OnClickListener, Observer {


    RecyclerView recyclerItems;


    ResponseItemsById response;
    ModelItems modle;

    ConnectionDetector cd;
    ProgressDialog pd;
    AdapterMenu adapterMenu;
    String restid;
    private List<Item> itmesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Intent intent = getIntent();
        restid = intent.getStringExtra("restid");
        init();

    }

    public void init(){
        recyclerItems = findViewById(R.id.recyclerItems);
        response=new ResponseItemsById();
        response.addObserver(this);
        modle=new ModelItems();
        cd = new ConnectionDetector(this);
        getItemsById();
    }
    public void getItemsById(){
        if (!cd.isConnectionToInternet()) {
            Toast.makeText(getApplicationContext(), "Connect to Internet",
                    Toast.LENGTH_SHORT).show();
        } else {
            pd= ProgressDialog.show(this, "wait", "Processing data");
            response.getItmesById(this,restid);

        }
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

    }

    @Override
    public void update(Observable observable, Object o) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (null!= response.getResponse().getItems()) {

                List<Item> itemsList = response.getResponse().getItems();
                if(itemsList!=null){

                    adapterMenu = new AdapterMenu(this, itemsList);
                    recyclerItems.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

                    recyclerItems.setAdapter(adapterMenu);
                }


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
