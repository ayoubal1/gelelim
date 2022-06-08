package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gelelim.Database.Customer;
import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;
import com.example.gelelim.FireCloud.Sample.Category;
import com.example.gelelim.Permanent.LoginPermanent;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class Driverlogin extends AppCompatActivity {
    private TextView CreateDriverAccount;
    private TextView TitleDriver;
    private Button LoginDriverButton;

    private EditText DriverEmail;
    private EditText DriverPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogin);
        CreateDriverAccount = (TextView) findViewById(R.id.driver_123);
        LoginDriverButton = (Button) findViewById(R.id.login_driver_btn);
        DriverEmail = (EditText) findViewById(R.id.driver_email);
        DriverPassword = (EditText) findViewById(R.id.driver_password);
        CreateDriverAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent CustomerIntent = new Intent(Driverlogin.this, DriverLoginRegisterActivity.class);
                    startActivity(CustomerIntent);
            }
        });
        LoginDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailadress=DriverEmail.getText().toString();
                String password=DriverPassword.getText().toString();
                Query q =
                        FirebaseService.QueryCreate(Driver.class).whereEqualTo("mail", mailadress);
                ArrayList<Driver> cs = FirebaseService.Get(Driver.class, q.whereEqualTo("password",password));
                if (cs.size() > 0) {
                    LoginPermanent.type=1;
                    LoginPermanent.driver=cs.get(0);
                    Intent intent = new Intent(Driverlogin.this, DriverActivity.class);

                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(Driverlogin.this, "Wrong!", Toast.LENGTH_SHORT).show();


                }





        });







    }
}