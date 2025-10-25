package TP5.LosToboganes;

public class Visitante implements Runnable {
    private Escalera esc;

    Visitante(Escalera esc) {
        this.esc = esc;
    }

    public void run() {
        try {
            System.out.println("esperando para entrar a la escalera " + Thread.currentThread().getName());
            esc.entrar();
            System.out.println("Entre a la escalera " + Thread.currentThread().getName());
            esc.esperarPermiso();
            System.out.println("Tengo permiso para bajar " + Thread.currentThread().getName());
            esc.bajar();
            esc.liberarMirador();
            System.out.println("libere mirador " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
