package TP6.Pasteleria;

public class Prueba {
    public static void main(String[] args) {
        Mostrador most = new Mostrador(10);
        MesaDeCaja mesa = new MesaDeCaja(20, 3);

        Thread hornoA = new Thread(new Horno(most, 'A'),"Horno A");
        Thread hornoB = new Thread(new Horno(most, 'B'),"Horno B");
        Thread hornoC = new Thread(new Horno(most, 'C'),"Horno C");
        hornoA.start();
        hornoB.start();
        hornoC.start();

        for(int i = 0; i<3;i++){
            Thread robot = new Thread(new Robot(most, mesa),"Robot "+i);
            robot.start();
        }

        Thread brazo = new Thread(new Brazo(mesa),"Brazo");
        brazo.start();
    }
}
