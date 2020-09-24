package casolibro.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import casolibro.modelo.Libro;
import casolibro.modelo.Transaccion;

/**
 * Servlet que recibe peticiones de registro de un nuevo libro
 * 
 * @author Carlos Cevallos
 */
@Transactional
@WebServlet("/registroLibro")
public class RegistroLibro extends HttpServlet {
	/**
	 * Versión de la clase
	 */
	private static final long serialVersionUID = 1935827743148363511L;

	/**
	 * EntityManager utilizado para persistir la información en la base de datos
	 */
	@PersistenceContext(unitName = "casolibroPU")
	EntityManager em;

	/**
	 * Recibe peticiones mediante el método HTTP Get. Permite hacer pruebas de uso
	 * desde un navegador, pasando datos por la URL. Registra un nuevo libro.
	 * Requiere los parámetros de request: titulo, autor y resumen.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * Recibe peticiones mediante el método HTTP POST. Registra un nuevo libro.
	 * Requiere los parámetros de request: titulo, autor y resumen.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Libro l = new Libro();

		l.setTitulo(req.getParameter("titulo"));
		l.setAutor(req.getParameter("autor"));
		l.setResumen(req.getParameter("resumen"));
		em.persist(l);

		Transaccion t = new Transaccion();
		t.setFecha(new Date());
		t.setTipo("registro");
		t.setIdLibro(l.getId());

		em.persist(t);

		// log.info("Registro de libro exitoso " + l.getId());
		OutputStream os = resp.getOutputStream();
		os.write("Libro registrado".getBytes());
		os.flush();
	}

}
