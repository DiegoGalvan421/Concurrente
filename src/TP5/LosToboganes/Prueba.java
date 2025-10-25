package TP5.LosToboganes;

public class Prueba {
    public static void main(String[] args) {
        Escalera esc = new Escalera(5);
        Thread enc = new Thread(new Encargado(esc));
        enc.start();

        for (int i = 0; i < 10; i++) {
            Thread vis = new Thread(new Visitante(esc), "Visitante: " + (i + 1));
            vis.start();
        }
    }

}
