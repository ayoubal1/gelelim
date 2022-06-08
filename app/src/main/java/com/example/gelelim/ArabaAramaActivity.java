package com.example.gelelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArabaAramaActivity extends AppCompatActivity {
    private View view;
    DrawerLayout cekmece;

    EditText btngetDivison;
    Button btnSearch;
    RecyclerView recview;
    Searchdriveradapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba_arama);
        setTitle("");
        cekmece = findViewById(R.id.arkaplan);
        btngetDivison = findViewById(R.id.btngetDivison);
        btnSearch = findViewById(R.id.btnSearch);
        recview = findViewById(R.id.arabasearch);
        recview.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Driver> drivers= FirebaseService.Get(Driver.class,
                FirebaseService.QueryCreate(Driver.class).whereEqualTo("statu",1));


        adapter=new Searchdriveradapter(drivers);
        recview.setAdapter(adapter);


    }







}