package TP6.PuenteEstrecho.SEMAFOROS;

public class Prueba {
    public static void main(String[] args) {
        Puente puen = new Puente();

        for(int i = 0;i<15;i++){
            Thread sur= new Thread(new CocheSur(puen), "CocheSur "+i);
            Thread norte= new Thread(new CocheNorte(puen), "CocheNorte "+i);
            sur.start();
            norte.start();
        }
    }
}
