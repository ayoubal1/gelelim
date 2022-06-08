package com.example.gelelim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gelelim.Database.Customer;
import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;
import com.example.gelelim.Permanent.LoginPermanent;

public class ProfieActivity extends AppCompatActivity {
    DrawerLayout cekmece;

    Customer customer;
    EditText txName, txPhone, txPassword, txMail, txAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profie);
        cekmece = findViewById(R.id.arkaplan);
        txName = findViewById(R.id.input_fullName);
        txPhone = findViewById(R.id.inputMobile);
        txPassword = findViewById(R.id.parol);
        txMail = findViewById(R.id.eopsta);
        txAdress = findViewById(R.id.inputAdress);
        customer = LoginPermanent.customer;
        txName.setText(customer.getName());
        txPhone.setText(customer.getTel());
        txPassword.setText(customer.getPassword());
        txMail.setText(customer.getMail());
        txAdress.setText(customer.getAdress());
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
        startActivity(new Intent(ProfieActivity.this, CustomerActivity.class));

    }

    public void Profiemenu(View view) {

        recreate();


    }

    public void Arabaaramamenu(View view) {
        startActivity(new Intent(ProfieActivity.this, ArabaAramaActivity.class));


    }

    public void Arabalarlistesi(View view) {
        startActivity(new Intent(ProfieActivity.this, ArabalarlistesiActivity.class));


    }

    public void Cikistmenu(View view) {
        AlertDialog.Builder uyariPenceresi = new AlertDialog.Builder(ProfieActivity.this);
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

    public void updatebtn(View view) {

        customer.setName(txName.getText().toString());
        customer.setTel(txPhone.getText().toString());
        customer.setPassword(txPassword.getText().toString());
        customer.setMail(txMail.getText().toString());
        customer.setAdress(txAdress.getText().toString());
        FirebaseService.UpdateData(customer);
    }
}