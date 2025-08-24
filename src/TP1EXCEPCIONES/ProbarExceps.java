package TP1EXCEPCIONES;
public class ProbarExceps{
    public static void main(String[] args) {
        PruebaExcep prueba = new PruebaExcep();

        // a) Edad
        try {
            prueba.verificarEdad(15); // lanza excepción
        } catch (Exception e) {
            System.out.println("Excepción en edad: " + e.getMessage());
        }

        // b) Ruleta
        try {
            prueba.jugarRuleta(7); // probar con distintos números
        } catch (Exception e) {
            System.out.println("Excepción en ruleta: " + e.getMessage());
        }

        // c) Colección
        prueba.mostrarColeccion();
    }
}
    