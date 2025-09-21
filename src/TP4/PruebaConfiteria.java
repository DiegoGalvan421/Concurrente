package TP4;

public class PruebaConfiteria {
    public static void main(String[] args) throws InterruptedException {
        Confiteria conf = new Confiteria();
        Thread mozo = new Thread(new Mozo(conf), "Mozo");
        mozo.start();

        int k = 5; // empleados
        int rondas = 2; // cada uno intenta comer 2 veces
        Thread[] empleados = new Thread[k];
        for (int i = 0; i < k; i++) {
            empleados[i] = new Thread(new Empleado("E" + (i + 1), conf, rondas), "Emp-" + (i + 1));
            empleados[i].start();
        }

        for (Thread t : empleados)
            t.join();
        mozo.interrupt();
        mozo.join();
        System.out.println("Fin.");
    }
}
