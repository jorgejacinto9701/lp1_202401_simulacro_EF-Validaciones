package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

@WebServlet("/crudRevista")
public class CrudRevistaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String metodo = req.getParameter("metodo");	
		switch (metodo) {
			case "paramLista": 		{ lista(req, resp); break;}
			case "paramInserta": 	{ inserta(req, resp); break;}
			case "paramActualiza": 	{ actualiza(req, resp); break;}
			case "paramELogica": 	{ elogica(req, resp); break;}
			case "paramEFisica": 	{ efisica(req, resp);}
		}
	}

	protected void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] lista");
		String filtro = req.getParameter("filtro");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		List<Revista> lstSalida = daoRevista.listaPorNombre("%" + filtro + "%");
		
		Gson gson = new Gson();
		String json = gson.toJson(lstSalida);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);

		System.out.println("[fin] lista");
	}
	
	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();

		String vnombre = req.getParameter("nombre");
		String vfrecuencia = req.getParameter("frecuencia");
		String vfechaCreacion = req.getParameter("fechaCreacion");
		String vmodalidad = req.getParameter("modalidad");
		
		Modalidad objModalidad = new Modalidad();
		objModalidad.setIdModalidad(Integer.parseInt(vmodalidad));
;
		Revista objRevista = new Revista();
		objRevista.setNombre(vnombre);
		objRevista.setFrecuencia(vfrecuencia);
		objRevista.setEstado(1);
		objRevista.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
		objRevista.setFechaCreacion(Date.valueOf(vfechaCreacion));
		objRevista.setModalidad(objModalidad);
		
		Respuesta objResupeta = new Respuesta();
		int salida =daoRevista.insertaRevista(objRevista);
		if (salida>0) {
			List<Revista>  lstDatos = daoRevista.listaPorNombre("%");
			objResupeta.setDatos(lstDatos);
			objResupeta.setMensaje("Registro existoso");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] inserta");
	}
	protected void actualiza(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] actualiza");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();

		String vidrevista = req.getParameter("idRevista");
		String vnombre = req.getParameter("nombre");
		String vfrecuencia = req.getParameter("frecuencia");
		String vfechaCreacion = req.getParameter("fechaCreacion");
		String vmodalidad = req.getParameter("modalidad");
		String vestado = req.getParameter("estado");
		
		Modalidad objModalidad = new Modalidad();
		objModalidad.setIdModalidad(Integer.parseInt(vmodalidad));

		Revista objRevista = new Revista();
		objRevista.setIdRevista(Integer.parseInt(vidrevista));
		objRevista.setNombre(vnombre);
		objRevista.setFrecuencia(vfrecuencia);
		objRevista.setEstado(Integer.parseInt(vestado));
		objRevista.setFechaCreacion(Date.valueOf(vfechaCreacion));
		objRevista.setModalidad(objModalidad);
		
		Respuesta objResupeta = new Respuesta();
		int salida =daoRevista.actualizaRevista(objRevista);
		if (salida>0) {
			List<Revista>  lstDatos = daoRevista.listaPorNombre("%");
			objResupeta.setDatos(lstDatos);
			objResupeta.setMensaje("Actualizaci\u00f3n existoso");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] actualiza");
	}
	protected void elogica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] elogica");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		String idRevista = req.getParameter("idRevista");
		Revista objRevista = daoRevista.buscaRevista(Integer.parseInt(idRevista));
		int estadoNuevo = objRevista.getEstado() == 0 ? 1 : 0;
		objRevista.setEstado(estadoNuevo);
		daoRevista.actualizaRevista(objRevista);
		
		Respuesta objResupeta = new Respuesta();
		List<Revista>  lstDatos = daoRevista.listaPorNombre("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] elogica");
	}
	
	protected void efisica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] efisica");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		String idRevista = req.getParameter("idRevista");
		
		Respuesta objResupeta = new Respuesta();
		int salida =daoRevista.eliminaRevista(Integer.parseInt(idRevista));
		if (salida>0) {
			List<Revista>  lstDatos = daoRevista.listaPorNombre("%");
			objResupeta.setDatos(lstDatos);
			objResupeta.setMensaje("Eliminaci\u00f3n existosa");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] efisica");
	}

}





