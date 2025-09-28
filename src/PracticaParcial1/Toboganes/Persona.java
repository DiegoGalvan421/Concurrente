package PracticaParcial1.Toboganes;

public class Persona implements Runnable {
    private LosToboganes tobs;

    Persona(LosToboganes nuevo){
        tobs= nuevo;
    }
    public void run() {
    if (tobs.intentarSubir()) {
        System.out.println(Thread.currentThread().getName() + " subió al mirador");
        tobs.esperarPermiso();
        tobs.bajarPorTobogan();
        tobs.liberarMirador();
    } else {
        System.out.println(Thread.currentThread().getName() + " no pude subir al mirador");
    }
}
}
