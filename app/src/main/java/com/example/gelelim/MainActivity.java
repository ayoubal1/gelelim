package com.example.gelelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gelelim.Database.Ads;
import com.example.gelelim.Database.Customer;
import com.example.gelelim.Database.Driver;
import com.example.gelelim.FireCloud.FirebaseService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    ImageView image;

    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

      /*  Driver driver=new Driver();
        driver.setName("Halit");
        driver.setAdress("Burasi");
        driver.setTel("054555555");

        FirebaseService.AddData(driver);
        Customer customer=new Customer();
        customer.setAdress("Orası");
        customer.setName("Eyüp");
        customer.setTel("0555555555");
        FirebaseService.AddData(customer);*/
     //   Driver driver = FirebaseService.ReadDatas(new Driver()).get(0);
      //  Customer customers = FirebaseService.ReadDatas(new Customer()).get(0);
    /*    for (int i = 0; i < 10; i++) {
            Ads ads=new Ads();
            ads.setDriverid(driver.getId());
            ads.setLocation("Adana"+i);
            ads.setType("Sakso");
            ads.setPlaka("01ADS31");
            FirebaseService.AddData(ads);
        }*/
       /* ArrayList<Ads> adsList = FirebaseService.ReadDatas(new Ads());
        Ads sec = adsList.get(4);
        String idDriver = sec.getDriverid();
        Driver getirDriver = new Driver();
        getirDriver.setId(idDriver);
        getirDriver = FirebaseService.ReadData(getirDriver).get(0);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.imageView2);
        logo = findViewById(R.id.textView3);
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Customerlogin.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
