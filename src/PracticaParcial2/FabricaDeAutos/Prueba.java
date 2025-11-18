package PracticaParcial2.FabricaDeAutos;

public class Prueba {
    public static void main(String[] args) {
        Fabrica fab= new Fabrica(40, 20, 10);
        Thread ruedas = new Thread(new Equipo('R', fab));
        Thread puertas = new Thread(new Equipo('P', fab));
        Thread carrocerias = new Thread(new Equipo('C', fab));
        Thread ensamblador = new Thread(new Equipo('A', fab));
        ensamblador.start();
        ruedas.start();
        puertas.start();
        carrocerias.start();
    }
}
