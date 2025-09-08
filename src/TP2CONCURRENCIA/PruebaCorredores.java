package TP2CONCURRENCIA;

public class PruebaCorredores {
    public static void main(String[] args) {
        /*
         * funcion pero me falta que no entiendo :Inicia todos los hilos creados usando el método
         * start(). Utiliza Thread.sleep() dentro
         * del método run() de cada corredor para simular el tiempo entre pasos.
         * Al finalizar la carrera se desea saber qué corredor hizo la mayor distancia y
         * cual fue
         * esa distancia. ¿Quién será el encargado de mostrar este mensaje? ¿Cómo hará
         * esperar que todo los corredores terminen la carrera?
         */
        Thread[] carrera = new Thread[5];
        carrera[0] = new Thread(new Corredor("jorge"));
        carrera[1] = new Thread(new Corredor("mauro"));
        carrera[2] = new Thread(new Corredor("ian"));
        carrera[3] = new Thread(new Corredor("facu"));
        carrera[4] = new Thread(new Corredor("lucho"));

        carrera[0].start();
        carrera[1].start();
        carrera[2].start();
        carrera[3].start();
        carrera[4].start();
    }
}
