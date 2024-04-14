package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;

import dao.RevistaDAO;
import entity.Modalidad;
import entity.Respuesta;
import entity.Revista;
import fabricas.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registraRevista")
public class RegistraRevistaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String nom = req.getParameter("nombre");
		String fre = req.getParameter("frecuencia");
		String fec = req.getParameter("fechaCreacion");
		String mod = req.getParameter("modalidad");

		Revista objRevista = new Revista();
		objRevista.setNombre(nom);
		objRevista.setFrecuencia(fre);
		objRevista.setFechaCreacion(Date.valueOf(fec));
		objRevista.setEstado(1);
		objRevista.setFechaRegistro(new Date(System.currentTimeMillis()));
		
		Modalidad objModalidad = new Modalidad();
		objModalidad.setIdModalidad(Integer.parseInt(mod));
		
		objRevista.setModalidad(objModalidad);
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		int salida = daoRevista.insertaRevista(objRevista);
	
		Respuesta objRespuesta = new Respuesta();
		
		if (salida > 0) {
			objRespuesta.setMensaje("Registro exitoso");
		}else {
			objRespuesta.setMensaje("Error en el registro");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println(json);
	}
}
