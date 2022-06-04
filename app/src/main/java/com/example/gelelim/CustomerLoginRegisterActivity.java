package com.example.gelelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerLoginRegisterActivity extends AppCompatActivity {
    private TextView CreateCustomerAccount ,Customername ,Customertel;
    private TextView TitleCustomer;
    private Button LoginCustomerButton;
    private Button RegisterCustomerButton;
    private EditText CustomerEmail;
    private EditText CustomerPassword;
    private Spinner Spiadderscustomer;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        db = FirebaseFirestore.getInstance();
        CreateCustomerAccount = (TextView) findViewById(R.id.customer_register_link);
        Customername = (TextView) findViewById(R.id.customer_name);
        Customertel = (TextView) findViewById(R.id.customer_tel);
        TitleCustomer = (TextView) findViewById(R.id.customer_status);
        LoginCustomerButton = (Button) findViewById(R.id.customer_login_btn);
        RegisterCustomerButton = (Button) findViewById(R.id.admin_register_btn);
        CustomerEmail = (EditText) findViewById(R.id.customer_email);
        CustomerPassword = (EditText) findViewById(R.id.customer_password);
        Spiadderscustomer= (Spinner)findViewById(R.id.spiadderscustomer);

        ArrayList<String> adders = new ArrayList<String>();
        adders.add("Adderss");
        adders.add("ankara");
        adders.add("kastamoun");
        adders.add("istanbul");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spiadderscustomer.setAdapter(adapter);





        RegisterCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailadress=CustomerEmail.getText().toString();
                String password=CustomerPassword.getText().toString();
                String name=Customername.getText().toString();
                String mobiilephone=Customertel.getText().toString();
                String adderss=adders.get(Spinner.ACCESSIBILITY_LIVE_REGION_ASSERTIVE) ;
                // Create a new user with a first, middle, and last name
                Map<String, Object> user = new HashMap<>();
                user.put("mail", mailadress);
                user.put("name", name);
                user.put("mobiilephone", mobiilephone);
                user.put("adderss", adderss);
                user.put("type", "1");


// Add a new document with a generated ID
                db.collection("User")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer  Added", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer  Add to Failed", Toast.LENGTH_SHORT).show();

                            }
                        });


            }

        });




    }

}