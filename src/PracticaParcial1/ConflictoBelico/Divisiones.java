package PracticaParcial1.ConflictoBelico;

public class Divisiones implements Runnable{
    private Monitor mon;
    private int id;

    Divisiones(int num, Monitor m){
        mon=m;
        id=num;
    }
    public void run(){
        boolean imprimi=false;

        while(!imprimi){
            imprimi=mon.publicar(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
        }
        imprimi=false;
        while(!imprimi){
            imprimi=mon.publicar(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
