package TP3;

public class Surtidor {
    private int litros;

    public Surtidor(int litros) {
        this.litros = litros;
    }

    public synchronized boolean cargarCombustible(int cantidad, String patente) {
        if (litros >= cantidad) {
            litros -= cantidad;
            System.out.println("Auto " + patente + " cargó " + cantidad + " litros. Surtidor restante: " + litros + " litros.");
            return true;
        } else {
            System.out.println("Auto " + patente + " quiso cargar, pero el surtidor no tiene suficiente combustible.");
            return false;
        }
    }

    public int getLitros() {
        return litros;
    }
}
