package TP6.ControlDeAccesoAEstudio;

public class Estudiante implements Runnable{
    private MonitorSala mon;

    Estudiante(MonitorSala mon){
        this.mon=mon;
    }

    public void run(){
        try {
            mon.entrarSala();
            System.out.println("Entre a estudiar "+Thread.currentThread().getName());
            Thread.sleep(1000);
            mon.salirSala();
            System.out.println("Termine de estudiar "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
