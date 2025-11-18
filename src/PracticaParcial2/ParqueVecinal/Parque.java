package PracticaParcial2.ParqueVecinal;

import java.util.concurrent.locks.*;

public class Parque {
    private Lock lock = new ReentrantLock();
    private Condition escuelasEsperan = lock.newCondition();
    private Condition visitantesEsperan = lock.newCondition();
    private Condition residentesEsperan = lock.newCondition();
    private int escuelasEnEspera = 0;
    private int reduccionDeAforo = 20;
    private int aforoMax;
    private int aforoAct = 0;
    private int residentesEnEsperan = 0;
    private int escuelasSeguidas = 0;
    private int residentesSeguidos = 0;
    private int visitantesSeguidos = 0;
    private int visitantesEnEspera = 0;

    Parque(int aforo) {
        this.aforoMax = aforo;
    }

    public void entrarResidente() {
        lock.lock();
        try {
            residentesEnEsperan++;
            while (aforoAct >= aforoMax || escuelasEnEspera > 0 || (residentesSeguidos >= 5 && visitantesEnEspera > 0)) {
                residentesEsperan.await();
            }
            residentesSeguidos++;
            aforoAct++;
            residentesEnEsperan--;
            System.out.println("Residente entró al parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } catch (InterruptedException e) {
            System.err.println("Error en entrarResidente: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void entrarVisitante() {
        lock.lock();
        try {
            visitantesEnEspera++;
            while (aforoAct >= aforoMax || escuelasEnEspera > 0 || residentesEnEsperan > 0) {
                visitantesEsperan.await();
            }
            visitantesEnEspera--;
            visitantesSeguidos++;
            if (visitantesSeguidos % 10 == 0) {
                residentesSeguidos = 0;
            } else if (visitantesSeguidos % 20 == 0) {
                escuelasSeguidas = 0;
            }
            aforoAct++;
            System.out.println("Visitante entró al parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } catch (InterruptedException e) {
            System.err.println("Error en entrarVisitante: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void entranEscuelas() {
        lock.lock();
        try {
            escuelasEnEspera++;
            while ((aforoAct + 10) > (aforoMax - reduccionDeAforo) || (escuelasSeguidas >= 2 && residentesEnEsperan > 0)) {
                escuelasEsperan.await();
            }
            escuelasSeguidas++;
            aforoMax -= reduccionDeAforo;
            aforoAct += 10;
            escuelasEnEspera--;
            System.out.println("Escuela entró al parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } catch (InterruptedException e) {
            System.err.println("Error en entranEscuelas: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void salirEscuela() {
        lock.lock();
        try {
            aforoMax += reduccionDeAforo;
            aforoAct -= 10;
            visitantesEsperan.signalAll();
            residentesEsperan.signalAll();
            escuelasEsperan.signalAll();
            System.out.println("Escuela salió del parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } finally {
            lock.unlock();
        }
    }

    public void salirResidente() {
        lock.lock();
        try {
            aforoAct--;
            visitantesEsperan.signalAll();
            residentesEsperan.signalAll();
            escuelasEsperan.signalAll();
            System.out.println("Residente salió del parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } finally {
            lock.unlock();
        }
    }

    public void salirVisitante() {
        lock.lock();
        try {
            aforoAct--;
            visitantesEsperan.signalAll();
            residentesEsperan.signalAll();
            escuelasEsperan.signalAll();
            System.out.println("Visitante salió del parque. Aforo actual: " + aforoAct + "/" + aforoMax);
        } finally {
            lock.unlock();
        }
    }
}
