package com.example.gelelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gelelim.Database.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.UserData;

public class CustomerActivity extends AppCompatActivity {
    DrawerLayout cekmece;
    private TextView getUserName;
    private TextView getUserEmail;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        cekmece=findViewById(R.id.arkaplan);
        getUserEmail = findViewById(R.id.UserEmailView);
        getUserName = findViewById(R.id.UserNameView);
        db = FirebaseFirestore.getInstance();

        getUserEmail.setText("mailadress");



    }
    public void Menutiklama(View view){
        cekmeceyAc(cekmece);

    }

    private void cekmeceyAc(DrawerLayout cekmece) {
        cekmece.openDrawer(GravityCompat.START);
    }
    public void logoyatiklama(View view){
        cekmeceyKapat(cekmece);

    }

    private void cekmeceyKapat(DrawerLayout cekmece) {
        if (cekmece.isDrawerOpen (GravityCompat.START)){
            cekmece.closeDrawer (GravityCompat.START);
    }
    }
    public void Anamenu(View view){
        recreate();

    }
    public void Profiemenu(View view){
        startActivity(new Intent(CustomerActivity.this, ProfieActivity.class));


    }
    public void Arabaaramamenu(View view){
        startActivity(new Intent(CustomerActivity.this, ArabaAramaActivity.class));


    }
    public void Arabalarlistesi(View view){
        startActivity(new Intent(CustomerActivity.this, ArabalarlistesiActivity.class));


    }
    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(CustomerActivity.this);
        uyariPenceresi.setTitle("Çıkış");
        uyariPenceresi.setMessage("Çıkış Yaplsan mı?");
        uyariPenceresi.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });
        uyariPenceresi.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        uyariPenceresi.show();
    }

    @Override
    protected void onPause() {
        cekmeceyKapat(cekmece);
        super.onPause();
    }


}
