package TP4;

import java.util.concurrent.Semaphore;

public class GestorImpresoras {
    private Semaphore impresorasA;
    private Semaphore impresorasB;

    GestorImpresoras(int cantA, int cantB) {
        // con esto cargamos la cantidad de impresoras que estaran disponibles.
        impresorasA = new Semaphore(cantA);
        impresorasB = new Semaphore(cantB);
    }

    public void imprimir(char tipo) {
        //se usa else if, para controlar una entrada sistematica
        if (tipo == 'A') {
            try {
                impresorasA.acquire();
                System.out.println("imprimiendo:" + Thread.currentThread().getName() + " en impresora A");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                impresorasA.release();
            }
        } else if (tipo == 'B') {
            try {
                impresorasB.acquire();
                System.out.println("imprimiendo:" + Thread.currentThread().getName() + " en impresora B");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                impresorasB.release();
            }
        } else if (tipo == 'C') {
            //se utiliza un bucle con sleep, para que c, en algun momento imprima y no se pierda despues de intentar y no poder
            boolean impreso = false;
            while (!impreso) {
                try {
                    if (impresorasA.tryAcquire()) {
                        System.out.println("imprimiendo:" + Thread.currentThread().getName() + " en impresora A");
                        Thread.sleep(1000);
                        impresorasA.release();
                        impreso = true;
                    } else if (impresorasB.tryAcquire()) {
                        System.out.println("imprimiendo:" + Thread.currentThread().getName() + " en impresora B");
                        Thread.sleep(1000);
                        impresorasB.release();
                        impreso = true;
                    } else {
                        Thread.sleep(100); // Espera antes de volver a intentar
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
