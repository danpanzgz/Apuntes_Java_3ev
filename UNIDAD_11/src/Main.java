//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class Main {
//    public static void main(String[] args) {
//        // concetar a base de datos
//        Class.forName("paquete.driver.nombre");
//
//        // sql lite
//        "org.sqlite.JDBC"
//        // mysql
//        "com.mysql.cj.jdbc.Driver"
//
//        /////////////////////////////
//        // especificar url de la base de datos
//        //sqlite que es un fichero local
//        String url = "jdbc:sqlite:biblioteca.db";
//        //mysql que es servidor
//        String url2 = "jdbc:mysql://localhost:3306/biblioteca";
//
//        // creamos conection
//        Connection conexion = DriverManager.getConnection(url);
//
//        // creamos sentencias
//        Statement sentencia = conexion.createStatement();
//
//        // ejecutamos sentencias select
//        ResultSet resultado = sentencia.executeQuery("SELECT * FROM clientes");
//        // ejecutamos sentencia que devuelve numero de filas
//        int filas = sentencia.executeUpdate("DELETE * FROM clientes");
//
//        // ver datos de ResultSet
//        while (resultado.next()) {
//            String nombre = resultado.getString("NOMBRE COLUMNA");
//            int edad = resultado.getInt("NOMBRE COLUMNA"); // <---- Si retribuimos un int pus getInt
//        }
//    }
//}