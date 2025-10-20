package ProblemaSalaMuseo;

public class MuseoMain {
    public static void main(String[] args) throws InterruptedException {
        GestorSala gestor = new GestorSala();
        // Sensor de temperatura
        Thread sensor = new Thread(new SensorTemperatura(gestor));
        sensor.start();

        // Crear hilos de personas y jubilados
        for (int i = 1; i <= 40; i++) {
            new Thread(new Persona(gestor, "Persona-" + i)).start();
            Thread.sleep(500); // Llegan escalonadamente
            new Thread(new Jubilado(gestor, "Jubilado-" + i)).start();
            Thread.sleep(800);
        }

    }
}
