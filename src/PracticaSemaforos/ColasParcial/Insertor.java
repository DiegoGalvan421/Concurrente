package PracticaSemaforos.ColasParcial;

public class Insertor implements Runnable{
    private Cola col;

    Insertor(Cola cl){
        col=cl;
    }

    public void run(){
        while(true){
            try {
                col.insertar(1);
                Thread.sleep(400);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }
}
