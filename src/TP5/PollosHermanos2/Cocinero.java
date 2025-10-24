package TP5.PollosHermanos2;

public class Cocinero implements Runnable {
    private SalaDeEspera sala;

    public Cocinero(SalaDeEspera sala) {
        this.sala = sala;
    }

    public void run() {
        try {
            while (true) {
                sala.llevarC(); // Espera a que un empleado pida comida
                System.out.println("Cocinero preparó una comida.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
