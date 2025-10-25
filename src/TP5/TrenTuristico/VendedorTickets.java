package TP5.TrenTuristico;

public class VendedorTickets implements Runnable {
    private Tren tren;

    VendedorTickets(Tren tren) {
        this.tren = tren;
    }

    public void run() {
        try {
            // Esperar pedidos de ticket y venderlos según lleguen
            while (true) {
                tren.esperarPedidoTicket();
                tren.venderTickets();
                System.out.println("Vendi un ticket");
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
