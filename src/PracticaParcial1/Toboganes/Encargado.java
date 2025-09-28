package PracticaParcial1.Toboganes;

public class Encargado implements Runnable{
    private LosToboganes tobs;

    Encargado (LosToboganes nuevo){
        tobs=nuevo;
    }
    public void run() {
    while (tobs.hayPersonasEnMirador()) {
        tobs.habilitarBajada();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }
    System.out.println("No quedan más personas en el mirador");
}
}
