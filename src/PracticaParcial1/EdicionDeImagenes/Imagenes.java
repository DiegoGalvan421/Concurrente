package PracticaParcial1.EdicionDeImagenes;

public class Imagenes implements Runnable{
    private EdicionDeIm edi;

    Imagenes(EdicionDeIm a){
        edi=a;
    }
    public void run(){
        edi.meterIm();
        edi.guardarIm();
    }
}
