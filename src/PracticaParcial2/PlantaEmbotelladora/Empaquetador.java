package PracticaParcial2.PlantaEmbotelladora;

public class Empaquetador implements Runnable{
    private Planta plan;

    Empaquetador(Planta plan){
        this.plan=plan;
    }

    public void run(){
        try {
            while (true) {
                plan.empaquetar();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
