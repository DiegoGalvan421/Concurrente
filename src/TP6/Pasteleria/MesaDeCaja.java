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
<<<<<<< HEAD
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
=======
        boolean confirmo = false;
        try {
            // Esperar hasta que haya caja y que entre el pastel
            while (!cajaEnMostrador || cajaLista || (capacidadAct + peso > capacidadMax)) {

                // Solo confirmar una vez que no pudo colocar
                if (!confirmo) {
                    System.out.println(Thread.currentThread().getName() +
                            " confirma que no puede poner pastel de " + peso + " kg");
>>>>>>> a988ccca3466456b2f27c6efc78e8d4c311031c7
                    confirmaciones++;
                } else {
                    System.out.println("Puse un pastel " + Thread.currentThread().getName());
                    capacidadAct += peso;
                }
<<<<<<< HEAD
                if (capacidadAct == capacidadMax || confirmaciones == cantidadRobots) {
                    System.out.println("Termine la caja o se llegaron a las 3 confirmaciones, despierto al brazo "
                            + Thread.currentThread().getName());
                    cajaLista = true;
                    confirmaciones = 0;
                    brazo.signal();
                }
=======

                // Si todos confirmaron → pedir al brazo que reemplace la caja
                if (confirmaciones >= cantidadRobots) {
                    System.out.println(Thread.currentThread().getName() +
                            " detecta todas las confirmaciones (" + confirmaciones + "), aviso al brazo");
                    cajaLista = true; 
                    confirmaciones = 0; 
                    brazo.signal(); 
                }

                // Esperar a que la situación cambie (brazo reponga, etc.)
                robots.await();
            }

            // Si llegó acá, puede colocar el pastel
            System.out.println(Thread.currentThread().getName() +
                    " coloca pastel de " + peso + " kg en la caja");

            capacidadAct += peso;
            confirmo = false;

            // Si se llenó justo la caja
            if (capacidadAct >= capacidadMax) {
                System.out.println(Thread.currentThread().getName() +
                        " completó la caja, aviso al brazo para retirar");
                cajaLista = true;
                confirmaciones = 0;
                brazo.signal();
            }
>>>>>>> a988ccca3466456b2f27c6efc78e8d4c311031c7

            // Avisar a otros robots que algo cambió
            robots.signalAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

}
