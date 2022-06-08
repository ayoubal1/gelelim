package com.example.gelelim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ArabalarlistesiActivity extends AppCompatActivity {
    DrawerLayout cekmece;
    Context context = this;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arabalarlistesi);
        cekmece = findViewById(R.id.arkaplan);
        recyclerView = findViewById(R.id.arabalist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ArrayList<Driver> drivers = FirebaseService.Get(Driver.class,
                FirebaseService.QueryCreate(Driver.class).whereEqualTo("statu", 1));
        AriverAdapter ariverAdapter = new AriverAdapter(drivers, context);
        recyclerView.setAdapter(ariverAdapter);


    }
    public void Call(String  number){

    }

    public void SearchCity(View view) {
        Query query = FirebaseService.QueryCreate(Driver.class).whereEqualTo("statu", 1);
        String city = ( (EditText) findViewById(R.id.e_t_searchcity) ).getText().toString();
        if (city != null && city.length() != 0)
            query = query.whereEqualTo("adress", city.trim());
        ArrayList<Driver> drivers = FirebaseService.Get(Driver.class, query);
        AriverAdapter ariverAdapter = new AriverAdapter(drivers, context);
        recyclerView.setAdapter(ariverAdapter);
    }

    public void Menutiklama(View view) {
        cekmeceyAc(cekmece);

    }

    private void cekmeceyAc(DrawerLayout cekmece) {
        cekmece.openDrawer(GravityCompat.START);
    }

    public void logoyatiklama(View view) {
        cekmeceyKapat(cekmece);

    }

    private void cekmeceyKapat(DrawerLayout cekmece) {
        if (cekmece.isDrawerOpen(GravityCompat.START)) {
            cekmece.closeDrawer(GravityCompat.START);
        }
    }

    public void Anamenu(View view) {
        startActivity(new Intent(ArabalarlistesiActivity.this, CustomerActivity.class));

    }

    public void Profiemenu(View view) {
        startActivity(new Intent(ArabalarlistesiActivity.this, ProfieActivity.class));


    }

    public void Arabaaramamenu(View view) {
        startActivity(new Intent(ArabalarlistesiActivity.this, ArabaAramaActivity.class));


    }

    public void advertisemenlisttmenu(View view) {


        recreate();


    }

    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(ArabalarlistesiActivity.this);
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