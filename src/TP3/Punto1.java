package TP3;

public class Punto1 {
    public static void main(String[] args) {
        //a) se deberia sincronizar el acceso al hacerR3etiro, para que no se solapen los retiros o no se produzcan
        //simultaneamente y de esa forma evitar que se produzcan inconsistencias
        VerificarCuenta vc = new VerificarCuenta();
        Thread Luis = new Thread(vc, "Luis");
        Thread Manuel = new Thread(vc, "Manuel");
        Luis.start();
        Manuel.start();
    }
}
