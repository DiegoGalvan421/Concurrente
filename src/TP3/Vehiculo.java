package TP3;

public class Vehiculo {
    protected String patente;
    protected String modelo;
    protected String marca;
    protected int km;

    public Vehiculo(String patente, String modelo, String marca, int km) {
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.km = km;
    }
}
