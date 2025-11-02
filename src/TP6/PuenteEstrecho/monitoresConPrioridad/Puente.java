package TP6.PuenteEstrecho.monitoresConPrioridad;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Puente {
    private Lock lock = new ReentrantLock(true);
    private Condition cochesNorte = lock.newCondition();
    private Condition cochesSur = lock.newCondition();
    private int enEsperaSur = 0;
    private int enEsperaNorte = 0;
    private String direccionActual = "Sur";
    private int crucesSeguidos = 0;
    private int enPuente=0;

    Puente() {

    }

    public void entrarCocheNorte() {
        lock.lock();
        try {
            enEsperaNorte++;
            while (direccionActual.equals("Sur") || (crucesSeguidos == 10 && enEsperaSur > 0)) {
                cochesNorte.await();
            }
            enEsperaNorte--;
            enPuente++;
            crucesSeguidos++;
        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public void salirCocheNorte() {
        lock.lock();
        enPuente--;
        if ((crucesSeguidos == 10 && enEsperaSur > 0) || enPuente==0) {
            System.out.println("cambiamos la direcciona  sur");
            crucesSeguidos=0;
            direccionActual = "Sur";
            cochesSur.signal();
        } else {
            cochesNorte.signal();
        }
        lock.unlock();
    }

    public void entrarCocheSur() {
        lock.lock();
        try {
            enEsperaSur++;
            while (direccionActual.equals("Norte") || (crucesSeguidos == 10 && enEsperaNorte > 0)) {
                cochesSur.await();
            }
            enEsperaSur--;
            enPuente++;
            crucesSeguidos++;
        } catch (InterruptedException e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public void salirCocheSur() {
        lock.lock();
        enPuente--;
        if ((crucesSeguidos == 10 && enEsperaNorte > 0) || enPuente==0) {
            System.out.println("cambiamos la direccion a norte");
            crucesSeguidos=0;
            direccionActual = "Norte";
            cochesNorte.signal();
        } else {
            cochesSur.signal();
        }
        lock.unlock();
    }
}
