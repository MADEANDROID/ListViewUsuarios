package com.example.listviewusuarios;

public class Paquete {

    private String cod_paq;
    private String nombre;
    private int costo;
    private String descripción;

    public Paquete(String cod_paq, String nombre, int costo, String descripción) {
        this.cod_paq = cod_paq;
        this.nombre = nombre;
        this.costo = costo;
        this.descripción = descripción;
    }

    public String getCod_paq() {
        return cod_paq;
    }

    public void setCod_paq(String cod_paq) {
        this.cod_paq = cod_paq;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
}
