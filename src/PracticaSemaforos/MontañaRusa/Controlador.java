package PracticaSemaforos.MontañaRusa;

public class Controlador implements Runnable{
    private Carro mr;

    Controlador(Carro m){
        mr=m;
    }

    public void run(){
        try {
            while (true) {
                mr.comenzarRecorrido();
                mr.resetRecorrido();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
