package PracticaSemaforos.MontañaRusa;

public class Prueba {
    public static void main(String[] args) {
        Carro mr = new Carro(10, 7);
        Thread cont = new Thread(new Controlador(mr),"Controlador");
        cont.start();

        for(int i = 0; i<100; i++){
            Thread vis = new Thread(new Pasajero(mr),"Pasajero "+i);
            vis.start();
        }
    }
}
