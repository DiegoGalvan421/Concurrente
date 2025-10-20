package ProblemaSalaMuseo;

class Persona implements Runnable {
    private final GestorSala gestor;
    private final String nombre;

    public Persona(GestorSala g, String nombre) {
        this.gestor = g;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " intenta entrar...");
            gestor.entrarSala();
            System.out.println(nombre + " ha entrado.");
            Thread.sleep(1000 + (int)(Math.random() * 1000)); // Simula permanencia
            gestor.salirSala();
            System.out.println(nombre + " ha salido.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

