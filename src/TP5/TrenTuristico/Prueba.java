package TP5.TrenTuristico;

public class Prueba {
    public static void main(String[] args) {
        Tren tren= new Tren(2);
        for(int i=0; i<10;i++){
            Thread pas=new Thread(new Pasajero(tren),"Pasajero: "+(i+1));
            pas.start();
        }
        Thread vendedor= new Thread(new VendedorTickets(tren));
        vendedor.start();
        Thread control= new Thread(new ControlTren(tren));
        control.start();
    }
}
