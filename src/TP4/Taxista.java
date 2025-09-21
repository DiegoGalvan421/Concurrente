package TP4;

public class Taxista implements Runnable{
    private String nombre;
    private Viajes actual;

    Taxista(String nombre, Viajes act){
        this.nombre=nombre;
        actual=act;
    }
    public void run(){
        actual.esperarPasajeroYconducir();
        actual.avisarLlegada();
    }
}
