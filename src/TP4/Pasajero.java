package TP4;

public class Pasajero implements Runnable{
    private String nombre;
    private Viajes actual;

    Pasajero(String nombre, Viajes act){
        this.nombre=nombre;
        actual=act;
    }
    public void run(){
        actual.solicitarViaje();
        actual.esperarLlegada();
    }
}
