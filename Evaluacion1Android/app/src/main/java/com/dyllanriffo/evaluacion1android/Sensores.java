package com.dyllanriffo.evaluacion1android;

public class Sensores {

    private String nombre_sensor;
    private String valor_sensore;
    private String fecha_hora;

    public Sensores(String nombre_sensor, String valor_sensore, String fecha_hora) {
        this.nombre_sensor = nombre_sensor;
        this.valor_sensore = valor_sensore;
        this.fecha_hora = fecha_hora;
    }

    public Sensores() {
    }

    public String getNombre_sensor() {
        return nombre_sensor;
    }

    public void setNombre_sensor(String nombre_sensor) {
        this.nombre_sensor = nombre_sensor;
    }

    public String getValor_sensore() {
        return valor_sensore;
    }

    public void setValor_sensore(String valor_sensore) {
        this.valor_sensore = valor_sensore;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
}