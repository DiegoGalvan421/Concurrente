package PracticaParcial2.FabricaDeAutos;

public class Prueba {
    public static void main(String[] args) {
        Fabrica fab= new Fabrica(40, 20, 10);
        Thread ruedas = new Thread(new Equipo(0, fab));
        Thread puertas = new Thread(new Equipo(1, fab));
        Thread carrocerias = new Thread(new Equipo(2, fab));
        Thread ensamblador = new Thread(new Equipo(3, fab));
        ensamblador.start();
        ruedas.start();
        puertas.start();
        carrocerias.start();
    }
}
