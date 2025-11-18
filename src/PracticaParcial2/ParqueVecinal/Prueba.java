package PracticaParcial2.ParqueVecinal;

public class Prueba {
    public static void main(String[] args) {
        Parque par = new Parque(100);
        for(int i = 0; i<150;i++){
            Thread vis = new Thread(new Visitante(i%3, par),"Visitante "+i);
            vis.start();
            if(i%10==0){
                Thread esc= new Thread(new Escuela(par),"Escuela "+i);
                esc.start();
            }
            
        }
    }
}
