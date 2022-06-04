package com.example.gelelim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ArabalarlistesiActivity extends AppCompatActivity {
    DrawerLayout cekmece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arabalarlistesi);
        cekmece=findViewById(R.id.arkaplan);
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
        startActivity(new Intent(ArabalarlistesiActivity.this, CustomerActivity.class));

    }
    public void Profiemenu(View view){
        startActivity(new Intent(ArabalarlistesiActivity.this, ProfieActivity.class));




    }
    public void Arabaaramamenu(View view){
        startActivity(new Intent(ArabalarlistesiActivity.this, ArabaAramaActivity.class));


    }
    public void advertisemenlisttmenu(View view){


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