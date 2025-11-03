package TP6.Pasteleria;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class Mostrador {
    private Lock lock = new ReentrantLock();
    private Condition robots = lock.newCondition();
    private Condition hornos = lock.newCondition();
    // cola con pesos de los pasteles
    private Queue<Integer> pasteles = new LinkedList<>();
    private int cantidadPastelesMax;
    private int cantidadPastelesAct = 0;

    Mostrador(int cant) {
        cantidadPastelesMax = cant;
    }


    public void ponerPastelEnMostrador(int peso) {
        lock.lock();
        try {
            while (cantidadPastelesAct == cantidadPastelesMax) {
                hornos.await();
            }
            pasteles.add(peso);
            cantidadPastelesAct++;
            System.out.println("hice un pastel, depsierto a los empacadores "+Thread.currentThread().getName());
            robots.signalAll();
        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    

    public int tomarPastel() {
        int peso = 0;
        lock.lock();
        try {
            while (cantidadPastelesAct == 0) {
                robots.await();
            }
            peso = (int) pasteles.poll();
            cantidadPastelesAct--;
            System.out.println("Tome un pastel "+Thread.currentThread().getName());
            if (cantidadPastelesAct == 0) {
                System.out.println("Despierto a los hornos para que produzcan "+Thread.currentThread().getName());
                hornos.signalAll();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
        return peso;

    }
}
