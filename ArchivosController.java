import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArchivosController {
    

    public static String leerArchivo(String nombreArchivo) {
        String linea = null;
        String texto = "";

        try {
            FileReader lectorArchivo = new FileReader(nombreArchivo);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {
                texto += linea + "\n";
            }

            bufferLectura.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

        return texto;
    }

    
}
