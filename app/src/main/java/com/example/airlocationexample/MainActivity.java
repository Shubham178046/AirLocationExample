package com.example.airlocationexample;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;
import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity extends AppCompatActivity {
    private Double currentLatitude;
    private Double currentLongitude;
    private AirLocation airLocation;
    private Button btn;
    private TextView latitude, lognitude;
    private HashMap _$_findViewCache;

    public final Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    public final void setCurrentLatitude(@Nullable Double var1) {
        this.currentLatitude = var1;
    }

    public final Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    public final void setCurrentLongitude(@Nullable Double var1) {
        this.currentLongitude = var1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnLocation);
        latitude = findViewById(R.id.txtlatitude);
        lognitude = findViewById(R.id.txtlognitude);

        getLocation();
        airLocation.start();

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                airLocation.start();
            }
        });*/

        /*btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                MainActivity.this.getLocation();
                AirLocation var10000 = MainActivity.this.airLocation;
                Intrinsics.checkNotNull(var10000);
                var10000.start();
            }
        }));*/
    }

    private void getLocation() {
        airLocation = new AirLocation(this, new AirLocation.Callback() {
            public void onFailure(AirLocation.LocationFailedEnum locationFailedEnum) {
            }

            public void onSuccess(ArrayList location) {
                Object var10001 = location.get(0);
                Log.d("TAG", "onSuccess: "+((Location) var10001).getLatitude() + " " + ((Location) var10001).getLongitude());
                latitude.setText(String.valueOf(((Location) var10001).getLatitude()));
                lognitude.setText(String.valueOf(((Location) var10001).getLongitude()));
                //Log.d("TAG", "onSuccess: "+( );
                Log.d("Location", "onSuccess: "+ (((ArrayList<Location>) location).get(0).getLatitude()) + " " + (((ArrayList<Location>) location).get(0).getLongitude()));
            }
        }, true, 0L, "");

    }

  /* private final void getLocation() {
       this.airLocation = new AirLocation((Activity)this, (AirLocation.Callback)(new AirLocation.Callback() {
           public void onFailure( AirLocation.LocationFailedEnum locationFailedEnum) {
               Intrinsics.checkNotNullParameter(locationFailedEnum, "locationFailedEnum");
           }

           public void onSuccess( ArrayList location) {
               MainActivity var10000 = MainActivity.this;
               Object var10001 = location.get(0);
               Intrinsics.checkNotNullExpressionValue(var10001, "location.get(0)");
               var10000.setCurrentLatitude(((Location)var10001).getLatitude());
               var10000 = MainActivity.this;
               var10001 = location.get(0);
               var10000.setCurrentLongitude(((Location)var10001).getLongitude());
               Intrinsics.checkNotNullExpressionValue(var10001, "location.get(0)");
               Log.d("TAG", "onSuccess: " + MainActivity.this.getCurrentLatitude() + " / " + MainActivity.this.getCurrentLongitude());
               latitude.setText((CharSequence)String.valueOf(MainActivity.this.getCurrentLatitude()));
               lognitude.setText((CharSequence)String.valueOf(MainActivity.this.getCurrentLongitude()));
           }
       }), false, 0L, "Location");
   }
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       AirLocation var10000 = this.airLocation;
       Intrinsics.checkNotNull(var10000);
       var10000.onActivityResult(requestCode, resultCode, data);
   }
   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
       AirLocation var10000 = this.airLocation;
       Intrinsics.checkNotNull(var10000);
       var10000.onRequestPermissionsResult(requestCode, permissions, grantResults);
   }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
