package modelo;

import modelo.actividades.Actividad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public void asignarSala(Sala sala) { this.sala = sala; }
    public void agregarActividad(Actividad actividad) { this.actividades.add(actividad); }

    public double calcularCostoEstimado() {
        if (gratuito) return 0;
        double total = costoBase;
        for (Actividad act : actividades) {
            total += act.calcularCostoMateriales();
        }
        return total;
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad act : actividades) {
            if (tipo.isInstance(act)) {
                resultado.add(tipo.cast(act));
            }
        }
        return resultado;
    }

    public boolean persistirEvento() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(id + ".dat"))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(id + ".dat"))) {
            return (EventoUniversitario) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void mostrarDatos() {
        System.out.println("Evento [ID: " + id + ", Título: " + titulo + ", Costo: $" + costoBase + ", Gratuito: " + (gratuito ? "Sí" : "No") + ", Sala: " + (sala != null ? sala.getNombre() : "Sin asignar") + "]");
        System.out.println("Costo total estimado: $" + calcularCostoEstimado());
        System.out.println("Actividades:");
        for (Actividad act : actividades) {
            System.out.println("  - #" + act.getId() + " [" + act.getTipo() + "] " + act.getTitulo() + " (cupo máx: " + act.getCupoMaximo() + ", inscriptos: " + act.getInscripciones().size() + ")");
        }
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Actividad> getActividades() { return actividades; }
}