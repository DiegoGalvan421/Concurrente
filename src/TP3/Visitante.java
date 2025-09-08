package TP3;

public class Visitante implements Runnable {
    private String nombre;
    private Area area;

    Visitante(String nombre, Area unArea) {
        this.nombre = nombre;
        this.area = unArea;
    }

    public void run() {
        for (int i = 0; i <= 6; i++) {
            int res = area.hacerReserva();
            if (res == 0) {
                System.out.println("No quedan espacios para reservar");
            } else {
                System.out.println(nombre + ": Reserve el lugar:" + res + " en el area:" + area.getNumeroArea());
            }
        }

    }
}
