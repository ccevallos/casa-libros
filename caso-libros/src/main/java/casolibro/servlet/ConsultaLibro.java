package casolibro.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import casolibro.modelo.Libro;
import casolibro.modelo.Transaccion;

/**
 * Servlet que recibe peticiones de consulta de libros por titulo
 * 
 * @author Carlos Cevallos
 */
@Transactional
@WebServlet("/consultaLibro")
public class ConsultaLibro extends HttpServlet {
	/**
	 * Versión de la clase
	 */
	private static final long serialVersionUID = -6932417330862918921L;

	/**
	 * EntityManager utilizado para consultar la información de la base de datos
	 */
	@PersistenceContext(unitName = "casolibroPU")
	EntityManager em;

	/**
	 * Recibe peticiones mediante el método HTTP Get. Permite hacer pruebas de uso
	 * desde un navegador, pasando datos por la URL. Consulta los libros por título.
	 * Requiere el parámetro de request nombre.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * Recibe peticiones mediante el método HTTP POST. Consulta los libros por
	 * título. Requiere el parámetro de request nombre.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");

		Transaccion t = new Transaccion();
		t.setFecha(new Date());
		t.setTipo("consulta");
		em.persist(t);

		TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.titulo like ?1", Libro.class);
		query.setParameter(1, nombre + "%");
		List<Libro> results = query.getResultList();

		OutputStream os = resp.getOutputStream();

		for (Libro l : results) {
			String mensaje = "Libro consultado: " + l.getTitulo() + " " + l.getAutor();
			os.write(mensaje.getBytes());
		}

		// log.info("Consulta de libros exitoso " + nombre);
		os.flush();
	}

}
