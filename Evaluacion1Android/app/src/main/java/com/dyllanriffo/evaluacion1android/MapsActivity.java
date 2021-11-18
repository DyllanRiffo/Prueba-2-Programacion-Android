package com.dyllanriffo.evaluacion1android;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.dyllanriffo.evaluacion1android.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle datos =  getIntent().getExtras();

        String datoObtendio = datos.getString("datos");

        String[] parts = datoObtendio.split(",");

        String part1 = parts[0];
        String part2 = parts[1];

        Toast.makeText(getApplicationContext(), part1 + "\n" +part2, Toast.LENGTH_LONG).show();

        Bundle lugar = getIntent().getExtras();

        String lugarObtenido =  lugar.getString("lugar");

        LatLng lugarMapa = new LatLng(Double.parseDouble(part1), Double.parseDouble(part2));

        mMap.addMarker(new MarkerOptions().position(lugarMapa).title(lugarObtenido));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugarMapa));
    }
}