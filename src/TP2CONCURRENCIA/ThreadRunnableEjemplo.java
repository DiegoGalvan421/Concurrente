package TP2CONCURRENCIA;

public class ThreadRunnableEjemplo implements Runnable {
    private String nombre;

    public ThreadRunnableEjemplo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + nombre);
        System.out.println("Termina thread " + nombre);
    }
    }
