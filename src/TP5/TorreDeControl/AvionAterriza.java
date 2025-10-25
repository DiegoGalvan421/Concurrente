package TP5.TorreDeControl;

public class AvionAterriza implements Runnable {
    private TorreDeControl tor;

    AvionAterriza(TorreDeControl tor) {
        this.tor = tor;
    }

    public void run() {
        try {
            tor.solicitarAterrizaje();
            System.out.println("solicite aterrizar: "+Thread.currentThread().getName());
            tor.aterrizar(); // luego va a tierra
            System.out.println("aterrice: "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
