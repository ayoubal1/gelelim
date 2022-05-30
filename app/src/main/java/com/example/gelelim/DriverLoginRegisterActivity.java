package com.example.gelelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private TextView CreateDriverAccount;
    private TextView TitleDriver;
    private Button LoginDriverButton;
    private Button RegisterDriverButton;
    private EditText DriverEmail;
    private EditText DriverPassword;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference DriverDatabaseRef;
    private String onlineDriverID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

        CreateDriverAccount = (TextView) findViewById(R.id.create_driver_account);
        TitleDriver = (TextView) findViewById(R.id.titlr_driver);
        LoginDriverButton = (Button) findViewById(R.id.login_driver_btn);
        RegisterDriverButton = (Button) findViewById(R.id.register_driver_btn);
        DriverEmail = (EditText) findViewById(R.id.driver_email);
        DriverPassword = (EditText) findViewById(R.id.driver_password);
        loadingBar = new ProgressDialog(this);



        RegisterDriverButton.setVisibility(View.INVISIBLE);
        RegisterDriverButton.setEnabled(false);

        CreateDriverAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateDriverAccount.setVisibility(View.INVISIBLE);
                LoginDriverButton.setVisibility(View.INVISIBLE);
                TitleDriver.setText("Driver Registration");

                RegisterDriverButton.setVisibility(View.VISIBLE);
                RegisterDriverButton.setEnabled(true);
            }
        });


        RegisterDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = DriverEmail.getText().toString();
                String password = DriverPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Lütfen e-postanızı yazın...", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Lütfen Parolanızı Yazınız...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loadingBar.setTitle("Lütfen bekle:");
                    loadingBar.setMessage("Sistem verileriniz üzerinde işlem gerçekleştirirken...");
                    loadingBar.show();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {

                                onlineDriverID=mAuth.getCurrentUser().getUid();
                                DriverDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(onlineDriverID);
                                DriverDatabaseRef.setValue(true);
                                Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriverMapActivity.class);
                                startActivity(driverIntent);
                                Toast.makeText(DriverLoginRegisterActivity.this, "Sürücü Başarıyla Kaydoldu...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }
                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Lütfen tekrar deneyin. Kayıt olurken hata oluştu... ", Toast.LENGTH_SHORT).show();

                                loadingBar.dismiss();
                            }
                        }
                    });
                }
            }
        });

        LoginDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = DriverEmail.getText().toString();
                String password = DriverPassword.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Lütfen e-postanızı yazın...", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Lütfen Parolanızı Yazınız...", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    loadingBar.setTitle("Lütfen bekle :");
                    loadingBar.setMessage("Sistem verileriniz üzerinde işlem gerçekleştirirken...");
                    loadingBar.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(DriverLoginRegisterActivity.this, DriverMapActivity.class);
                                startActivity(intent);
                                Toast.makeText(DriverLoginRegisterActivity.this, "Giriş Yap, Başarılı...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Oturum Açılırken Hata Oluştu... ", Toast.LENGTH_SHORT).show();

                                loadingBar.dismiss();
                            }

                        }
                    });
                }
            }
        });

    }
}