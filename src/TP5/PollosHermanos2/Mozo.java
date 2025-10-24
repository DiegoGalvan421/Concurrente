package TP5.PollosHermanos2;

public class Mozo implements Runnable {
    private SalaDeEspera sala;

    public Mozo(SalaDeEspera sala) {
        this.sala = sala;
    }

    public void run() {
        try {
            while (true) {
                sala.llevarB(); // Espera a que un empleado pida una bebida
                System.out.println("Mozo sirvió una bebida.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
