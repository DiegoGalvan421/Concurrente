package TP6.ControlDeAccesoAEstudio;

public class Prueba {
    public static void main(String[] args) {
        MonitorSala mon=new MonitorSala(20);
        for(int i=0;i<100;i++){
            Thread est=new Thread(new Estudiante(mon),"Estudiante "+i);
            est.start();
        }
    }
}
