package TP3;

public class Punto7 {
    public static void main(String[] args) throws InterruptedException {
        StringCompartido texto = new StringCompartido();
        int repeticiones = 3; // cantidad de veces que se repite la secuencia ABBCCC...

        Thread A = new Thread(new HiloA(texto, repeticiones));
        Thread B = new Thread(new HiloB(texto, repeticiones));
        Thread C = new Thread(new HiloC(texto, repeticiones));

        A.start();
        B.start();
        C.start();
        //el join hace que el hilo del main espere a que estos terminen para ocntinuar.
        A.join();
        B.join();
        C.join();

        System.out.println(texto.toString());
    }

}
