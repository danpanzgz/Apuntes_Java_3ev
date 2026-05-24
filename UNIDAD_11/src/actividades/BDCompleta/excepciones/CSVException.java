package actividades.BDCompleta.excepciones;

public class CSVException extends RuntimeException {
    public static final String ERROR_NO_EXISTE = "El fichero no existe en la ruta especificada";
    public static final String ERROR_ESCRITURA = "Error al escribir el fichero";
    public static final String ERROR_APERTURA = "Error al abrir el fichero";
    public static final String ERROR_CIERRE = "Error al cerrar el fichero";
    public static final String ERROR_PARSE = "Error al convertir de cadena a número";
    public static final String ERROR_NO_EXISTE_DEP = "No existe ese departamento";

    public CSVException(String message) {
        super("Error: " + message);
    }
}
