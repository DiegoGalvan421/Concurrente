package TP5.FormacionDeAgua;

public class Oxigeno implements Runnable{
    private Recipiente rec;

    Oxigeno( Recipiente rec){
        this.rec=rec;
    }
    public void run(){
        rec.Olisto();
        System.out.println("Estoy listo: "+Thread.currentThread().getName());
        try {
            rec.hacerAgua();
            System.out.println("Se hizo agua");
            rec.terminarOx();
            System.out.println("termine mi ejecucion "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
