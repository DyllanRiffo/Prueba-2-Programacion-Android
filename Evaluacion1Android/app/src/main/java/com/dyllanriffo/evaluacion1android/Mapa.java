package com.dyllanriffo.evaluacion1android;

public class Mapa {

    private String lugar;
    private String datos;

    public Mapa(String lugar, String datos) {
        this.lugar = lugar;
        this.datos = datos;
    }

    public Mapa() {
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}