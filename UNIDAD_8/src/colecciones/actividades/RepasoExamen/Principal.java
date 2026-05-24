package colecciones.actividades.RepasoExamen;

import colecciones.actividades.RepasoExamen.clases.Administrador;
import colecciones.actividades.RepasoExamen.clases.Comprador;
import colecciones.actividades.RepasoExamen.clases.Producto;
import colecciones.actividades.RepasoExamen.clases.Usuario;
import colecciones.actividades.RepasoExamen.exepciones.excepcionProductoFavorito;
import colecciones.actividades.RepasoExamen.exepciones.excepcionUsuario;
import entrada.Teclado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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

    private static String[] datosUsuarioComun() {
        String correo = Teclado.leerCadena("Correo: ");
        String nombre = Teclado.leerCadena("Nombre: ");
        String apellido1 = Teclado.leerCadena("Apellido1: ");
        String apellido2 = Teclado.leerCadena("Apellido2: ");
        String contraseña = Teclado.leerCadena("Contraseña: ");
        return new String[]{correo, nombre, apellido1, apellido2, contraseña};
    }

    public static void main(String[] args) {
        GestionUsuarios gestion = new GestionUsuarios();
        int opcion;

        do {
            opcion = menu("\n(0) Salir del programa.\n" +
                    "(1) Insertar un administrador\n" +
                    "(2) Insertar un comprador\n" +
                    "(3) Ver todo\n" +
                    "(4) Ver ordenados por clave ascendente\n" +
                    "(5) Ver odenados por clave descendente\n" +
                    "(6) Consultar un usuario por correo\n" +
                    "(7) Actualizar nombre, apellios y contraseña dado el correo\n" +
                    "(8) Borrar usuario por clave\n" +
                    "(9) Validar a un usuario si su contraseña es correcta\n" +
                    "(10) Obtener todos los correos de los compradores que tengan un producto determinado en su lista de favoritos\n" +
                    "(11) Administradores por categoria y comentarios\n" +
                    "(12) Obtener todos los usuarios ordenados por nombre y apellidos\n" +
                    "(13) Obtener lista de favoritos de un usuario\n" +
                    "(14) Obtener producto/s con más favoritos" +
                    "(15) Añadir un producto a la lista de favoritos de un comprador\n" +
                    "(16) Borrar producto favorito", 16);

            switch (opcion) {
                case 1:
                    String[] datosNormales = datosUsuarioComun();
                    String categoriaAdmin = Teclado.leerCadena("Categoria: ");
                    boolean escritosAdmin = Teclado.leerBooleano("Escritos: ");
                    Usuario a = new Administrador(datosNormales[0], datosNormales[1], datosNormales[2], datosNormales[3], datosNormales[4], categoriaAdmin, escritosAdmin);
                    if (gestion.insertarUsuario(a)) {
                        System.out.println("añadido");
                    } else {
                        System.out.println("no añadido, ya existe con ese correoActualizar");
                    }
                    break;
                case 2:
                    String[] datosNormales2 = datosUsuarioComun();
                    String tarjetapago = Teclado.leerCadena("Tarjeta: ");
                    String direccionEnvio = Teclado.leerCadena("Direccion: ");
                    Usuario u = new Comprador(datosNormales2[0], datosNormales2[1], datosNormales2[2], datosNormales2[3], datosNormales2[4], tarjetapago, direccionEnvio);
                    if (gestion.insertarUsuario(u)) {
                        System.out.println("añadido");
                    } else {
                        System.out.println("no añadido, ya existe con ese correoActualizar");
                    }
                    break;
                case 3:
                    System.out.println(gestion);
                    break;
                case 4:
                    System.out.println(gestion.ordenarPorClave());
                    break;
                case 5:
                    System.out.println(gestion.ordenPorClaveDescendente());
                    break;
                case 6:
                    String correo = Teclado.leerCadena("Correo: ");
                    Usuario consultarCorreo = gestion.consultarPorCorreo(correo);
                    if (consultarCorreo != null) {
                        System.out.println(consultarCorreo);
                    } else {
                        System.out.println("no existe");
                    }
                    break;
                case 7:
                    String correoActualizar = Teclado.leerCadena("Correo: ");
                    Usuario actualizarCorreo = gestion.consultarPorCorreo(correoActualizar);
                    if (actualizarCorreo != null) {
                        String nombreNuevo = Teclado.leerCadena("Nuevo nombre: ");
                        String apellido1Nuevo = Teclado.leerCadena("Nuevo apellido1: ");
                        String apellido2Nuevo = Teclado.leerCadena("Nuevo apellido2: ");
                        String contraseñaNueva = Teclado.leerCadena("Nueva contraseña: ");
                        gestion.actualizarPorCorreo(actualizarCorreo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, contraseñaNueva);
                        System.out.println("actualizado");
                    } else {
                        System.out.println("no existe");
                    }
                    break;
                case 8:
                    String correoBorrar = Teclado.leerCadena("Correo: ");
                    if (!gestion.borrarPorCorreo(correoBorrar)) {
                        System.out.println("Error: no existe");
                    } else {
                        System.out.println("borrado.");
                    }
                    break;
                case 10:
                    int codigoProductoCorreos = Teclado.leerEntero("Codigo: ");
                    LinkedList<String> correosFavoritos = gestion.obtenerCorreosFavoritos(codigoProductoCorreos);
                    if (correosFavoritos.isEmpty()) {
                        System.out.println("no hay nadie con ese producto favorito");
                    } else {
                        System.out.println(correosFavoritos);
                    }
                    break;
                case 11:
                    String categoriaAdministradores = Teclado.leerCadena("Categoria: ");
                    LinkedList<Administrador> administradoresCategoria = gestion.obtenerAdministradoresCategoria(categoriaAdministradores);
                    if (administradoresCategoria.isEmpty()) {
                        System.out.println("no hay");
                    } else {
                        System.out.println(administradoresCategoria);
                    }
                    break;
                case 12:
                    ArrayList<Usuario> listaOrdenadaNombreApellidos = gestion.ordenarPorNombreYApellidos();
                    if (listaOrdenadaNombreApellidos.isEmpty()) {
                        System.out.println("no hay");
                    } else {
                        System.out.println(listaOrdenadaNombreApellidos);
                    }
                    break;
                case 13:
                    String correoFavoritos = Teclado.leerCadena("Correo: ");
                    try {
                        ArrayList<Producto> productosFavoritos = gestion.obtenerListaFavoritosComprador(correoFavoritos);
                        if (productosFavoritos.isEmpty()) {
                            System.out.println("no hay");
                        } else {
                            System.out.println(productosFavoritos);
                        }
                    } catch (excepcionUsuario eu) {
                        System.out.println(eu.getMessage());
                    }
                    break;
                case 14:
                    HashSet<Producto> productosMasFavoritos = gestion.obtenerProductoMasFavoritos();
                    if (productosMasFavoritos.isEmpty()) {
                        System.out.println("no hay");
                    } else {
                        System.out.println(productosMasFavoritos);
                    }
                    break;
                case 15:
                    String correoProductoAñadir = Teclado.leerCadena("Correo: ");
                    int codigoProcutoAñadir = Teclado.leerEntero("Codigo: ");
                    String nombreProductoAñadir = Teclado.leerCadena("Nombre: ");
                    double precioProductoAñadir = Teclado.leerReal("Precio: ");
                    try {
                        Producto p = new Producto(codigoProcutoAñadir, nombreProductoAñadir, precioProductoAñadir);
                        gestion.añadirProductoFavorito(correoProductoAñadir, p);
                        System.out.println("añadido");
                    } catch (excepcionUsuario | excepcionProductoFavorito e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 16:
                    String correoProductoBorrar = Teclado.leerCadena("Correo: ");
                    int codigoProductoBorrar = Teclado.leerEntero("Codigo: ");
                    try {
                        gestion.borrarProductoFavorito(correoProductoBorrar, codigoProductoBorrar);
                        System.out.println("borrado");
                    } catch (excepcionUsuario | excepcionProductoFavorito e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("otra opcion porfa");
            }
        } while (opcion != 0);
    }
}
