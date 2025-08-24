package TP2CONCURRENCIA;

public class PruebaRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadRunnableEjemplo("Maria Jose"));
        Thread t2 = new Thread(new ThreadRunnableEjemplo("Jose Maria"));

        t1.start();
        t2.start();

        System.out.println("Termina thread main");
    }
}
