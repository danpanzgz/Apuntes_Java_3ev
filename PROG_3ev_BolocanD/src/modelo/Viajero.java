package modelo;

public class Viajero {

	private int codigo;
	private int codigoClase;
	private String nombre;
	private String fechaNacimiento;
	private String lugarResidencia;
	private String correo;
	private int puntos;

	public Viajero(int codigo, int codigoClase, String nombre, String fechaNacimiento,
				   String lugarResidencia, String correo, int puntos) {
		this.codigo = codigo;
		this.codigoClase = codigoClase;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarResidencia = lugarResidencia;
		this.correo = correo;
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return 
			"Viajero [Código = " + this.codigo + ",\n" +
			"         Clase = " + this.codigoClase + ",\n" +
			"         Nombre = " + this.nombre + 
			", FechaNacimiento = " + this.fechaNacimiento + 
			", LugarResidencia = " + this.lugarResidencia + 
			", Correo = " + this.correo + 
			", Puntos = " + this.puntos	+ 
			"]";
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getClase() {
		return this.codigoClase;
	}

	public void setClase(int clase) {
		this.codigoClase = clase;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarResidencia() {
		return this.lugarResidencia;
	}

	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getCodigoClase() { return this.codigoClase; }

	public void setCodigoClase(int codigoClase) { this.codigoClase = codigoClase; }


}
