package TP6.Pasteleria;

public class Brazo implements Runnable{
    private MesaDeCaja mesa;

    Brazo(MesaDeCaja mesa){
        this.mesa=mesa;
    }

    public void run(){
        while(true){
            mesa.retirarCaja();
            mesa.reponerCaja();
            
        }
    }
}
