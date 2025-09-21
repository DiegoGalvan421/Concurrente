package TP4;

public class PruebaTaxis {
    public static void main(String[] args) throws InterruptedException{
        Viajes viaj=new Viajes();
        Thread pasajero= new Thread(new Pasajero("Diego", viaj),"diego");
        Thread taxista= new Thread(new Taxista("pedro", viaj),"pedro");

        pasajero.start();
        taxista.start();
        pasajero.join();
        taxista.join();

    }
}
