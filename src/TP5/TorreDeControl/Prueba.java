package TP5.TorreDeControl;

public class Prueba {
    public static void main(String[] args) {
        TorreDeControl tor= new TorreDeControl();
        for(int i=0;i<20;i++ ){
            Thread desp=new Thread(new AvionDespegue(tor),"Avion D: "+(i+1));
            Thread ater=new Thread(new AvionAterriza(tor),"Avion A: "+(i+1));
            desp.start();
            ater.start();
        }
    }
}
