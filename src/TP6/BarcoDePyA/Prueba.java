package TP6.BarcoDePyA;

public class Prueba {
    public static void main(String[] args) {
        Ferry fer = new Ferry(30);

        for (int i = 0; i < 100; i++) {
            Thread pas = new Thread(new Pasajero(fer), "Pasajero " + i);
            pas.start();
            if (i % 3 == 0) {
                Thread auto = new Thread(new Auto(fer), "Auto " + i);
                auto.start();
            }
        }
    }
}
