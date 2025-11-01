package TP5.Babuinos;

public class PruebaBabuinos {
    public static void main(String[] args) {
        Cuerda cu = new Cuerda();
        for (int i = 0; i < 20; i++) {
            Thread babuinoA = new Thread(new BabuinoA(cu), "BabuinoA " + (i + 1));
            babuinoA.start();
        }

        for (int i = 0; i < 20; i++) {
            Thread babuinoB = new Thread(new BabuinoB(cu), "BabuinoB " + (i + 1));
            babuinoB.start();
        }
        System.out.println(cu.getcruzoA());
    }
}
