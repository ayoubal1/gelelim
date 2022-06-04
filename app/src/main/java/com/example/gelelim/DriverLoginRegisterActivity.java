package com.example.gelelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private TextView CreateDriverAccount;
    private TextView TitleDriver ,Drivername,Drivertel;
    private Button LoginDriverButton;
    private Button RegisterDriverButton;
    private EditText DriverEmail;
    private EditText DriverPassword;
    private Spinner SpiTip,SpiAdres;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);
        db = FirebaseFirestore.getInstance();

        TitleDriver = (TextView) findViewById(R.id.driver_status);
        Drivername = (TextView) findViewById(R.id.driver_name);
        Drivertel = (TextView) findViewById(R.id.driver_tel);
        LoginDriverButton = (Button) findViewById(R.id.login_driver_btn);
        RegisterDriverButton = (Button) findViewById(R.id.register_driver_btn);
        DriverEmail = (EditText) findViewById(R.id.driver_email);
        DriverPassword = (EditText) findViewById(R.id.driver_password);
        SpiAdres = (Spinner) findViewById(R.id.spiaddress);
        SpiTip= (Spinner)findViewById(R.id.spitip);

        ArrayList<String> adders = new ArrayList<String>();
        adders.add("Adderss");
        adders.add("ankara");
        adders.add("kastamoun");
        adders.add("istanbul");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpiAdres.setAdapter(adapter);

        ArrayList<String> tip = new ArrayList<String>();
        tip.add(" Arabanı Tip");
        tip.add("Sedan");
        tip.add("Pick Up");
        tip.add("Kamyonet");
        tip.add("Minivan");
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tip);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpiTip.setAdapter(adapters);





        RegisterDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailadress=DriverEmail.getText().toString();
                String password=DriverPassword.getText().toString();
                String name=Drivername.getText().toString();
                String mobiilephone=Drivertel.getText().toString();
                String adderss=adders.get(Spinner.ACCESSIBILITY_LIVE_REGION_ASSERTIVE) ;
                String cartype=tip.get(Spinner.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);

                // Create a new user with a first, middle, and last name
                Map<String, Object> user = new HashMap<>();
                user.put("mail", mailadress);
                user.put("password", password);
                user.put("name", name);
                user.put("mobiilephone", mobiilephone);
                user.put("adderss", adderss);
                user.put("cartype", cartype);
                user.put("type", "0");


// Add a new document with a generated ID
                db.collection("User")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver  Added", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver  Add to Failed", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }
}