package TP6.PuenteEstrecho.monitoresConPrioridad;

public class CocheNorte implements Runnable{
    private Puente puen;

    CocheNorte(Puente puen){
        this.puen=puen;
    }

    public void run(){
        puen.entrarCocheNorte();
        System.out.println("Estoy cruzando el puente Norte "+Thread.currentThread().getName());
        puen.salirCocheNorte();
        System.out.println("Termine de cruzar el puente Norte "+ Thread.currentThread().getName());
    }
}
