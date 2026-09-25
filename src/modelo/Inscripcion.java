package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
    }

    public void confirmarInscripcion() {
        this.estado = "Confirmada";
        this.ticket = new TicketDeAcceso("TICK-" + (int)(Math.random() * 9000 + 1000));
    }

    public Estudiante getEstudiante() { return estudiante; }
    public String getEstado() { return estado; }
    public TicketDeAcceso getTicket() { return ticket; }

    public class TicketDeAcceso implements Serializable {
        private String id;
        private LocalDate fechaEmision;

        public TicketDeAcceso(String id) {
            this.id = id;
            this.fechaEmision = LocalDate.now();
        }

        public void enviarTicket() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("  -> Enviando ticket " + id + " a " + estudiante.getNombre());
        }

        public String getId() { return id; }
    }
}