package TP3;

public class Punto4 {
    public static void main(String[] args) {
        //cumple pero podria agregar y modificar vistitante para que le llegue un arreglo de areas y pueda reservar en la que quiera
        Area area= new Area(5);
        Thread visitante1= new Thread(new Visitante("jorge", area));
        Thread visitante2= new Thread(new Visitante("facu", area));
        Thread visitante3= new Thread(new Visitante("diego", area));
        Thread visitante4= new Thread(new Visitante("ian", area));

        visitante1.start();
        visitante2.start();
        visitante3.start();
        visitante4.start();
    }
}
