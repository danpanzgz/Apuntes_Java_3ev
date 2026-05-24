package ejemplos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccesoEmpleado {

	private static final String NOMBRE_FICHERO_EMPLEADOS = "directorio/empleados.dat";
	
	
	/**
	 * Lectura de fichero de empleados
	 * @return
	 */
	public static List<Empleado> lecturaFicheroEmpleados() {
		ObjectInputStream flujoEntrada = null;
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		try {
			// Lectura fichero binario de objetos
			flujoEntrada = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO_EMPLEADOS));
			while (true) {			
				Empleado empleado = (Empleado) flujoEntrada.readObject();
				listaEmpleados.add(empleado);				
			}
		}
		catch (EOFException eofe) {
			// Cuando termine el fichero sale por aquí
		}
		catch (ClassNotFoundException fnfe) {                      
			System.out.println("Error leyendo fichero: " + fnfe.getMessage());
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("Error al leer del fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		finally {
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
	
		
	/**
	 * Escribe todos los empleados de una lista desde el principio de un fichero binario.
	 * @param listaEmpleados
	 */
	public static void escrituraFicheroEmpleados(List<Empleado> listaEmpleados){
		ObjectOutputStream flujoSalida = null;
		try {
			FileOutputStream escritorFichero = new FileOutputStream(NOMBRE_FICHERO_EMPLEADOS);
			flujoSalida = new ObjectOutputStream(escritorFichero);
			for (Empleado empleado : listaEmpleados) {
				flujoSalida.writeObject(empleado);
			}
		}
		catch (IOException ioe) {
			System.out.println("Error al escribir fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		finally {
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
	
	public static void main(String[] args) {
		
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		Empleado empleado1 = new Empleado(1, 1, "Óscar", "12/02/2012", 1200);
		listaEmpleados.add(empleado1);
		Empleado empleado2 = new Empleado(2, 1, "Pedro", "12/02/2012", 1200);
		listaEmpleados.add(empleado2);
		Empleado empleado3 = new Empleado(3, 1, "Mariano", "12/02/2012", 1200);
		listaEmpleados.add(empleado3);
		Empleado empleado4 = new Empleado(4, 1, "María", "12/02/2012", 1200);
		listaEmpleados.add(empleado4);
		
		escrituraFicheroEmpleados(listaEmpleados);
		List<Empleado> lista = lecturaFicheroEmpleados();
		System.out.println(lista);
	}
	
}
