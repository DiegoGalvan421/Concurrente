package TP5.GestorPiscina;

public class Prueba {
    public static void main(String[] args) {
        GestorPiscina gest = new GestorPiscina(10);

        for(int i=0;i<15;i++){
            Thread visitante = new Thread(new Visitante(gest),"Visitante "+(i+1));
            visitante.start();
        }
    }
}
