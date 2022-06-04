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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class ProfieDriverActivity extends AppCompatActivity {
    DrawerLayout cekmece;
    private ImageView imgView;
    private String imgPath;
    private static final int REQUEST_LOAD_GALLERY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profie_driver);
        cekmece=findViewById(R.id.arkaplan);
        imgView = findViewById(R.id.profile_image);


        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGalery();
            }
        });
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
        startActivity(new Intent(ProfieDriverActivity.this, DriverActivity.class));


    }
    public void Profiemenu(View view){
        recreate();



    }
    public void advertisementmenu(View view){

        startActivity(new Intent(ProfieDriverActivity.this, AdvertisementActivity.class));


    }
    public void advertisemenlisttmenu(View view){


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
    private void loadGalery() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_LOAD_GALLERY);
                loadGalery();
            } else {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_LOAD_GALLERY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOAD_GALLERY && resultCode == RESULT_OK) {
            Uri imageSelected = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageSelected, filePath, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePath[0]);
            String imagePath = cursor.getString(columnIndex);
            cursor.close();

            imgPath = imagePath;
            Log.i("MainActivity", "Image Path " + imgPath);
            File f = new File(imagePath);

            String extension = getFileExtension(f);

            if (extension.toLowerCase().equals("jpg") || extension.toLowerCase().equals("jpeg") || extension.toLowerCase().equals("png")) {
                //check extension file

                //compress size file
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 8;

                Bitmap bitmap = BitmapFactory.decodeFile(imgPath, bmOptions);

                imgView.setImageBitmap(bitmap);
            }


        }
    }

    private String getFileExtension(File f) {
        String name = f.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }


    }
}