package actividades.A1x04;

import  java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

import entrada.Teclado;

// Con ficheros binarios
public class Actividad1x04_FicherosBinarios {

	public static void main(String[] args) {
		String ruta = Teclado.leerCadena("Introduzca la ruta del fichero: ");
		
		TreeMap<Integer, Empleado> empleados = lecturaFicheroEmpleados(ruta);
		
		int opcion;
		do {
			opcion = menu();
			if (opcion == 1) {
				int codigo = Teclado.leerEntero("¿Código? ");
				if (empleados.get(codigo) != null) {
					System.err.println("\nYa existe otro empleado con ese código en el fichero binario.");
				} else {
					Empleado empleado = crearEmpleado(codigo);
					
					empleados.put(codigo, empleado);
					escrituraFicheroEmpleados(empleados, ruta); // Modificar fichero
					
					System.out.println("Se ha insertado un empleado en el fichero binario.");
				}
			} else if (opcion == 2) {
				
				if (empleados.isEmpty()) {
					System.err.println("El fichero binario está vacío.");
				} else {
					System.out.println(consultar(empleados));
				}
				
			} else if (opcion == 3) {
				
				int codigo = Teclado.leerEntero("¿Código? ");
				Empleado empleado = empleados.get(codigo);
				
				if (empleado == null) {
					System.err.println("\nNo existe ningún empleado con ese código en el fichero binario.");
				} else {
					System.out.println("\n" + empleado.toString());
				}
				
			} else if (opcion == 4) {
				int codigo = Teclado.leerEntero("¿Código? ");
				
				if (empleados.get(codigo) == null) {
					System.err.println("\nNo existe ningún empleado con ese código en el fichero binario.\n");
				} else {
					Empleado empleado = empleados.get(codigo);
					
					if (empleado == null) {
						System.err.println("\nNo existe ningún empleado con ese código en el fichero binario.");
					} else {
						empleado = modificarEmpleado(codigo, empleado.getNombre(), empleado.getFechaNacimiento());					
						empleados.put(codigo, empleado);
						escrituraFicheroEmpleados(empleados, ruta); // Modificar fichero
						System.out.println("\nSe ha actualizado un empleado del fichero binario.");
					}
				}
			} else if (opcion == 5) {
				
				int codigo = Teclado.leerEntero("¿Código? ");
				Empleado empleado = empleados.get(codigo);
				
				if (empleado == null) {
					System.err.println("\nNo existe ningún empleado con ese código en el fichero binario.");
				} else {
					empleados.remove(codigo);
					escrituraFicheroEmpleados(empleados, ruta); // Modificar fichero
					System.out.println("\nSe ha eliminado un empleado del fichero binario.");
				}
				
			} else {
				if (opcion != 0) {
					System.err.println("La opción de menú debe estar comprendida entre 0 y 5");
				}
			}
		} while (opcion != 0);
	}
	
	public static Empleado crearEmpleado(int codigo) {
		String nombre = Teclado.leerCadena("¿Nombre? ");
		String fechaNacimiento = Teclado.leerCadena("¿Fecha de nacimiento? ");
		String dpto = Teclado.leerCadena("¿Departamento? ");
		double salario = Teclado.leerReal("¿Salario? ");
		System.out.println();
		
		return new Empleado(codigo, nombre, fechaNacimiento, dpto, salario);
	}
	
	public static Empleado modificarEmpleado(int codigo, String nombre, String fechaNacimiento) {
		String dpto = Teclado.leerCadena("¿Departamento? ");
		double salario = Teclado.leerReal("¿Salario? ");
		System.out.println();
		
		return new Empleado(codigo, nombre, fechaNacimiento, dpto, salario);
	}
	
	public static void escrituraFicheroEmpleados(TreeMap<Integer, Empleado> empleados, String ruta) {
		ObjectOutputStream flujoSalida = null;
		try {
			FileOutputStream escritorFichero = new FileOutputStream(ruta);
			flujoSalida = new ObjectOutputStream(escritorFichero);
			for (Empleado empleado : empleados.values()) {
				flujoSalida.writeObject(empleado);
			}
		} catch (IOException ioe) {
			System.out.println("Error al escribir fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			if (flujoSalida != null) {
				try {
					flujoSalida.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static TreeMap<Integer, Empleado> lecturaFicheroEmpleados(String ruta) {
		ObjectInputStream flujoEntrada = null;
		TreeMap<Integer, Empleado> listaEmpleados = new TreeMap<Integer, Empleado>();
		
		File fichero = new File(ruta);
		if (!fichero.exists()) {
			return listaEmpleados;
		}
		
		try {
			flujoEntrada = new ObjectInputStream(new FileInputStream(ruta));
			boolean terminado = true;
			while (terminado) {	
				try {
					Empleado empleado = (Empleado) flujoEntrada.readObject();
					listaEmpleados.put((Integer) empleado.getCodigo(), empleado);				
				} catch (EOFException eofe) {
					terminado = !terminado;
				}
			}
		} catch (ClassNotFoundException fnfe) {                      
			System.out.println("Error leyendo fichero: " + fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error al leer del fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			if (flujoEntrada != null) {
				try {
					flujoEntrada.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listaEmpleados;
	}
	
	private static String consultar(TreeMap<Integer, Empleado> empleados) {
		String devolver = "";
		
		int contador = 1;
		for (Empleado empleado : empleados.values()) {
			devolver += empleado.toString() + "\n\n";
			contador++;
		}
		devolver += "Se han consultado " + contador + " empleados del fichero binario.";
		
		return devolver;
	}
	
	private static int menu() {
		// Imprimir opciones del menú
		System.out.println("\n0) Salir del programa.");
		System.out.println("1) Insertar un empleado en el fichero");
		System.out.println("2) Consultar todos los empleados del fichero binario");
		System.out.println("3) Consultar un empleado, por código, del fichero binario");
		System.out.println("4) Actualizar un empleado, por código, del fichero binario");
		System.out.println("5) Eliminar un empleado, por código, del fichero binario");
		
		// Escoger opción
		int opcion = Teclado.leerEntero("¿Opción? ");
		
		// Espacio visual
		System.out.println();
		return opcion;
	}
}