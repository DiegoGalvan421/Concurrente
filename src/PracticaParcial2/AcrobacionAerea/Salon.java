package PracticaParcial2.AcrobacionAerea;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Salon {
    private int cupoTela = 4;
    private int cupoLyra = 4;
    private int cupoYoga = 4;
    private int capacidadMax = 12;
    private int capacidadAct = 0;

    private ReentrantLock lock = new ReentrantLock(true);
    private Condition actividadLlena = lock.newCondition();
    private Condition turnoCompleto = lock.newCondition();

    public void ingresarSalon() throws InterruptedException {
        lock.lock();
        try {
            while (capacidadAct == capacidadMax) {
                turnoCompleto.await(); // Espera a que haya espacio en el salón
            }
            capacidadAct++;
            System.out
                    .println(Thread.currentThread().getName() + " ingresó al salón. Capacidad actual: " + capacidadAct);
        } finally {
            lock.unlock();
        }
    }

    public void eligioActividad(String actividad, String actividadPrimera) throws InterruptedException {
        lock.lock();
        try {
            boolean asignado = false;
            while (!asignado) {
                switch (actividad) {
                    case "tela":
                        if (cupoTela > 0 && (actividadPrimera == null || !actividadPrimera.equals("tela"))) {
                            cupoTela--;
                            asignado = true;
                            System.out
                                    .println(Thread.currentThread().getName() + " eligió tela. Cupo tela: " + cupoTela);
                        } else {
                            actividad = reasignarActividad(actividadPrimera); // Reasigna si no hay cupos
                        }
                        break;
                    case "lyra":
                        if (cupoLyra > 0 && (actividadPrimera == null || !actividadPrimera.equals("lyra"))) {
                            cupoLyra--;
                            asignado = true;
                            System.out
                                    .println(Thread.currentThread().getName() + " eligió lyra. Cupo lyra: " + cupoLyra);
                        } else {
                            actividad = reasignarActividad(actividadPrimera);
                        }
                        break;
                    case "yoga":
                        if (cupoYoga > 0 && (actividadPrimera == null || !actividadPrimera.equals("yoga"))) {
                            cupoYoga--;
                            asignado = true;
                            System.out
                                    .println(Thread.currentThread().getName() + " eligió yoga. Cupo yoga: " + cupoYoga);
                        } else {
                            actividad = reasignarActividad(actividadPrimera);
                        }
                        break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private String reasignarActividad(String actividadPrimera) {
        if (cupoTela > 0 && !actividadPrimera.equals("tela")) {
            System.out.println(Thread.currentThread().getName() + " fue reasignado a tela.");
            return "tela";
        } else if (cupoLyra > 0 && !actividadPrimera.equals("lyra")) {
            System.out.println(Thread.currentThread().getName() + " fue reasignado a lyra.");
            return "lyra";
        } else if (cupoYoga > 0 && !actividadPrimera.equals("yoga")) {
            System.out.println(Thread.currentThread().getName() + " fue reasignado a yoga.");
            return "yoga";
        } else {
            throw new IllegalStateException("No hay cupos disponibles para reasignar. Esto no debería ocurrir.");
        }
    }

    public void terminoActividad(String actividad) throws InterruptedException {
        lock.lock();
        try {
            switch (actividad) {
                case "tela":
                    cupoTela++;
                    break;
                case "lyra":
                    cupoLyra++;
                    break;
                case "yoga":
                    cupoYoga++;
                    break;
            }
            System.out
                    .println(Thread.currentThread().getName() + " terminó " + actividad + ". Cupo " + actividad + ": " +
                            (actividad.equals("tela") ? cupoTela : actividad.equals("lyra") ? cupoLyra : cupoYoga));
            actividadLlena.signalAll(); // Notifica que se liberaron cupos
        } finally {
            lock.unlock();
        }
    }

    public void elegirPrimeraActividad(String actividad) throws InterruptedException {
        eligioActividad(actividad, null); // No hay actividad previa en la primera elección
        Thread.sleep(100); // Simula 30 minutos en la primera actividad
        terminoActividad(actividad);
    }

    public void elegirSegundaActividad(String actividad, String actividadPrimera) throws InterruptedException {
        eligioActividad(actividad, actividadPrimera); // Pasa la primera actividad para evitar repetirla
        Thread.sleep(100); // Simula 30 minutos en la segunda actividad
        terminoActividad(actividad);
    }

    public void salirSalon() throws InterruptedException {
        lock.lock();
        try {
            capacidadAct--;
            System.out
                    .println(Thread.currentThread().getName() + " salió del salón. Capacidad actual: " + capacidadAct);
            if (capacidadAct == 0) {
                // Reinicia los cupos para el próximo turno
                cupoTela = 4;
                cupoLyra = 4;
                cupoYoga = 4;
                turnoCompleto.signalAll(); // Notifica que el turno terminó
            }
            actividadLlena.signalAll(); // Notifica que se liberaron cupos
        } finally {
            lock.unlock();
        }
    }
}
