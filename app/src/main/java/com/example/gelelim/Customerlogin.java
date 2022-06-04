package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Customerlogin extends AppCompatActivity {
    private TextView CreateCustomerAccount;
    private TextView TitleCustomer;
    private Button LoginCustomerButton;
    private Button RegisterCustomerButton;
    private EditText CustomerEmail;
    private EditText CustomerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);
        CreateCustomerAccount = (TextView) findViewById(R.id.customer_register_link);
        TitleCustomer = (TextView) findViewById(R.id.customer_status);
        LoginCustomerButton = (Button) findViewById(R.id.customer_login_btn);

        CustomerEmail = (EditText) findViewById(R.id.customer_email);
        CustomerPassword = (EditText) findViewById(R.id.customer_password);

        CreateCustomerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Customerlogin.this,CustomerLoginRegisterActivity.class);
                startActivity(intent);
            }
        });

        LoginCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Customerlogin.this,CustomerActivity.class);
                startActivity(intent);
            }
        });
    }



}