package com.dyllanriffo.evaluacion1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditarDatos extends AppCompatActivity implements View.OnClickListener {

    private Button botonEditor;
    private Button botonEliminar;
    private Button botonVerMapa;

    private TextView Datos;
    private TextView Lugar;

    private Conexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);

        botonEditor = (Button) findViewById(R.id.btnEditar);
        botonEliminar = (Button) findViewById(R.id.btnEliminar);
        botonVerMapa = (Button) findViewById(R.id.btnVerEnMapa);


        botonEditor.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonVerMapa.setOnClickListener(this);

        Datos =(TextView) findViewById(R.id.tbxDato);
        Lugar =(TextView) findViewById(R.id.tbxLugar);

        Lugar.setEnabled(false);

        Bundle datos =  getIntent().getExtras();

        String datoObtenido1 = datos.getString("datos");


        Datos.setText(datoObtenido1);

        Bundle lugar = getIntent().getExtras();
        String datoObtenido2 = lugar.getString("lugar");

        Lugar.setText(datoObtenido2);


        conexion = new Conexion(getApplicationContext(), "db_mapa", null, 1);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnEditar){

            SQLiteDatabase database  = conexion.getWritableDatabase();

            try {

                database.execSQL("UPDATE mapa SET datos = '"+ Datos.getText().toString() +"' WHERE lugar = '"+ Lugar.getText().toString() +"'");
                database.close();

                Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }

        }
            if (v.getId() == R.id.btnEliminar){

               try {
                   SQLiteDatabase database = conexion.getWritableDatabase();

                   String lugar = Lugar.getText().toString();

                   database.execSQL("DELETE FROM mapa WHERE lugar = '"+ lugar + "'");
                   database.close();


                   Lugar.setText("");
                   Datos.setText("");

                   Toast.makeText(getApplicationContext(), "Lugar Destruido", Toast.LENGTH_SHORT).show();

               }catch (Exception e){

                   Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
               }

            }
            if (v.getId()==R.id.btnVerEnMapa){

                String lugar =  Lugar.getText().toString();
                String dato =  Datos.getText().toString();

                Intent mapsIntent = new Intent(EditarDatos.this, MapsActivity.class);

                mapsIntent.putExtra("datos", dato.toString());
                mapsIntent.putExtra("lugar", lugar.toString());

                startActivity(mapsIntent);
            }
       }


}