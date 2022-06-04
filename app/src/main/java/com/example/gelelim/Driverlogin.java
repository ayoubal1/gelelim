package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gelelim.FireCloud.FirebaseService;
import com.example.gelelim.FireCloud.Sample.Category;

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
               /* String mailadress=DriverEmail.getText().toString();
                String password=DriverEmail.getText().toString();


                if(mailadress.isEmpty()&&password.isEmpty()){
                    Toast.makeText(Driverlogin.this, "Lütfan Boşlok Bırakmayın", Toast.LENGTH_SHORT).show();
                }
                else {
                    Category category= FirebaseService.SearchCustom(new Category(),FirebaseService.QueryCustom
                            (new Category()).  whereEqualTo("mail", mailadress).whereEqualTo("password", password)    ).get(0);

                    if (category!=null)
                        Toast.makeText(Driverlogin.this, "Driver  Added", Toast.LENGTH_SHORT).show();*/
                    Intent intent = new Intent(Driverlogin.this, DriverActivity.class);
                    startActivity(intent);
                    finish();


                }





        });







    }
}