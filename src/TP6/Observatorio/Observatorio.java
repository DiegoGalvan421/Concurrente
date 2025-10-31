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
    private int visitantes = 0;
    private int manteni = 0;
    private boolean hayInvest=false;
    Observatorio() {

    }
    //considerar sala de espera con silla de ruedas
    public void ingresarVis(Boolean tieneSilla) {
        lock.lock();
        try {

            if (tieneSilla) {
                while (visitantes >= 30 || manteni != 0 || !hayInvest) {
                    sillaDeRuedas.await();

                }
                capacidadMax = 30;
                sillas++;
                visitantes++;

            } else {
                while (visitantes >= capacidadMax || manteni != 0 || !hayInvest) {
                    visitante.await();
                }
                visitantes++;

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
                investigador.signalAll();
                mantenimiento.signalAll();
                sillaDeRuedas.signalAll();
                visitante.signalAll();
            } else {
                sillaDeRuedas.signal();
                visitante.signal();
            }
        } else {
            visitantes--;
            if (visitantes == 0) {
                investigador.signalAll();
                mantenimiento.signalAll();
                sillaDeRuedas.signalAll();
                visitante.signalAll();
            } else {
                //puede ir afuera del if
                sillaDeRuedas.signalAll();
                visitante.signalAll();
            }
        }
        lock.unlock();
    }

    public void ingresarMant() {
        lock.lock();
        try {
            while (visitantes>0 || !hayInvest) {
                mantenimiento.await();
            }
            manteni++;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {lock.unlock();
        }
    }

    public void salirMant(){
        lock.lock();
        manteni--;
            if(manteni==0){
                investigador.signalAll();
                sillaDeRuedas.signalAll();
                visitante.signalAll();
            }
        lock.unlock();
    }
    public void ingresarInvest(){
        lock.lock();
        try {
            while(visitantes!=0 || manteni!=0 || hayInvest){
                investigador.await();
            }
            hayInvest=true;
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void salirInvest(){
        lock.lock();
        hayInvest=false;
        investigador.signalAll();
        mantenimiento.signalAll();
        sillaDeRuedas.signalAll();
        visitante.signalAll();
        lock.unlock();
    }
}
