package TP3;

public class Punto3 {
    public static void main(String[] args) {
        // Objetos para sincronizar el acceso a los recursos
        Object plato = new Object();
        Object rueda = new Object();
        Object hamaca = new Object();

        // Crear varios hámster
        for (int i = 1; i <= 3; i++) {
            Thread hamster = new Thread(new Hamster("Hamster-" + i, plato, rueda, hamaca));
            hamster.start();
        }
    }
}
