package actividades.A1x04;

import java.io.Serializable;

public class Empleado implements Serializable, Comparable<Empleado> {
	//
	private static final long serialVersionUID = 2L;
	private static final String SEPARADOR = ";";
	//
	private int codigo;
	private String nombre;
	private String fechaNacimiento;
	private String dpto;
	private double salario;
	
	// Fichero binario
	public Empleado(int codigo, String nombre, String fechaNacimiento, String dpto, double salario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.dpto = dpto;
		this.salario = salario;
	}
	
	// Fichero de texto
	public Empleado(String linea) {
		String[] datos = linea.split(SEPARADOR);
		this.codigo = Integer.parseInt(datos[0]);
		this.nombre = datos[1];
		this.fechaNacimiento = datos[2];
		this.dpto = datos[3];
		this.salario = Double.parseDouble(datos[4]);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [Código: " + codigo + ", Nombre: " + nombre + ",\n\t" +
				"Fecha de Nacimiento: " + fechaNacimiento + ",\n\t" +
				"Departamento: " + dpto + ", Salario: " + String.format("%.2f", salario) + "]";
	}

	public String toStringWithSeparators() {
		return 
			this.codigo + SEPARADOR + 
			this.nombre + SEPARADOR + 
			this.fechaNacimiento + SEPARADOR +
			this.dpto + SEPARADOR +
			String.format("%.2f", this.salario).replace(',', '.');
	}
	
	@Override
	public int compareTo(Empleado o) {
		return this.codigo - o.codigo;
	}
	
}