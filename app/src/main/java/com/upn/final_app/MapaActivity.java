package com.upn.final_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.GoogleMaps);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        String titulo = getIntent().getStringExtra("titulo");
        String latitud = getIntent().getStringExtra("latitud");
        String longitud = getIntent().getStringExtra("longitud");

        LatLng latLng = new LatLng(
                Double.parseDouble(latitud),
                Double.parseDouble(longitud));
        googleMap.addMarker(new MarkerOptions().position(latLng).title(titulo));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }
}