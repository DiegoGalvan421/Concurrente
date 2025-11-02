package TP6.BarcoDePyA;

public class Auto implements Runnable{
    private Ferry fer;

    Auto(Ferry f) {
        fer = f;
    }

    public void run() {
        try {
            fer.ingresarAuto();
            System.out.println("Embarque " + Thread.currentThread().getName());
            fer.salirAuto();
            System.out.println("Desembarque " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
