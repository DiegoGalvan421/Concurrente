package PracticaParcial2.PlantaEmbotelladora;

public class Embotellador implements Runnable {
    private Planta plan;
    private int tipo;

    Embotellador(int tipo, Planta plan) {
        this.tipo = tipo;
        this.plan = plan;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Preparo la botella "+Thread.currentThread().getName());
                Thread.sleep(600);
                if (tipo == 1) {
                    plan.embotellarVino();
                } else {
                    plan.embotellarSaborizado();
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
