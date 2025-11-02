package TP6.PuenteEstrecho.monitoresConPrioridad;

public class CocheSur implements Runnable{
    private Puente puen;

    CocheSur(Puente puen){
        this.puen=puen;
    }

    public void run(){
        puen.entrarCocheSur();
        System.out.println("Estoy cruzando el puente Sur "+Thread.currentThread().getName());
        puen.salirCocheSur();
        System.out.println("Termine de cruzar el puente Sur "+ Thread.currentThread().getName());
    }
}
