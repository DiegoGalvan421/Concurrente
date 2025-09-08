package TP3;

public class Auto extends Vehiculo implements Runnable {
    private int combustible; // litros actuales
    private final int capacidad; // capacidad máxima del tanque
    private final int reserva; // nivel de reserva
    private Surtidor surtidor;

    public Auto(String patente, String modelo, String marca, int km, int capacidad, int reserva, Surtidor surtidor) {
        super(patente, modelo, marca, km);
        this.capacidad = capacidad;
        this.reserva = reserva;
        this.combustible = capacidad; // inicia lleno
        this.surtidor = surtidor;
    }

    @Override
    public void run() {
        while (true) {
            // Simula recorrer entre 5 y 20 km
            int recorrido = (int) (Math.random() * 16) + 5;
            km += recorrido;
            combustible -= recorrido / 10; // 1 litro cada 10 km
            System.out.println(
                    "Auto " + patente + " recorrió " + recorrido + " km. Combustible: " + combustible + " litros.");

            if (combustible <= reserva) {
                int aCargar = capacidad - combustible;
                boolean exito = surtidor.cargarCombustible(aCargar, patente);
                if (exito) {
                    combustible = capacidad;
                } else {
                    System.out.println("Auto " + patente + " se quedó sin combustible.");
                    break;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}
