package actividades.BDCompleta;

import actividades.BDCompleta.dao.AccesoDepartamento;
import actividades.BDCompleta.dao.AccesoEmpleado;
import actividades.BDCompleta.excepciones.BDException;
import actividades.BDCompleta.excepciones.CSVException;
import actividades.BDCompleta.modelo.Departamento;
import actividades.BDCompleta.modelo.Empleado;
import entrada.Teclado;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static String opcionBD;

    private static int menu(String mensaje, int numOpciones) {
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
        do {
            opcionBD = Teclado.leerCadena("Opción (mysql | sqlite): ");
        } while (!opcionBD.equalsIgnoreCase("mysql") && !opcionBD.equalsIgnoreCase("sqlite"));

        int opcion;
        do {
            opcion = menu("\n(0) Salir del programa.\n" +
                    "-----------------------------------------\n" +
                    "(1) Añadir departamento\n" +
                    "(2) Consultar departamento por código\n" +
                    "(3) Consultar los departamentos ordenados por nombre\n" +
                    "(4) Modificar la ubicación de un departamento por código\n" +
                    "(5) Borrar un departamento por código\n" +
                    "-----------------------------------------\n" +
                    "(6) Insertar empleado\n" +
                    "(7) Consultar empleado por codigo\n" +
                    "(8) Consultar todos los empleados\n" +
                    "(9) Consultar empleados con mayor salario a dado\n" +
                    "(10) Buscar todos los codigos y nombres de empelados que trabajen en el departamento de informática\n" +
                    "(11) Modificar departamento de un empleado\n" +
                    "(12) Borrar empleado por codigo\n" +
                    "(13) Exportar empleados a CSV\n" +
                    "(14) Importar empleados de CSV\n" +
                    "-----------------------------------------", 14);

            try {
                switch (opcion) {
                    case 1:
                        String nombre5 = Teclado.leerCadena("Nombre: ");
                        String ubicacion5 = Teclado.leerCadena("Ubicacion: ");
                        AccesoDepartamento.anadirDepartamento(nombre5, ubicacion5, opcionBD);
                        System.out.println("Departamento añadido");
                        break;
                    case 2:
                        int codigo1 = Teclado.leerEntero("codigo? ");
                        Departamento departamento1 = AccesoDepartamento.consultarDepartamentoCodigo(codigo1, opcionBD);
                        if (departamento1 != null) {
                            System.out.println(departamento1);
                        } else {
                            System.out.println("No existe departamento con codigo " + codigo1);
                        }
                        break;
                    case 3:
                        List<Departamento> listaDepartamentos = AccesoDepartamento.consultarDepartamentosOrdenNombre(opcionBD);
                        if (!listaDepartamentos.isEmpty()) {
                            System.out.println("Departamentos ordenados por nombre:");
                            for (Departamento departamento : listaDepartamentos) {
                                System.out.println(departamento);
                            }
                        } else {
                            System.out.println("No existen departamentos");
                        }
                        break;
                    case 4:
                        int codigo2 = Teclado.leerEntero("codigo? ");
                        String ubicacion2 = Teclado.leerCadena("ubicación? ");
                        boolean modificado = AccesoDepartamento.modificarUbicacionDepartamentoCodigo(codigo2, ubicacion2, opcionBD);
                        System.out.println(modificado ? "Departamento modificado" : "Departamento no modificado");
                        break;
                    case 5:
                        int codigo3 = Teclado.leerEntero("codigo? ");
                        boolean borrado = AccesoDepartamento.borrarDepartamentoCodigo(codigo3, opcionBD);
                        System.out.println(borrado ? "Departamento borrado" : "Departamento no borrado");
                        break;
                    case 6:
                        String nombre8 = Teclado.leerCadena("Nombre : ");
                        String fechaAlta8 = Teclado.leerCadena("Fecha alta: ");
                        float salario8 = (float) Teclado.leerReal("salario: ");
                        System.out.println("Departamentos disponibles:");

                        // muestro los departamentos existentes
                        List<Departamento> listaDepartamentos8 = AccesoDepartamento.consultarDepartamentosOrdenNombre(opcionBD);
                        for (Departamento d8 : listaDepartamentos8) {
                            System.out.println(d8);
                        }

                        // lista con los codigos de los departamentos
                        List<Integer> codigosDepartamentos = new ArrayList<>(listaDepartamentos8.size());
                        for (Departamento departamento : listaDepartamentos8) {
                            codigosDepartamentos.add(departamento.getCodigo());
                        }

                        // le hago escoger el departamento existente
                        int codigoDepartamento8 = Teclado.leerEntero("Codigo del departamento: ");
                        while (!codigosDepartamentos.contains(codigoDepartamento8)) {
                            System.out.println("Codigo no valido");
                            codigoDepartamento8 = Teclado.leerEntero("Codigo del departamento: ");
                        }

                        boolean anadido8 = AccesoEmpleado.anadirEmpleado(nombre8, fechaAlta8, salario8, codigoDepartamento8, opcionBD);
                        System.out.println(anadido8 ? "empleado añadido" : "empleado no añadido");
                        break;
                    case 7:
                        int codigo6 = Teclado.leerEntero("Codigo: ");
                        Empleado empleado6 = AccesoEmpleado.consultarEmpleadoCodigo(codigo6, opcionBD);
                        System.out.println(empleado6 != null ? empleado6 : "no encontrado");
                        break;
                    case 8:
                        List<Empleado> listaEmpleados11 = AccesoEmpleado.consultarEmpleados(opcionBD);
                        if (!listaEmpleados11.isEmpty()) {
                            System.out.println("Empleados:");
                            for (Empleado empleado : listaEmpleados11) {
                                System.out.println(empleado);
                            }
                        } else {
                            System.out.println("No existen empleados");
                        }
                        break;
                    case 9:
                        float salario7 = (float) Teclado.leerReal("salario: ");
                        List<Empleado> listaEmpleados9 = AccesoEmpleado.consultarEmpleadosConMasSalario(salario7, opcionBD);
                        if (!listaEmpleados9.isEmpty()) {
                            System.out.println("Empleados con mayor salario que " + salario7);
                            for (Empleado empleado9 : listaEmpleados9) {
                                System.out.println(empleado9);
                            }
                        } else {
                            System.out.println("No existen empleados");
                        }
                        break;
                    case 10:
                        List<Empleado> listaEmpleados10 = AccesoEmpleado.consultarEmpleadosInformatica(opcionBD);
                        if (!listaEmpleados10.isEmpty()) {
                            System.out.println("Empleados de informática: ");
                            for (Empleado empleado10 : listaEmpleados10) {
                                System.out.println("codigo: " + empleado10.getCodigo() + " nombre: " + empleado10.getNombre());
                            }
                        } else {
                            System.out.println("No existen empleados");
                        }
                        break;
                    case 11:
                        List<Empleado> empleados11 = AccesoEmpleado.consultarEmpleados(opcionBD);
                        System.out.println("Empleados disponibles:");
                        for (Empleado empleado11 : empleados11) {
                            System.out.println("\t" + empleado11);
                        }

                        int codigoEmpleado11 = Teclado.leerEntero("Codigo de empleado a modificar: ");
                        Empleado empleado11 = AccesoEmpleado.consultarEmpleadoCodigo(codigoEmpleado11, opcionBD);
                        if (empleado11 == null) {
                            System.out.println("No existe ese empleado");
                            break;
                        }

                        System.out.println("Departamentos disponibles:");
                        List<Departamento> listaDepartamentos11 = AccesoDepartamento.consultarDepartamentosOrdenNombre(opcionBD);
                        for (Departamento d : listaDepartamentos11) {
                            System.out.println("\t" + d);
                        }

                        boolean modificado11 = false;

                        int codigoDepartamento11 = Teclado.leerEntero("Codigo del departamento: ");
                        try {
                            modificado11 = AccesoEmpleado.modificarDepartamentoCodigo(codigoEmpleado11, codigoDepartamento11, opcionBD);
                        } catch (BDException bde) {
                            boolean crearNuevo11 = Teclado.leerBooleano("No existe ese departamento. Deseas crear uno nuevo? (true | false): ");

                            if (crearNuevo11) {
                                System.out.println("Creando nuevo departamento con código: " + codigoDepartamento11);
                                String nombre11 = Teclado.leerCadena("Nombre nuevo: ");
                                String ubicacion11 = Teclado.leerCadena("Ubicacion nueva: ");

                                AccesoDepartamento.anadirDepartamentoCodigo(codigoDepartamento11, nombre11, ubicacion11, opcionBD);
                                modificado11 = AccesoEmpleado.modificarDepartamentoCodigo(codigoEmpleado11, codigoDepartamento11, opcionBD);
                            }
                        }

                        if (modificado11) {
                            System.out.println("Empleado modificado:");
                        } else {
                            System.out.println("Empleado no modificado:");
                        }
                        break;
                    case 12:
                        int codigo9 = Teclado.leerEntero("codigo? ");
                        boolean borrado9 = AccesoEmpleado.borrarEmpleadoCodigo(codigo9, opcionBD);
                        System.out.println(borrado9 ? "Empleado borrado" : "Empleado no borrado");
                        break;
                    case 13:
                        List<Empleado> empleados12 = AccesoEmpleado.consultarEmpleados(opcionBD);
                        AccesoEmpleado.exportarEmpleadosCSV(empleados12);
                        System.out.println("Archivo exportado en csv/empleados.csv");
                        break;
                    case 14:
                        String ruta14 = Teclado.leerCadena("ruta del fichero: ");
                        boolean fallaronAlgunas14 = AccesoEmpleado.importarEmpleadosCSV(ruta14);
                        if (fallaronAlgunas14) {
                            System.out.println("Algunos empleados no se pudieron insertar ya que su departamento no existe en la base de datos.");
                        }
                        System.out.println("Empleados importados correctamente");
                        break;
                }
            } catch (BDException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (CSVException csve) {
                System.out.println(csve.getMessage());
            }
        } while (opcion != 0);
    }
}
