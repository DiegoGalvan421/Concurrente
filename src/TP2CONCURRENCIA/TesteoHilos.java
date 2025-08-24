package TP2CONCURRENCIA;

public class TesteoHilos {
    public static void main(String[] args) {
        Thread miHilo = new MiEjecucion();
        miHilo.start();
        try {
            Thread.sleep(10); // pausa breve, da tiempo a miHilo
        } catch (InterruptedException e) {}
        System.out.println("En el main");
    }
}
