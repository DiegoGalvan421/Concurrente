package TP3;

public class Punto5 {
    public static void main(String[] args) {
        Surtidor surtidor = new Surtidor(100); // 100 litros en el surtidor

        Thread[] autos = new Thread[5];
        autos[0] = new Thread(new Auto("AAA111", "Fiesta", "Ford", 0, 40, 5, surtidor));
        autos[1] = new Thread(new Auto("BBB222", "Gol", "VW", 0, 35, 5, surtidor));
        autos[2] = new Thread(new Auto("CCC333", "Corsa", "Chevrolet", 0, 38, 5, surtidor));
        autos[3] = new Thread(new Auto("DDD444", "208", "Peugeot", 0, 42, 5, surtidor));
        autos[4] = new Thread(new Auto("EEE555", "Sandero", "Renault", 0, 36, 5, surtidor));

        for (Thread t : autos) t.start();
    }
}
