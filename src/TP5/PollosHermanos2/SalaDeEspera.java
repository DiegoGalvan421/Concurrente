package TP5.PollosHermanos2;

import java.util.concurrent.Semaphore;

public class SalaDeEspera {
    private Semaphore sala=new Semaphore(2,true);
    private Semaphore avisarMozo= new Semaphore(0);
    private Semaphore llevarBebida=new Semaphore(0);
    private Semaphore avisarCocinero=new Semaphore(0);
    private Semaphore servirComida=new Semaphore(0);
    SalaDeEspera(){
    }

    public void entrarSala() throws InterruptedException{
        sala.acquire();//entra a la sala
    }

    public void avisarB(){
        avisarMozo.release();
    }

    public void llevarB()throws InterruptedException{
        avisarMozo.acquire();
        llevarBebida.release();
    }

    public void recibirB()throws InterruptedException{
        llevarBebida.acquire();
    }

    public void avisarC(){
        avisarCocinero.release();
    }

    public void llevarC()throws InterruptedException{
        avisarCocinero.acquire();
        servirComida.release();
    }

    public void recibirC()throws InterruptedException{
        servirComida.acquire();
    }

    public void dejarSala(){
        sala.release();
    }
}
