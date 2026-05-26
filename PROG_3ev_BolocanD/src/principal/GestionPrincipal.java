package principal;
import dao.AccesoBillete;
import dao.AccesoEstacion;
import dao.AccesoFicheroClase;
import dao.AccesoViajero;
import entrada.Teclado;
import excepciones.BDException;
import gui.VentanaViajerosPorEstacion;
import modelo.Estacion;
import modelo.Viajero;

import java.util.List;
import java.util.Scanner;

import static entrada.Teclado.leerEntero;

public class GestionPrincipal {
	private static Scanner sc = new Scanner(System.in);


	/**
	 * Visualiza en consola el menú de opciones del programa principal.
	 */
	public static void escribirMenuOpcionesPrincipal() {
		System.out.println();
		System.out.println("*********** MENÚ PRINCIPAL ***********");
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Actualizar las clases del fichero CSV"); 
		System.out.println("(2) Sacar billete"); 
		System.out.println("(3) Consultar viajeros en estación y día"); 
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion;
		do {
			escribirMenuOpcionesPrincipal();
			opcion = leerEntero("Opción?");
			switch (opcion) {

			case 0:
				break;
				
			// TODO: Exportar las clases a fichero de texto.
			case 1:
				System.out.println("\n*********** ACTUALIZAR LAS CLASES DEL FICHERO ***********");
				// TODO
				String rutaFichero = "data/clases.csv";
				AccesoFicheroClase accesoFichero = new AccesoFicheroClase(rutaFichero);
				try {
					accesoFichero.actualizarFicheroDesdeDB(rutaFichero);
				} catch (BDException e) {
					System.out.println("Error: " + e.getMessage());
				}				System.out.println("Fichero actualizado");
				break;
								
			// TODO: Sacar un billete
			case 2:
				System.out.println("\n*********** SACAR BILLETE ***********");
				int codViajero = leerEntero("¿Código viajero? ");

				AccesoViajero accesoViajero = new AccesoViajero();
				Viajero viajero;
				try {
					viajero = accesoViajero.consultarViajero(codViajero);
				} catch (BDException e) {
					System.out.println("Error al buscar viajero: " + e.getMessage());
					return;
				}

				if (viajero == null) {
					System.out.println("No existe ningún viajero con ese código.");
					return;
				}

				System.out.println(viajero);

				AccesoEstacion accesoEstacion = new AccesoEstacion();
				List<Estacion> estaciones;
				try {
					estaciones = accesoEstacion.consultarEstaciones();
				} catch (BDException e) {
					System.out.println("Error al obtener estaciones: " + e.getMessage());
					return;
				}

				System.out.println("Estaciones:");
				for (Estacion est : estaciones) {
					System.out.println(est);
				}

				int codOrigen, codDestino;
				do {
					codOrigen = leerEntero("¿Código estación de Origen? ");
					codDestino = leerEntero("¿Código estación de Destino? ");
					if (codOrigen == codDestino) {
						System.out.println("Las estaciones de origen y destino no pueden ser iguales.");
					}
				} while (codOrigen == codDestino);

				System.out.print("Introduce la fecha (dd/MM/yyyy): ");
				String fecha = sc.nextLine().trim();

				System.out.print("Introduce la hora de salida (HH:mm): ");
				String horaSalida = sc.nextLine().trim();

				System.out.print("Introduce la hora de llegada (HH:mm): ");
				String horaLlegada = sc.nextLine().trim();

				System.out.print("Introduce el importe: ");
				double importe;
				try {
					importe = Double.parseDouble(sc.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println("Importe no válido.");
					return;
				}

				AccesoBillete accesoBillete = new AccesoBillete();
				try {
					int codBillete = accesoBillete.sacarBillete(
							viajero.getCodigo(), codOrigen, codDestino,
							fecha, horaSalida, horaLlegada, importe
					);
					System.out.println("Se ha sacado un billete con código (" + codBillete + ")");
				} catch (BDException e) {
					System.out.println("Error al sacar el billete para el viajero.");
				}

				break;
						
			// TODO: Consultar viajeros en estación y día
			case 3:
				System.out.println("\n*********** CONSULTAR VIAJEROS EN ESTACIÓN Y DÍA ***********");
				javax.swing.SwingUtilities.invokeLater(() -> new VentanaViajerosPorEstacion());

				break;
						 
			default:
				System.out.println("La opción de menú debe estar comprendida entre 0 y 3.");
			}
		}
		while (opcion != 0);
	}
}
