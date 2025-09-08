package TP3;

class Hamster implements Runnable {
    private String nombre;
    private Object plato, rueda, hamaca;

    public Hamster(String nombre, Object plato, Object rueda, Object hamaca) {
        this.nombre = nombre;
        this.plato = plato;
        this.rueda = rueda;
        this.hamaca = hamaca;
    }

    /*
     * Cada recurso es un objeto usado como candado (synchronized).
     * Cada hámster (hilo) realiza las actividades en orden, esperando su turno para
     * cada recurso.
     */
    @Override
    public void run() {
        try {
            // Comer
            synchronized (plato) {
                System.out.println(nombre + " está comiendo.");
                Thread.sleep(1000);
            }
            // Correr
            synchronized (rueda) {
                System.out.println(nombre + " está corriendo en la rueda.");
                Thread.sleep(1000);
            }
            // Descansar
            synchronized (hamaca) {
                System.out.println(nombre + " está descansando en la hamaca.");
                Thread.sleep(1000);
            }
            System.out.println(nombre + " terminó sus actividades.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
