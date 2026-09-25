import java.io.File;

public class Utilidades {
    public static void limpiarArchivoPersistencia(String id) {
        File f = new File(id + ".dat");
        if (f.exists()) {
            if (f.delete()) {
                System.out.println("Archivo " + id + ".dat eliminado correctamente.");
            }
        }
    }
}