package TP6.Observatorio;

public class Mantenimiento implements Runnable {
    private Observatorio obs;

    Mantenimiento(Observatorio obs) {
        this.obs = obs;
    }

    public void run() {
        while (true) {
            obs.ingresarMant();
            System.out.println("Manteniemiento entro a hacer su trabajo " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            obs.salirMant();
            System.out.println("Mantenimiento salio " + Thread.currentThread().getName());
        }
    }
}
