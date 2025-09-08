package TP3;

public class Punto2 {
    public static void main(String[] args) throws InterruptedException {
        Energia energia = new Energia();
        /*Sin sincronyzar el recurso compartido, los valores son aleatorios y no tienen un orden particular
         * mientras que cuando se syncroniza el metodo que accede al recurso compartido, se estabiliza el ingreso y da siempre el mismo resultado
         */
        Thread criaturaOscura = new Thread(new CriaturaOscura(energia), "Hilo-CriaturaOscura");
        Thread sanador = new Thread(new Sanador(energia), "Hilo-Sanador");

        criaturaOscura.start();
        sanador.start();

        criaturaOscura.join();
        sanador.join();

        System.out.println("Energía final: " + energia.getEnergia());
    }
}
