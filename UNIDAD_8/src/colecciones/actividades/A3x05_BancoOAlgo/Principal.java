package colecciones.actividades.A3x05_BancoOAlgo;

import entrada.Teclado;

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
        Banco banco = new Banco();
        int opcion;

        do {
            opcion = menu("\n(0) Salir del programa.\n" +
                    "(1) Insertar un cliente en el conjunto.\n" +
                    "(2) Eliminar un cliente, por DNI, del conjunto.\n" +
                    "(3) Consultar un cliente, por DNI, del conjunto.\n" +
                    "(4) Consultar todos los clientes del conjunto, en orden por DNI ascendente.\n" +
                    "(5) Consultar todos los clientes del conjunto, en orden por nombre ascendente.\n" +
                    "(6) Consultar todos los clientes del conjunto, en orden por fecha de nacimiento ascendente.\n" +
                    "(7) Cambiar de yahoo a gmail.\n", 7);

            switch (opcion) {
                case 1:
                    String dniInsertar = Teclado.leerCadena("DNI: ");
                    if (banco.estaDNI(dniInsertar)) {
                        System.out.println("Ya existe otro cliente con ese DNI en el conjunto");
                    } else {
                        String nombre = Teclado.leerCadena("Nombre: ");
                        int dia = Teclado.leerEntero("Dia de nacimiento: ");
                        int mes = Teclado.leerEntero("Mes de nacimiento: ");
                        int anyo = Teclado.leerEntero("Anyo de nacimiento: ");
                        Fecha fechaNacimiento = new Fecha(dia, mes, anyo);
                        String domicilio = Teclado.leerCadena("Domicilio: ");
                        String eMail = Teclado.leerCadena("E-mail: ");
                        double saldo = Teclado.leerEntero("Saldo: ");

                        Cliente insertarCliente = new Cliente(dniInsertar, nombre, fechaNacimiento, eMail, domicilio, saldo);
                        banco.insertarCliente(insertarCliente);
                        System.out.println("Se ha insertado el cliente en el conjunto");
                    }
                    break;
                case 2:
                    String dniEliminar = Teclado.leerCadena("DNI: ");
                    if (!banco.eliminarPorDNI(dniEliminar)) {
                        System.out.print("No existe ningún cliente con ese DNI en el conjunto.");
                    } else {
                        System.out.println("Se ha eliminado el cliente del conjunto");
                    }
                    break;
                case 3:
                    String dniConsultar = Teclado.leerCadena("DNI: ");
                    Cliente clienteConsultar = banco.consultarPorDNI(dniConsultar);
                    if (clienteConsultar == null) {
                        System.out.println("No existe ningún cliente con ese DNI en el conjunto");
                    } else {
                        System.out.println(clienteConsultar);
                    }
                    break;
                case 4:
                    Banco consultarDNI = banco.consultarTodosDNIAscendente();
                    if (consultarDNI.estaVacio()) {
                        System.out.println("El conjunto está vacío");
                    } else {
                        System.out.println(consultarDNI);
                    }
                    break;
                case 5:
                    Banco consultarNombre = banco.consultarTodosNombreAscendente();
                    if (consultarNombre.estaVacio()) {
                        System.out.println("El conjunto está vacío");
                    } else {
                        System.out.println(consultarNombre);
                    }
                    break;
                case 6:
                    Banco consultarFecha = banco.consultarTodosNacimientoAscendente();
                    if (consultarFecha.estaVacio()) {
                        System.out.println("El conjunto está vacío");
                    } else {
                        System.out.println(consultarFecha);
                    }
                    break;
                case 7:
                    int afectadosCambioEmail = banco.cambiarClientesYahooGmail();
                    System.out.println("Afectados " + afectadosCambioEmail + " clientes.");
            }
        } while (opcion != 0);
    }
}
