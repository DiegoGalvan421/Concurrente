package TP6.Observatorio;

public class Visitante implements Runnable {
    private Observatorio obs;
    private Boolean tieneSilla;

    Visitante(Observatorio obs, Boolean tieneSilla) {
        this.obs = obs;
        this.tieneSilla = tieneSilla;
    }

    public void run() {
        obs.ingresarVis(tieneSilla);
        System.out.println("ingrese al observatorio con silla:" + tieneSilla + " " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
        }
        obs.salirVis(tieneSilla);
        System.out.println("sali del observatoriocon silla:" + tieneSilla + " " + Thread.currentThread().getName());

    }

}
