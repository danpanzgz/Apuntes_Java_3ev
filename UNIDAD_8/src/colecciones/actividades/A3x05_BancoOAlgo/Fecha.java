package colecciones.actividades.A3x05_BancoOAlgo;

public class Fecha {
    private int dia;
    private int mes;
    private int anyo;

    public Fecha(int dia, int mes, int anyo) {
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
    }

    public String toString() {
        return String.format("%02d/%02d/%02d", this.dia, this.mes, this.anyo);
    }

    public int compareTo(Fecha fechaNacimiento) {
        if (this.anyo == fechaNacimiento.anyo) {
            if (this.mes == fechaNacimiento.mes) {
                return this.dia - fechaNacimiento.dia;
            }
            return this.mes - fechaNacimiento.mes;
        }
        return this.anyo - fechaNacimiento.anyo;
    }
}
