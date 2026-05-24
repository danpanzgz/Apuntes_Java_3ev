package actividades.A1x04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import entrada.Teclado;

// Con ficheros de texto
public class Actividad1x04_FicherosCSV {

	public static void main(String[] args) {
		String ruta = Teclado.leerCadena("Introduzca la ruta del fichero: ");
		
		TreeMap<Integer, Empleado> empleados = lecturaFicheroEmpleados(ruta);
		int ultimaPosicion = 0;
		if (empleados.size() > 0) {
			ultimaPosicion = empleados.size()-1;
		}
		
		int opcion;
		do {
			opcion = menu();
			if (opcion == 1) {
				int codigo = Teclado.leerEntero("¿Código? ");
				if (empleados.get(codigo) != null) {
					System.err.println("\nYa existe otro empleado con ese código en el fichero de texto.");
				} else {
					Empleado empleado = crearEmpleado(codigo);
					
					empleados.put(codigo, empleado);
					if (ultimaPosicion != 0) {
						ultimaPosicion = modificarFicheroEmpleados(empleados, ruta, ultimaPosicion); // Modificar fichero
						empleados.put(codigo, empleado);
					} else {
						escrituraFicheroEmpleados(empleados, ruta);
						empleados.put(codigo, empleado);
					}
					
					System.out.println("Se ha insertado un empleado en el fichero de texto.");
				}
			} else if (opcion == 2) {
				
				if (empleados.isEmpty()) {
					System.err.println("El fichero de texto está vacío.");
				} else {
					System.out.println(consultar(empleados));
				}
				
			} else if (opcion == 3) {
				
				int codigo = Teclado.leerEntero("¿Código? ");
				Empleado empleado = empleados.get(codigo);
				
				if (empleado == null) {
					System.err.println("\nNo existe ningún empleado con ese código en el fichero de texto.");
				} else {
					System.out.println("\n" + empleado.toString());
				}
				
			} else if (opcion == 4) {
				int codigo = Teclado.leerEntero("¿Código? ");
				if (empleados.get(codigo) == null) {
						System.err.println("\nNo existe ningún empleado con ese código en el fichero de texto.");
				} else {
					Empleado empleado = empleados.get(codigo);
					
					if (empleado == null) {
						System.err.println("\nNo existe ningún empleado con ese código en el fichero de texto.");
					} else {
						empleado = modificarEmpleado(codigo, empleado.getNombre(), empleado.getFechaNacimiento());					
						empleados.put(codigo, empleado);
						escrituraFicheroEmpleados(empleados, ruta); // Modificar fichero
						System.out.println("\nSe ha actualizado un empleado del fichero de texto.");
					}
				}
			} else if (opcion == 5) {
				
				int codigo = Teclado.leerEntero("¿Código? ");
				Empleado empleado = empleados.get(codigo);
				
				if (empleado == null) {
					System.err.println("\nNo existe ningún empleado con ese código en el fichero de texto.");
				} else {
					empleados.remove(codigo);
					escrituraFicheroEmpleados(empleados, ruta); // Sobreescribir fichero
					System.out.println("\nSe ha eliminado un empleado del fichero de texto.");
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
		
		return new Empleado(codigo, nombre, fechaNacimiento, dpto, salario);
	}
	
	public static int modificarFicheroEmpleados(TreeMap<Integer, Empleado> empleados, String ruta, int posicion) {
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new FileWriter(new File(ruta), true));
			ArrayList<Empleado> listaEmpleados = (ArrayList<Empleado>) empleados.values();
			
			for (int i = posicion; i < listaEmpleados.size(); i++) {
				bw.write(listaEmpleados.get(i).toStringWithSeparators());
				bw.newLine();
			}
			bw.close();
			
		} catch (IOException ioe) {
			System.out.println("Error al escribir en el fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return empleados.size()-1;
	}
	
	public static void escrituraFicheroEmpleados(TreeMap<Integer, Empleado> empleados, String ruta) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(ruta), false));
			
			for (Empleado empleado : empleados.values()) {
				bw.write(empleado.toStringWithSeparators());
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException ioe) {
			System.out.println("Error al escribir en el fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
	}
	
	public static TreeMap<Integer, Empleado> lecturaFicheroEmpleados(String ruta) {
		TreeMap<Integer, Empleado> listaEmpleados = new TreeMap<Integer, Empleado>();
		
		BufferedReader br = null;
		File fichero = new File(ruta);
		if (!fichero.exists()) {
			return listaEmpleados;
		}
		
		try {		
			br = new BufferedReader(new FileReader(fichero));
      		String linea = br.readLine(); 
      		
      		while (linea != null) { 	
      			Empleado empleado = new Empleado(linea);
      			listaEmpleados.put((Integer) empleado.getCodigo(), empleado);
      		}
      		
      		br.close();
		} catch (FileNotFoundException fnfe) {                      
			System.out.println("Error al abrir el fichero: " + fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error al leer del fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Error al convertir de cadena a número: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
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
		devolver += "Se han consultado " + contador + " empleados del fichero de texto.";
		
		return devolver;
	}
	
	private static int menu() {
		// Imprimir opciones del menú
		System.out.println("\n0) Salir del programa.");
		System.out.println("1) Insertar un empleado en el fichero");
		System.out.println("2) Consultar todos los empleados del fichero de texto");
		System.out.println("3) Consultar un empleado, por código, del fichero de texto");
		System.out.println("4) Actualizar un empleado, por código, del fichero de texto");
		System.out.println("5) Eliminar un empleado, por código, del fichero de texto");
		
		// Escoger opción
		int opcion = Teclado.leerEntero("¿Opción? ");
		
		// Espacio visual
		System.out.println();
		return opcion;
	}
}