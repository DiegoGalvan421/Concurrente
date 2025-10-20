package ProblemaSalaMuseo;

class Jubilado implements Runnable {
    private final GestorSala gestor;
    private final String nombre;

    public Jubilado(GestorSala g, String nombre) {
        this.gestor = g;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " (jubilado) intenta entrar...");
            gestor.entrarSalaJubilado();
            System.out.println(nombre + " (jubilado) ha entrado.");
            Thread.sleep(1000 + (int)(Math.random() * 1000));
            gestor.salirSala();
            System.out.println(nombre + " (jubilado) ha salido.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}