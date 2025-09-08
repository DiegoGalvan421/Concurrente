package TP3;

import java.util.Random;

public class Punto6 {
    public static void main(String[] args) throws InterruptedException {
        final int N = 50000;
        final int K = 10;
        int[] arreglo = new int[N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            arreglo[i] = rand.nextInt(10) + 1;
        }

        int[] sumasParciales = new int[K];
        Thread[] hilos = new Thread[K];
        int segmento = N / K;

        for (int i = 0; i < K; i++) {
            int inicio = i * segmento;
            int fin;
            if (i == K - 1) {
                fin = N;
            } else {
                fin = inicio + segmento;
            }
            hilos[i] = new Thread(new SumadorParcial(arreglo, inicio, fin, sumasParciales, i));
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        int sumaTotal = 0;
        for (int i = 0; i < sumasParciales.length; i++) {
            sumaTotal += sumasParciales[i];
        }

        System.out.println("La suma total es: " + sumaTotal);
    }
}
