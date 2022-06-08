package com.example.gelelim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gelelim.Permanent.LoginPermanent;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class DriverActivity extends AppCompatActivity {
    DrawerLayout cekmece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        cekmece=findViewById(R.id.arkaplan);
        Task<Uri> t = FirebaseStorage.getInstance().getReference().child("myImages/" + LoginPermanent.driver.getId()).getDownloadUrl();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (t.isComplete()) {

                String stR = t.getResult().getPath();
                String st1R = t.getResult().toString();

                if (t.isSuccessful()) {
                    ImageView v=findViewById(R.id.ayisus);
                    Picasso.get().load(t.getResult()).into(v);

                }
                break;
            }
        }
        TextView v=findViewById(R.id.UserNameView);
        TextView v1=findViewById(R.id.UserEmailView);
        v.setText(LoginPermanent.driver.getName());
        v1.setText(LoginPermanent.driver.getMail());
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
        startActivity(new Intent(DriverActivity.this, ProfieDriverActivity.class));


    }
    public void advertisementmenu(View view){

        startActivity(new Intent(DriverActivity.this, AdvertisementActivity.class));


    }
    public void advertisemenlisttmenu(View view){


        startActivity(new Intent(DriverActivity.this, AdslistActivity.class));


    }


    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(DriverActivity.this);
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