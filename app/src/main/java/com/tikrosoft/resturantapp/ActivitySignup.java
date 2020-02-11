package com.tikrosoft.resturantapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tikrosoft.resturantapp.pojo.signup.SignupModel;
import com.tikrosoft.resturantapp.response.ResponseSignup;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;
import com.tikrosoft.resturantapp.utility.ConstantKeys;
import com.tikrosoft.resturantapp.utility.UtilityClass;

import java.util.Observable;
import java.util.Observer;

public class ActivitySignup extends AppCompatActivity implements View.OnClickListener, Observer {

    EditText etSname;
    EditText etsEmail;
    EditText etsPhone;
    EditText etsPassword;
    Button btnSignup_;
    Button adminSignup;

    String utype="0";




    ResponseSignup response;
    SignupModel modle;

    ConnectionDetector cd;
    ProgressDialog pd;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    public void init(){
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        adminSignup= findViewById(R.id.adminSignup);
        adminSignup.setOnClickListener(this);

        etSname = findViewById(R.id.etSname);
        etsEmail = findViewById(R.id.etsEmail);
        etsPhone = findViewById(R.id.etsPhone);
        etsPassword = findViewById(R.id.etsPassword);

        btnSignup_ = findViewById(R.id.btnSignup_);
        btnSignup_.setOnClickListener(this);





        response=new ResponseSignup();
        response.addObserver(this);
        modle=new SignupModel();
        cd = new ConnectionDetector(this);
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

        if(view.getId() == R.id.btnSignup_){
            utype = "0";
            validateFields();
        }
        if(view.getId() == R.id.adminSignup){
            utype = "1";
            validateFields();

        }

    }

    public void  validateFields(){
        if(etSname.getText().toString().compareTo("") ==0
                || etsEmail.getText().toString().compareTo("")==0
                || etsPhone.getText().toString().compareTo("") == 0
                || etsPassword.getText().toString().compareTo("")==0){
            Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
        }
        else if(UtilityClass.isValidEmail(etsEmail.getText().toString())){

            if (!cd.isConnectionToInternet()) {
                Toast.makeText(getApplicationContext(), "Connect to Internet",
                        Toast.LENGTH_SHORT).show();
            } else {
                pd= ProgressDialog.show(this, "wait", "Processing data");
             String uname= etSname.getText().toString();
                String uemail= etsEmail.getText().toString();
                String uphone= etsPhone.getText().toString();
                String upasswrod= etsPassword.getText().toString();

                response.signup(this,uname,uemail,uphone,upasswrod,utype);

            }
        }else{
            Toast.makeText(getApplicationContext(),"Email is not valid", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void update(Observable observable, Object o) {

        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (response.getResponse().getSuccess()) {

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(ConstantKeys.KEYID,response.getResponse().getUser().getUid());
                editor.putString(ConstantKeys.KEYEMAIL,response.getResponse().getUser().getUemail());
                editor.putString(ConstantKeys.KEYTYPE,response.getResponse().getUser().getUtype());
                editor.putString(ConstantKeys.KEYPHONE,response.getResponse().getUser().getUphone());
                editor.commit();

                if(utype.compareTo("1")==0){
                    Toast.makeText(getApplicationContext(),"You are logged in as an admin", Toast.LENGTH_SHORT).show();
                    Intent Home = new Intent(ActivitySignup.this,ActivityAdmin.class);
                    startActivity(Home);
                }

                Toast.makeText(getApplicationContext(),response.getResponse().getUser().getUid()+"",Toast.LENGTH_SHORT).show();
                Intent Home = new Intent(ActivitySignup.this,ActivityHome.class);
                startActivity(Home);


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }


    }

}
