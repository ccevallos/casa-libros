package casolibro.bdd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Esta clase permite enviar los scripts de borrado y creación de estructuras de
 * datos del caso Administración de Libros.
 * 
 * @author Carlos Cevallos
 */
public class CreaEstructurasBDD {

	/**
	 * Metodo principal en donde está la lógica de la clase
	 * 
	 * @param args no requiere enviar ningún dato
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// carga el Driver para la conexión a la Base de Datos
		Class.forName("org.postgresql.Driver");
		// ajuste aquí los datos de su conexión
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		System.out.println("Conexion exitosa");

		// Crea las estructuras de datos del caso
		try {
			ejecutaArchivo(con, "scriptBDD/scriptBDDCreaUsuarioBDD.sql");
			System.out.println("1. Usuario creado exitosamente");
		} catch (Exception e) {
			System.out.println("1." + e.getMessage());
		}
		con.close();

		// carga el Driver para la conexión a la Base de Datos
		Class.forName("org.postgresql.Driver");
		
		// ajuste aquí los datos de su conexión
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/caso_libros", "usr_caso_libros",
				"usr");
		System.out.println("Conexion exitosa");

		
		// Crea las estructuras de datos del caso
		try {
			ejecutaArchivo(con, "scriptBDD/scriptBDDCreaEstructuras.sql");
			System.out.println("2. Estructuras creadas exitosamente");
		} catch (Exception e) {
			System.out.println("2." + e.getMessage());
		}
		
		con.close();
	}

	/**
	 * Este método permite leer un archivo y ejecutar las sentencias sql que tenga
	 * en su interior.
	 * 
	 * @param nombreArchivo nombre del archivo con las sentencias sql
	 * @throws Exception
	 */
	public static void ejecutaArchivo(Connection con, String nombreArchivo) throws Exception {
		FileInputStream fis = new FileInputStream(nombreArchivo);
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));

		String sentencia = "";
		String linea = bf.readLine();
		while (linea != null) {
			sentencia += linea;
			linea = bf.readLine();
		}

		Statement stm = con.createStatement();
		stm.execute(sentencia);
		bf.close();
		fis.close();
	}
}
