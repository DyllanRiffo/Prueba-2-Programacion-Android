package com.dyllanriffo.evaluacion1android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddSensore;
    private Button btnMaps;
    private ListView listViewSensores;

    Conexion conn;
    ArrayList<String> listaInformacion;
    ArrayList<Sensores> listaSensores;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayAdapter adapter;
    private List list;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddSensore = (Button) findViewById(R.id.btnAddSensore);
        btnAddSensore.setOnClickListener(this);

        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(this);

        listViewSensores = (ListView) findViewById(R.id.idListViewSensores);

        conn = new Conexion(getApplicationContext(), "db_sensores", null,1);

        consultarSensores();

        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewSensores.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                actualizarRefresh();
            }
        });
    }

    private void consultarSensores() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Sensores sensores = null;
        listaSensores =  new ArrayList<Sensores>();

        Cursor cursor = db.rawQuery("SELECT * FROM sensores", null);

        while (cursor.moveToNext()) {
            sensores =  new Sensores();
            sensores.setNombre_sensor(cursor.getString(0));
            sensores.setValor_sensore(cursor.getString(1));
            sensores.setFecha_hora(cursor.getString(2));
            listaSensores.add(sensores);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion =  new ArrayList<String>();
        for (int i = 0; i < listaSensores.size(); i++){
            listaInformacion.add(listaSensores.get(i).getNombre_sensor()+ " - "+ listaSensores.get(i).getValor_sensore() + " - " + listaSensores.get(i).getFecha_hora());
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAddSensore){
            Intent btnAddSensoreIntent = new Intent(this, AddSensor.class);
            startActivity(btnAddSensoreIntent);
        }

        if(v.getId() == R.id.btnMaps){
            Intent btnMapsIntent = new Intent(this, AgregarMapa.class);
            startActivity(btnMapsIntent);
        }
    }

    public  void actualizarRefresh(){

        consultarSensores();

        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewSensores.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}