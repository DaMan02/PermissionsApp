package com.example.dayal.fragmentsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    static final int MY_PERM_REQ_CAMERA=1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btn);
        // int permissionCheckContacts= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheckCamera= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                if(permissionCheckCamera!= PackageManager.PERMISSION_GRANTED){
                    // Should we show an explanation?
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA)){
                        Toast.makeText(MainActivity.this, "This permission is very good! Accept it", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},MY_PERM_REQ_CAMERA);
                        //TODO if user sees msg, try again to request
                    }
                    else {    // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},MY_PERM_REQ_CAMERA);
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case MY_PERM_REQ_CAMERA: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                // permission was granted, yay! Do the
                // contacts-related task you need to do
                Toast.makeText(this, "Thank you allowing access to your camera.", Toast.LENGTH_SHORT).show();                         }
                else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(this, "We cannot reach your camera", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }
}


