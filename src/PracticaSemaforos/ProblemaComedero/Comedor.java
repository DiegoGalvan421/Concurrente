package PracticaSemaforos.ProblemaComedero;

import java.util.concurrent.Semaphore;

public class Comedor {

    private final Semaphore mutex = new Semaphore(1, true);
    private final Semaphore comederos;          
    private final Semaphore esperarPerros = new Semaphore(0, true);
    private final Semaphore esperarGatos = new Semaphore(0, true);

    private int perrosDentro = 0;
    private int gatosDentro = 0;
    private int perrosEsperando = 0;
    private int gatosEsperando = 0;

    // null = nadie comió aún → el primero que llega decide
    private Boolean turnoPerros = null;

    public Comedor(int capacidad) {
        this.comederos = new Semaphore(capacidad, true);
    }

    // =================== PERROS ===================

    public void entrarPerro() throws InterruptedException {
        mutex.acquire();
        perrosEsperando++;
        System.out.println(" (PERRO) llega. EsperandoP=" + perrosEsperando + 
                           " DentroP=" + perrosDentro + " DentroG=" + gatosDentro);

        // Caso base: el primer animal decide el turno
        if (turnoPerros == null && perrosDentro == 0 && gatosDentro == 0) {
            turnoPerros = true;
            System.out.println("→ Primer animal es PERRO → turnoPerros = true");
        }

        while ((gatosDentro > 0) || (turnoPerros != null && turnoPerros == false)) {
            System.out.println(" (PERRO) bloqueado. TurnoPerros=" + turnoPerros + 
                               " GatosDentro=" + gatosDentro);
            mutex.release();
            esperarPerros.acquire();
            mutex.acquire();
            System.out.println(" (PERRO) reintentando entrar");
        }

        perrosEsperando--;
        perrosDentro++;
        System.out.println(" (PERRO) ENTRA. DentroP=" + perrosDentro);

        mutex.release();
        comederos.acquire();
    }

    public void salirPerro() throws InterruptedException {
        mutex.acquire();
        perrosDentro--;
        System.out.println(" (PERRO) SALE. DentroP=" + perrosDentro);

        comederos.release();

        // Si no quedan perros, permitir gatos
        if (perrosDentro == 0 && gatosEsperando > 0) {
            turnoPerros = false;
            System.out.println("→ Cambiamos turno: ahora turnoPerros = false");
            System.out.println(" Despertando " + gatosEsperando + " gatos");
            esperarGatos.release(gatosEsperando);
        }

        mutex.release();
    }

    // =================== GATOS ===================

    public void entrarGato() throws InterruptedException {
        mutex.acquire();
        gatosEsperando++;
        System.out.println(" (GATO) llega. EsperandoG=" + gatosEsperando + 
                           " DentroP=" + perrosDentro + " DentroG=" + gatosDentro);

        // Caso base: primer animal
        if (turnoPerros == null && perrosDentro == 0 && gatosDentro == 0) {
            turnoPerros = false;
            System.out.println(" Primer animal es GATO → turnoPerros = false");
        }

        while ((perrosDentro > 0) || (turnoPerros != null && turnoPerros == true)) {
            System.out.println(" (GATO) bloqueado. TurnoPerros=" + turnoPerros + 
                               " PerrosDentro=" + perrosDentro);
            mutex.release();
            esperarGatos.acquire();
            mutex.acquire();
            System.out.println(" (GATO) reintentando entrar");
        }

        gatosEsperando--;
        gatosDentro++;
        System.out.println(" (GATO) ENTRA. DentroG=" + gatosDentro);

        mutex.release();
        comederos.acquire();
    }

    public void salirGato() throws InterruptedException {
        mutex.acquire();
        gatosDentro--;
        System.out.println(" (GATO) SALE. DentroG=" + gatosDentro);

        comederos.release();

        // Si no quedan gatos, permitir perros
        if (gatosDentro == 0 && perrosEsperando > 0) {
            turnoPerros = true;
            System.out.println(" Cambiamos turno: ahora turnoPerros = true");
            System.out.println(" Despertando " + perrosEsperando + " perros");
            esperarPerros.release(perrosEsperando);
        }

        mutex.release();
    }
}