package PracticaParcial2.AcrobacionAerea;

public class Persona implements Runnable {
    private Salon salon;
    private String primeraActividad;
    private String segundaActividad;

    public Persona(Salon salon, String primeraActividad, String segundaActividad) {
        this.salon = salon;
        this.primeraActividad = primeraActividad;
        this.segundaActividad = segundaActividad;
    }

    @Override
    public void run() {
        try {
            salon.ingresarSalon();
            salon.elegirPrimeraActividad(primeraActividad);
            Thread.sleep(100); // Simula 30 minutos en la primera actividad
            salon.elegirSegundaActividad(segundaActividad, primeraActividad);
            Thread.sleep(100); // Simula 30 minutos en la segunda actividad
            salon.salirSalon();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
