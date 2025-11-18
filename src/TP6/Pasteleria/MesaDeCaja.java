package TP6.Pasteleria;

import java.util.concurrent.locks.*;

public class MesaDeCaja {
    private Lock lock = new ReentrantLock();
    private Condition robots = lock.newCondition();
    private Condition brazo = lock.newCondition();
    private int cajasListas = 0;
    private int capacidadMax;
    private int capacidadAct = 0;
    private int cantidadRobots;
    private int confirmaciones = 0;
    private Boolean cajaLista = false;
    private Boolean cajaEnMostrador = true;

    MesaDeCaja(int cap, int cantRob) {
        capacidadMax = cap;
        cantidadRobots = cantRob;
    }

    public void retirarCaja() {
        lock.lock();
        try {
            while (!cajaLista) {
                brazo.await();
            }
            System.out.println("Saco la caja del mostrador y la dejo lista que pesa: " + capacidadAct);
            cajaEnMostrador = false;
            cajaLista = false;
            cajasListas++;
            System.out.println("Cajas listas " + cajasListas);
        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }

    }

    public void reponerCaja() {
        lock.lock();
        System.out.println("pongo una nueva caja en el mostrador y despierto a los empacadores");
        capacidadAct = 0;
        cajaEnMostrador = true;
        robots.signalAll();
        lock.unlock();
    }

    public void soltarPastel(int peso) {
        lock.lock();
        boolean coloco = false;
        boolean confirmo=false;
        try {
                // corregir que no tire el pastel

                //si se vuelve a despertar es solo porque el brazo despiertas
                while (!cajaEnMostrador || cajaLista) {
                    robots.await();
                }
                if ((peso + capacidadAct) > capacidadMax) {
                    System.out.println("Confirmo que no pude poner un pastel de " + peso + "kg "
                            + Thread.currentThread().getName());
                    confirmaciones++;
                } else {
                    System.out.println("Puse un pastel " + Thread.currentThread().getName());
                    capacidadAct += peso;
                }
                if (capacidadAct == capacidadMax || confirmaciones == cantidadRobots) {
                    System.out.println("Termine la caja o se llegaron a las 3 confirmaciones, despierto al brazo "
                            + Thread.currentThread().getName());
                    cajaLista = true;
                    confirmaciones = 0;
                    brazo.signal();
                }

        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

}
