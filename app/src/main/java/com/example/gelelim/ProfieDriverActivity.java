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
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;
import com.example.gelelim.Permanent.LoginPermanent;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfieDriverActivity extends AppCompatActivity {
    DrawerLayout cekmece;
    private ImageView imgView;
    public Uri imageUri;
    private StorageReference mstorageReference;
    private FirebaseStorage storage;

    private static final int REQUEST_LOAD_GALLERY = 1000;
    EditText txName, txPhone, txPassword, txMail, txAdress;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profie_driver);
        cekmece = findViewById(R.id.arkaplan);
        imgView = findViewById(R.id.profile_image);
        txName = findViewById(R.id.input_fullName);
        txPhone = findViewById(R.id.inputMobile);
        txPassword = findViewById(R.id.parol);
        txMail = findViewById(R.id.eopsta);
        txAdress = findViewById(R.id.inputAdress);
        driver = LoginPermanent.driver;
        txName.setText(driver.getName());
        txPhone.setText(driver.getPhone());
        txPassword.setText(driver.getPassword());
        txMail.setText(driver.getMail());
        txAdress.setText(driver.getAdress());
        storage = FirebaseStorage.getInstance();
        mstorageReference = storage.getReference();
        Task<Uri> t = FirebaseStorage.getInstance().getReference().child("myImages/" + driver.getId()).getDownloadUrl();
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
                    Picasso.get().load(t.getResult()).into(imgView);

                }
                break;
            }
        }

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadGalery();
            }
        });
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
        startActivity(new Intent(ProfieDriverActivity.this, DriverActivity.class));


    }

    public void Profiemenu(View view) {
        recreate();


    }

    public void advertisementmenu(View view) {

        startActivity(new Intent(ProfieDriverActivity.this, AdvertisementActivity.class));


    }

    public void advertisemenlisttmenu(View view) {


        startActivity(new Intent(ProfieDriverActivity.this, AdslistActivity.class));


    }


    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(ProfieDriverActivity.this);
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

    private final int galerycode = 1000;

    private void loadGalery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, galerycode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == galerycode) {

            imageUri = data.getData();
            imgView.setImageURI(imageUri);


        }
    }


    public void updatebtn(View view) {
        driver.setName(txName.getText().toString());
        driver.setPhone(txPhone.getText().toString());
        driver.setPassword(txPassword.getText().toString());
        driver.setMail(txMail.getText().toString());
        driver.setAdress(txAdress.getText().toString());

        StorageReference ref = FirebaseStorage.getInstance().getReference().child("myImages/" + driver.getId());

        // false
        UploadTask t = ref.putFile(imageUri);

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (t.isComplete()) {

                String ss = t.getResult().getUploadSessionUri().toString();

                driver.setImage(ss);
                break;
            }
        }
        FirebaseService.UpdateData(driver);
    }
}