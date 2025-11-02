package TP6.BarcoDePyA;

public class Pasajero implements Runnable {
    private Ferry fer;

    Pasajero(Ferry f) {
        fer = f;
    }

    public void run() {
        try {
            fer.ingresarPasajero();
            System.out.println("Embarque " + Thread.currentThread().getName());
            fer.salirPasajero();
            System.out.println("Desembarque " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
