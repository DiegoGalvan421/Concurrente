package TP5.GestorPiscina;

public class Visitante implements Runnable {
    private GestorPiscina gest;

    Visitante(GestorPiscina gest) {
        this.gest = gest;
    }

    public void run() {
        try {
            System.out.println("intento ingresar "+Thread.currentThread().getName());
            gest.ingresar();
            Thread.sleep(500);
            System.out.println("estoy en la piscina "+Thread.currentThread().getName());
            gest.salir();
            System.out.println("sali de la piscina "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
