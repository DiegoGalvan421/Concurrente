package TP6.PuenteEstrecho.SEMAFOROS;

public class CocheNorte implements Runnable{
    private Puente puen;

    CocheNorte(Puente puen){
        this.puen=puen;
    }

    public void run(){
        try {
            puen.cruzarNorte();
            puen.terminarCruzarNorte();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
