package TP6.Observatorio;

public class PruebaObs {
    public static void main(String[] args) {
        Observatorio obs = new Observatorio();
        for (int i = 0; i < 200; i++) {
            Thread vis = new Thread(new Visitante(obs, i % 10 == 0), "visitante " + i);
            vis.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread Invest = new Thread(new Investigador(obs), "Investigador " + i);
            Invest.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread mant = new Thread(new Mantenimiento(obs), "mantenimiento " + i);
            mant.start();
        }

    }
}
