package TP6.Observatorio;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.*;

public class Observatorio {
    private Lock lock = new ReentrantLock(true);
    private Condition visitante = lock.newCondition();
    private Condition sillaDeRuedas = lock.newCondition();// los visitantes con sillas de ruedas tendran un boolean en
                                                          // true
    private Condition mantenimiento = lock.newCondition();
    private Condition investigador = lock.newCondition();
    private int capacidadMax = 50;
    private int sillas = 0;
    private int sillasEnEspera = 0;
    private int visitantes = 0;
    private int manteni = 0;
    private int visitantesHisotricos = 0;
    private boolean hayInvest = false;
    private int mantenimientoSeguido = 0;

    Observatorio() {

    }

    // considerar sala de espera con silla de ruedas
    public void ingresarVis(Boolean tieneSilla) {
        lock.lock();
        try {

            if (tieneSilla) {
                sillasEnEspera++;
                // esperar mientras esté lleno el cupo para sillas, haya mantenimiento
                // en curso o haya un investigador (investigador es exclusivo)
                while (visitantes >= 30 || manteni > 0 || hayInvest || visitantesHisotricos >= 60) {
                    sillaDeRuedas.await();

                }
                sillasEnEspera--;
                capacidadMax = 30;
                sillas++;
                visitantes++;
                visitantesHisotricos++;

            } else {
                // visitantes normales esperan si se alcanzó la capacidad, hay mantenimiento,
                // hay investigador (exclusivo), se llegó al tope historico o hay prioridad
                // de personas con silla en espera.
                while (visitantes >= capacidadMax || manteni > 0 || hayInvest || visitantesHisotricos >= 60
                        || sillasEnEspera > 0) {
                    visitante.await();
                }
                visitantes++;
                visitantesHisotricos++;
                mantenimientoSeguido = 0;

            }
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }

    }

    public void salirVis(boolean tieneSilla) {
        lock.lock();
        if (tieneSilla) {
            sillas--;
            visitantes--;
            if (sillas == 0) {
                capacidadMax = 50;
            }
            if (visitantes == 0) {
                mantenimiento.signalAll();
                investigador.signalAll();

            }
        } else {
            visitantes--;
            if (visitantes == 0) {
                mantenimiento.signalAll();
                investigador.signalAll();

            }

        }
        sillaDeRuedas.signalAll();
        visitante.signalAll();
        lock.unlock();
    }

    public void ingresarMant() {
        lock.lock();
        try {
            // mantenimiento sólo puede entrar cuando no hay visitantes y no hay
            // investigador
            while (visitantes > 0 || hayInvest || mantenimientoSeguido > 10) {
                mantenimiento.await();
            }
            manteni++;
            mantenimientoSeguido++;
            visitantesHisotricos = 0;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public void salirMant() {
        lock.lock();
        manteni--;
        if (manteni == 0) {
            investigador.signalAll();
            sillaDeRuedas.signalAll();
            visitante.signalAll();
        }
        lock.unlock();
    }

    public void ingresarInvest() {
        lock.lock();
        try {
            // investigador necesita exclusividad: no visitantes ni mantenimiento ni otro
            // investigador
            while (visitantes > 0 || manteni > 0 || hayInvest) {
                investigador.await();
            }
            hayInvest = true;
            visitantesHisotricos = 0;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public void salirInvest() {
        lock.lock();
        hayInvest = false;
        sillaDeRuedas.signalAll();
        visitante.signalAll();
        mantenimiento.signalAll();
        investigador.signal();
        lock.unlock();
    }
}
