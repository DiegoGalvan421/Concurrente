package PracticaParcial2.AcrobacionAerea;

public class Prueba {
    public static void main(String[] args) {
        Salon salon = new Salon();

        Thread[] personas = new Thread[24];
        for (int i = 0; i < 24; i++) {
            String primeraActividad;
            String segundaActividad;

            if (i % 3 == 0) {
                primeraActividad = "tela";
                segundaActividad = "yoga";
            } else if (i % 3 == 1) {
                primeraActividad = "yoga";
                segundaActividad = "lyra";

            } else {
                primeraActividad = "lyra";
                segundaActividad = "tela";
            }

            personas[i] = new Thread(new Persona(salon, primeraActividad, segundaActividad), "Persona " + (i + 1));
        }
        for (Thread persona : personas) {
            persona.start();
        }
    }
}