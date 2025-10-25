package TP5.TrenTuristico;

public class ControlTren implements Runnable {
    private Tren tren;

    ControlTren(Tren tren) {
        this.tren = tren;
    }

    public void run() {
        int i = 0;
        try {
            while (true) {
                System.out.println("viaje numero: " + (i + 1));
                tren.enSalida();
                System.out.println("estoy en salida");

                // permitir que suban hasta 'pasajerosTotales' pasajeros
                tren.permitirSubir();
                System.out.println("di permiso de subir (capacidad completa)");

                tren.enLlegada();
                System.out.println("estoy en llegada, bajan todos y repito");
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
