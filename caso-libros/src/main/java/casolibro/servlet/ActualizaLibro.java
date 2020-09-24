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
 * Servlet que recibe peticiones de actualización de datos de libros
 * 
 * @author Carlos Cevallos
 */
@Transactional
@WebServlet("/actualizaLibro")
public class ActualizaLibro extends HttpServlet {
	/**
	 * Versión de la clase
	 */
	private static final long serialVersionUID = 1194864013998587624L;

	/**
	 * EntityManager utilizado para persistir la información en la base de datos
	 */
	@PersistenceContext(unitName = "casolibroPU")
	EntityManager em;

	/**
	 * Recibe peticiones mediante el método HTTP Get. Permite hacer pruebas de uso
	 * desde un navegador, pasando datos por la URL. Actualiza los datos de un nuevo libro.
	 * Requiere los parámetros de request: id, titulo, autor y resumen.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * Recibe peticiones mediante el método HTTP POST. Actualiza los datos de un nuevo libro.
	 * Requiere los parámetros de request: id, titulo, autor y resumen.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id = new Integer(idStr);

		Libro l = em.find(Libro.class, id);

		l.setTitulo(req.getParameter("titulo"));
		l.setAutor(req.getParameter("autor"));
		l.setResumen(req.getParameter("resumen"));
		em.persist(l);

		Transaccion t = new Transaccion();
		t.setFecha(new Date());
		t.setTipo("actualiza");
		em.persist(t);

		OutputStream os = resp.getOutputStream();
		os.write("Libro actualizado".getBytes());
		os.flush();
	}
}
