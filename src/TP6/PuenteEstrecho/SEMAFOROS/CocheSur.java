package TP6.PuenteEstrecho.SEMAFOROS;

public class CocheSur implements Runnable{
    private Puente puen;

    CocheSur(Puente puen){
        this.puen=puen;
    }

    public void run(){
        try {
            puen.cruzarSur();
            puen.terminarCruzarSur();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
