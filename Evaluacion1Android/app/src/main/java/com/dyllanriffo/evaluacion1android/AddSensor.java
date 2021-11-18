package com.dyllanriffo.evaluacion1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AddSensor extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private Button btnSensorLuz;
    private Button btnAcelerometro;
    private Button btnSensorGravedad;

    private TextView txtValueSensorLuz;
    private TextView txtValueAcelerometro;
    private TextView txtValueSesnroGravedad;

    private Sensor accelerometerSensor;
    private Sensor lightSensor;
    private Sensor gravitySensor;
    private Sensor magneticSensor;
    private SensorManager sensorManager;

    private String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        btnSensorLuz = (Button) findViewById(R.id.btnSensorLuz);
        btnSensorLuz.setOnClickListener(this);

        btnAcelerometro = (Button) findViewById(R.id.btnAcelerometro);
        btnAcelerometro.setOnClickListener(this);

        btnSensorGravedad = (Button) findViewById(R.id.btnSensorGravedad);
        btnSensorGravedad.setOnClickListener(this);

        txtValueSensorLuz =  (TextView) findViewById(R.id.lblValueSensorLuz);
        txtValueAcelerometro =  (TextView) findViewById(R.id.lblValueAcelerometro);
        txtValueSesnroGravedad =  (TextView) findViewById(R.id.lblValueSesnroGravedad);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometerSensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gravitySensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        magneticSensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(this, accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gravitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticSensor,SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSensorLuz){
            String nombre, valor, fecha;

            nombre = "Sensor Luz";
            valor = txtValueSensorLuz.getText().toString();
            fecha = currentDateTimeString.toString();

            Conexion conn =   new Conexion(this, "db_sensores", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            try {
                String INSERT = "INSERT INTO sensores (nombre_sensor, valor_sensore, fecha_hora) VALUES ('" + nombre + "', '" + valor + "', '" + fecha + "')";
                db.execSQL(INSERT);
                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                db.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al registrar sensor", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == R.id.btnAcelerometro){

            Conexion conn =   new Conexion(this, "db_sensores", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            String nombre = "Acelerometro";
            String valor =  txtValueAcelerometro.getText().toString();
            String fecha =  currentDateTimeString.toString();

            try {
                String INSERT = "INSERT INTO sensores (nombre_sensor, valor_sensore, fecha_hora) VALUES ('" + nombre + "', '" + valor + "', '" + fecha + "')";
                db.execSQL(INSERT);
                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                db.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al registrar sensor", Toast.LENGTH_SHORT).show();
            }

        }

        if(v.getId() == R.id.btnSensorGravedad){

            Conexion conn =   new Conexion(this, "db_sensores", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            String nombre = "Sensor Gravedad";
            String valor =  txtValueSesnroGravedad.getText().toString();
            String fecha =  currentDateTimeString.toString();

            try {
                String INSERT = "INSERT INTO sensores (nombre_sensor, valor_sensore, fecha_hora) VALUES ('" + nombre + "', '" + valor + "', '" + fecha + "')";
                db.execSQL(INSERT);
                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                db.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al registrar sensor", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                txtValueAcelerometro.setText(String.valueOf(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_LIGHT:
                txtValueSensorLuz.setText(String.valueOf(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_GRAVITY:
                txtValueSesnroGravedad.setText(String.valueOf(sensorEvent.values[0]));
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}