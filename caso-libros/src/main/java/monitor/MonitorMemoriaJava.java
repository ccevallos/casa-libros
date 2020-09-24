package monitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que permite realizar el monitoreo del uso de memoria en la Java
 * Virtual Machine. Se ejecuta en cuanto se carga la aplicación. Los datos se
 * almacenan en la carpeta en donde está instalado WildFly.
 * 
 * @author Carlos Cevallos
 */
@WebServlet(name = "monitor", urlPatterns = "/monitor", loadOnStartup = 1)
public class MonitorMemoriaJava extends HttpServlet {
	/**
	 * Versión de la clase
	 */
	private static final long serialVersionUID = 6676903170503182294L;

	/**
	 * Es una bandera que permite inactivar el monitor cuando se accede a la servlet
	 * por método HTTP get con el parámetro fin.
	 */
	private boolean continua = true;

	/**
	 * Corresponde a la periodicidad con la que se realizará el monitoreo. Se
	 * inicializa con 10 segundos.
	 */
	private int periodicidad = 10000;

	/**
	 * Constante que indica la ubicación en donde se almacenará el archivo.
	 */
	private final String CARPETA_ARCHIVO = "../";

	/**
	 * Constante que indica el nombre base del archivo.
	 */
	private final String NOMBRE_ARCHIVO = "monitoreo-memoria.data";

	@Override
	public void init() throws ServletException {
		super.init();
		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmmss-");
		String fechaNombreArchivoStr = sdf.format(fecha);

		File archivo = new File(CARPETA_ARCHIVO + fechaNombreArchivoStr + NOMBRE_ARCHIVO);

		// Crea cabeceras en el archivo.
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			PrintWriter pw = new PrintWriter(fos);
			pw.print("Fecha,MemoriaUsadaJVM,MemoriaLibreJVM");
			pw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Crea un nuevo hilo de ejecución para que no interfiera con la atención de la
		// servlet.
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (continua) {
					try {
						// Obtiene el objeto de ejecución
						Runtime rt = Runtime.getRuntime();
						// Obtiene la memoria libre y calcula la utilizada.
						// Los datos están en bytes.
						long freeMemory = rt.freeMemory();
						long usedMB = rt.totalMemory() - freeMemory;

						// Se abre el archivo para añadir contenido al final
						FileOutputStream fos = new FileOutputStream(archivo, true);
						PrintWriter pw = new PrintWriter(fos);

						// Se obtiene y formatea la fecha y hora
						Date fecha = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String fechaStr = sdf.format(fecha);

						// Se guardan los datos en el archivo
						pw.print("\n" + fechaStr + "," + usedMB + "," + freeMemory);
						pw.close();
						fos.close();
						Thread.sleep(periodicidad);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		});

		th.start();
	}

	/**
	 * Recibe peticiones mediante el método HTTP Get. Permite finalizar la ejecución
	 * del monitoreo si se recibe el parámetro fin.
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		if (req.getParameter("fin") != null) {
			continua = false;
		}
	}

}
