package PracticaSemaforos.MontañaRusa;

public class Pasajero implements Runnable{
    private Carro mr;

    Pasajero(Carro m){
        mr=m;
    }
    public void run(){
        try {
            while (true) {
                mr.intentarSubir();
                mr.bajarMR();
                Thread.sleep(400);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
