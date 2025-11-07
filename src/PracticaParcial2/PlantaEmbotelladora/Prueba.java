package PracticaParcial2.PlantaEmbotelladora;

public class Prueba {
    public static void main(String[] args) {
        Planta plan= new Planta();
        Thread em=new Thread(new Empaquetador(plan),"Empaquetador");
        em.start();
        for(int i=0; i<2;i++){
            Thread embo= new Thread(new Embotellador(i+1, plan),"Embotellador "+i);
            embo.start();
        }
    }
}
