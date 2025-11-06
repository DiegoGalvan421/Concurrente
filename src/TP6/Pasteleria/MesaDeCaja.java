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
        boolean confirmo = false;
        try {
            // Esperar hasta que haya caja y que quepa el pastel
            while (!cajaEnMostrador || cajaLista || (capacidadAct + peso > capacidadMax)) {

                // Solo confirmar una vez que no pudo colocar
                if (!confirmo) {
                    System.out.println(Thread.currentThread().getName() +
                            " confirma que no puede poner pastel de " + peso + " kg");
                    confirmaciones++;
                    confirmo = true;
                }

                // Si todos confirmaron → pedir al brazo que reemplace la caja
                if (confirmaciones >= cantidadRobots) {
                    System.out.println(Thread.currentThread().getName() +
                            " detecta todas las confirmaciones (" + confirmaciones + "), aviso al brazo");
                    cajaLista = true; // señal de que debe retirarse
                    confirmaciones = 0; // reiniciar para próxima caja
                    brazo.signal(); // despertar al brazo
                }

                // Esperar a que la situación cambie (brazo reponga, etc.)
                robots.await();
            }

            // Si llegó acá, puede colocar el pastel
            System.out.println(Thread.currentThread().getName() +
                    " coloca pastel de " + peso + " kg en la caja");

            capacidadAct += peso;
            confirmo = false; // reset local (no necesario, pero claro)

            // Si se llenó justo la caja
            if (capacidadAct >= capacidadMax) {
                System.out.println(Thread.currentThread().getName() +
                        " completó la caja, aviso al brazo para retirar");
                cajaLista = true;
                confirmaciones = 0;
                brazo.signal();
            }

            // Avisar a otros robots que algo cambió (quizás ahora sí entre su pastel)
            robots.signalAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

}
