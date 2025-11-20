package PracticaSemaforos.ProblemaComedero;

public class Prueba {
    public static void main(String[] args) {
        Comedor sal = new Comedor(10);
        for(int i=0;i<100;i++){
            Thread an= new Thread(new Animal(sal, i%2),"Animal tipo "+(i%2));
            an.start();
        }
    }
}
