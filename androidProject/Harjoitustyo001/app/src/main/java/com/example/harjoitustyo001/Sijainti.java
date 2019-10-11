package com.example.harjoitustyo001;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Sijainti extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sijainti2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }

    private void openMain() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        double x1 = bundle.getDouble("x1");
        double y1 = bundle.getDouble("y1");

        // Add a marker in "merituuliohjelmointi" and move the camera
        LatLng placeName = new LatLng(x1, y1);
        mMap.addMarker(new MarkerOptions().position(placeName).title("Merituuli Ohjelmointi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placeName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeName,15));
    }
}



