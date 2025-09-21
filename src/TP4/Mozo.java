package TP4;

class Mozo implements Runnable {
    private final Confiteria conf;

    public Mozo(Confiteria conf) {
        this.conf = conf;
    }

    @Override
    public void run() {
        boolean seguir = true;
        while (seguir) {
            try {
                conf.esperarPedido(); // bloquea hasta que un empleado se siente y avise
                Thread.sleep(500);
                conf.servir(); // habilita a ese empleado a comer
                System.out.println("[Mozo] Serví y quedo a la espera del próximo");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                seguir = false;
            }
        }
    }
}
