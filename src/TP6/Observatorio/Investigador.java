package TP6.Observatorio;

public class Investigador implements Runnable {
    private Observatorio obs;

    Investigador(Observatorio obs) {
        this.obs = obs;
    }

    public void run() {
        while (true) {
            obs.ingresarInvest();
            System.out.println("Yo investigador entre " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            obs.salirInvest();
            System.out.println("Yo investigador sali " + Thread.currentThread().getName());
        }
    }
}
