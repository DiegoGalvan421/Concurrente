package TP5.FormacionDeAgua;

public class Hidrogeno implements Runnable{
    private Recipiente rec;

    Hidrogeno( Recipiente rec){
        this.rec=rec;
    }
    //consultar sobre la espera y finalizacion de los 3 hilos
    public void run(){
        rec.Hlisto();
        System.out.println("Estoy Listo: "+Thread.currentThread().getName());
        System.out.println("estoy esperando: "+Thread.currentThread().getName());
        try {
            rec.esperar();
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
        
        System.out.println("termine mi ejecucion: "+Thread.currentThread().getName());
        
    }
}
