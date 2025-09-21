package TP4;

class Empleado implements Runnable {
    private final String nombre;
    private final Confiteria conf;
    private final int rondas; // cuántas veces intentará comer

    public Empleado(String nombre, Confiteria conf, int rondas) {
        this.nombre = nombre;
        this.conf = conf;
        this.rondas = rondas;
    }

    @Override
    public void run() {
        int hechos = 0;
        boolean seguir = true;
        while (seguir && hechos < rondas) {
            if (seguir) {
                boolean sentado = conf.intentarSentarse();
                if (sentado) {
                    try {
                        System.out.printf("[Emp %s] Me senté y aviso pedido%n", nombre);
                        conf.avisarPedido();
                        System.out.printf("[Emp %s] Espero ser servido%n", nombre);
                        conf.esperarServido();
                        System.out.printf("[Emp %s] Como y me voy%n", nombre);
                        hechos++;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        seguir = false;
                    } finally {
                        conf.liberarAsiento();
                    }
                }
            }
        }
    }

}
