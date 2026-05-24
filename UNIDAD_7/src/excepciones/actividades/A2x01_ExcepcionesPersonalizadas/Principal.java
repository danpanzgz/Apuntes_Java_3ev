package excepciones.actividades.A2x01_ExcepcionesPersonalizadas;

import entrada.Teclado;
import excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones.ExcepcionClientela;
import excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones.ExcepcionUsuario;

public class Principal {
    public static int menu(String mensaje, int numOpciones) {
        int opcion;
        do {
            System.out.println(mensaje);

            opcion = Teclado.leerEntero("Opción: ");
            if (opcion < 0 || opcion > numOpciones) {
                System.out.println("la opcion debe estar entre 0 y " + numOpciones);
            }
        } while (opcion < 0 || opcion > numOpciones);
        return opcion;
    }

    public static void main(String[] args) {
        Clientela clientela = new Clientela(100);
        int opcion;
        do {
            opcion = menu("\n(0) Salir del programa.\n" +
                    "(1) Insertar un usuario en la clientela.\n" +
                    "(2) Consultar todos los usuarios de la clientela.\n" +
                    "(3) Eliminar un usuario, por posición, de la clientela.\n", 3);
            switch (opcion) {
                case 1:
                    String nombre = Teclado.leerCadena("Nombre: ");
                    String contraseña = Teclado.leerCadena("Contraseña: ");
                    int numPuntos = Teclado.leerEntero("Número de puntos: ");
                    try {
                        Usuario usuarioInsertar = new Usuario(nombre, contraseña, numPuntos);
                        clientela.agregar(usuarioInsertar);
                        System.out.println("Usuario insertado con éxito.");
                    } catch (ExcepcionUsuario eu) {
                        System.out.println(eu.getMessage());
                    } catch (ExcepcionClientela ec) {
                        System.out.println(ec.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(clientela);
                    break;
                case 3:
                    int pos = Teclado.leerEntero("Posición: ");
                    try {
                        clientela.eliminarPorPosciion(pos);
                        System.out.println("Usuario eliminado con éxito.");
                    } catch (ExcepcionClientela ec) {
                        System.out.println(ec.getMessage());
                    }
            }
        } while (opcion != 0);
    }
}
