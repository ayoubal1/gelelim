package com.example.gelelim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class AdslistActivity extends AppCompatActivity {
    DrawerLayout cekmece;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adslist);
        cekmece = findViewById(R.id.arkaplan);

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
        startActivity(new Intent(AdslistActivity.this, DriverActivity.class));


    }

    public void Profiemenu(View view) {
        startActivity(new Intent(AdslistActivity.this, ProfieDriverActivity.class));


    }

    public void advertisementmenu(View view) {
        startActivity(new Intent(AdslistActivity.this, AdvertisementActivity.class));


    }

    public void advertisemenlisttmenu(View view) {


        recreate();


    }


    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(AdslistActivity.this);
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