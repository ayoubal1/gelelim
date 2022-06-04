package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button DriverWelcomeButton;
    private Button CustomerWelcomeButton;
    private Button AdminWelcomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        DriverWelcomeButton = (Button) findViewById(R.id.driver_welcome_btn);
        CustomerWelcomeButton = (Button) findViewById(R.id.customer_welcome_btn);
        AdminWelcomeButton = (Button) findViewById(R.id.admin_welcome_btn);



        DriverWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent DriverIntent = new Intent(WelcomeActivity.this, Driverlogin.class);
                startActivity(DriverIntent);
            }
        });

        CustomerWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent CustomerIntent = new Intent(WelcomeActivity.this, Customerlogin.class);
                startActivity(CustomerIntent);
            }
        });
        AdminWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent CustomerIntent = new Intent(WelcomeActivity.this, AdminLoginRegisterActivity.class);
                startActivity(CustomerIntent);
            }
        });
    }
}