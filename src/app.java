import certificacion.Certificable;
import excepciones.CupoExcedidoException;
import hilos.EnvioTicketsThread;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.Sala;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== TP2 - EJERCICIO 1 ===");
        
        Estudiante est1 = new Estudiante("50201", "Ana");
        Estudiante est2 = new Estudiante("50202", "Juan");
        Estudiante est3 = new Estudiante("50203", "Pedro");

        Sala salaPrincipal = new Sala(1, "Auditorio Principal");
        EventoUniversitario evento = new EventoUniversitario("EVT-001", "Semana de la Ingeniería", 1000.0, false);
        evento.asignarSala(salaPrincipal);

        Charla charlaIA = new Charla("Introducción a IA", 2, "Dr. Pérez");
        Taller tallerRobotica = new Taller("Taller de Robótica", 15, true);
        Curso cursoJava = new Curso("Curso de Java Avanzado", 20, 2);

        evento.agregarActividad(charlaIA);
        evento.agregarActividad(tallerRobotica);
        evento.agregarActividad(cursoJava);

        evento.mostrarDatos();

        System.out.println("\n--- Caso exitoso: inscripciones dentro del cupo ---");
        try {
            charlaIA.inscribir(est1);
            charlaIA.inscribir(est2);
            System.out.println("Ana y Juan inscriptos correctamente.");
        } catch (CupoExcedidoException e) {
            System.err.println(e.getMessage());
        }

        // Persistencia
        boolean guardado = evento.persistirEvento();
        System.out.println("Evento guardado: " + guardado);
        
        EventoUniversitario rec = EventoUniversitario.recuperarEvento("EVT-001");
        if (rec != null) {
            System.out.println("Evento recuperado correctamente.");
        }

        System.out.println("\nRealizando limpieza...");
        Utilidades.limpiarArchivoPersistencia("EVT-001");

        System.out.println("\n--- Caso fallido: cupo excedido ---");
        try {
            charlaIA.inscribir(est3);
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== EJERCICIO 2 & 3: CERTIFICACIONES Y GENERICS ===");
        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
        System.out.println("Charlas encontradas: " + charlas.size());

        System.out.println("\nEmisión de Certificados:");
        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable certificable) {
                for (Inscripcion ins : act.getInscripciones()) {
                    System.out.println(certificable.generarCertificado(ins.getEstudiante()));
                }
            }
        }

        System.out.println("\n=== EJERCICIO 4: HILOS Y CLASES ANIDADAS ===");
        for (Inscripcion ins : charlaIA.getInscripciones()) {
            ins.confirmarInscripcion();
        }

        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento);
        hiloEnvio.start();

        System.out.println("[" + Thread.currentThread().getName() + "] El hilo principal continúa la ejecución...");
        try {
            hiloEnvio.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}