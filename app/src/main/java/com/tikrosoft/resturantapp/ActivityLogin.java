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

import com.tikrosoft.resturantapp.pojo.login.ModelLogin;
import com.tikrosoft.resturantapp.response.ResponseLogin;
import com.tikrosoft.resturantapp.utility.ConnectionDetector;
import com.tikrosoft.resturantapp.utility.ConstantKeys;
import com.tikrosoft.resturantapp.utility.UtilityClass;

import java.util.Observable;
import java.util.Observer;

import static com.tikrosoft.resturantapp.utility.ConstantKeys.PREF_NAME;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, Observer {

    EditText etEmail;
    EditText etPassword;
    Button adminLogin;
    Button btnLogin;
    Button btnGuest;
    Button btnSignup;

    SharedPreferences shared;


    ResponseLogin response;
    ModelLogin modle;

    ConnectionDetector cd;
    ProgressDialog pd;

    SharedPreferences sharedPref;

    String utype;
    public  static  String USER_STATUS = "0" ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        init();

    }

    public void init(){
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        adminLogin = findViewById(R.id.adminLogin);
        adminLogin.setOnClickListener(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnGuest = findViewById(R.id.btnGuest);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin.setOnClickListener(this);
        btnGuest.setOnClickListener(this);
        btnSignup.setOnClickListener(this);


        response=new ResponseLogin();
        response.addObserver(this);
        modle=new ModelLogin();
        cd = new ConnectionDetector(this);

        utype = "0";
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

        if(view.getId() == R.id.btnGuest){
            Intent home = new Intent(ActivityLogin.this,ActivityHome.class);
            shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(ConstantKeys.KEYID,"guest");
            editor.commit();
            // SharedPreferences.Editor editor = sharedPref.edit();

            editor.commit();

            editor.commit();
            startActivity(home);
        }
        if(view.getId() == R.id.btnSignup){
            Intent signup = new Intent(ActivityLogin.this,ActivitySignup.class);
            startActivity(signup);
        }
        if(view.getId() == R.id.btnLogin){
            utype = "0";
            USER_STATUS="0";
            validateFields();
        }
        if(view.getId() == R.id.adminLogin){
            utype = "1";
            USER_STATUS = "1";
            validateFields();
        }

    }

    @Override
    public void update(Observable observable, Object o) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        if(observable==response){

            if (response.getResponse().getSuccess()) {


                shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString(ConstantKeys.KEYID,response.getResponse().getUser().getUid());
                editor.commit();
               // SharedPreferences.Editor editor = sharedPref.edit();

                editor.commit();
                Toast.makeText(getApplicationContext(),response.getResponse().getUser().getUid()+"",Toast.LENGTH_SHORT).show();

                if(utype.compareTo("0")==0) {
                    Intent Home = new Intent(ActivityLogin.this, ActivityHome.class);
                    startActivity(Home);
                }else{
                    Intent Home = new Intent(ActivityLogin.this, ActivityAdmin.class);
                    startActivity(Home);
                }


            }else
            {
                Toast.makeText(getApplicationContext(), "Unable to load data", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void validateFields(){
        if( etEmail.getText().toString().compareTo("")==0
                || etPassword.getText().toString().compareTo("")==0){
            Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
        }
        else if(UtilityClass.isValidEmail(etEmail.getText().toString())){

            if (!cd.isConnectionToInternet()) {
                Toast.makeText(getApplicationContext(), "Connect to Internet",
                        Toast.LENGTH_SHORT).show();
            } else {
                pd= ProgressDialog.show(this, "wait", "Processing data");
                String uemail= etEmail.getText().toString();
                String upasswrod= etPassword.getText().toString();

                response.login(this,uemail,upasswrod,utype);

            }
        }else{
            Toast.makeText(getApplicationContext(),"Email is not valid", Toast.LENGTH_SHORT).show();
        }
    }
}
