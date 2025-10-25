package TP5.LosToboganes;

public class Encargado implements Runnable{
    private Escalera esc;

    Encargado(Escalera esc){
        this.esc=esc;
    }

    public void run(){
        try {
            while (true) {
                esc.esperarPedido();
                esc.habilitarBajada();
                System.out.println("Encargado: di un permiso");
            }
        } catch (InterruptedException e) {
            // terminar hilo si se interrumpe
            Thread.currentThread().interrupt();
        }
    }
}
