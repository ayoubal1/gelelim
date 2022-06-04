package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminLoginRegisterActivity extends AppCompatActivity {
    private TextView CreateAdminAccount;
    private TextView TitleAdmin;
    private Button LoginAdminButton;
    private Button RegisterAdminButton;
    private EditText AdminEmail;
    private EditText AdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_register);
        CreateAdminAccount = (TextView) findViewById(R.id.admin_register_link);
        TitleAdmin = (TextView) findViewById(R.id.Admin_status);
        LoginAdminButton = (Button) findViewById(R.id.admin_login_btn);
        RegisterAdminButton = (Button) findViewById(R.id.admin_register_btn);
        AdminEmail = (EditText) findViewById(R.id.admin_email);
        AdminPassword = (EditText) findViewById(R.id.admin_password);





        LoginAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminLoginRegisterActivity.this,AdminActivty.class);
                startActivity(intent);

            }
        });
    }
}