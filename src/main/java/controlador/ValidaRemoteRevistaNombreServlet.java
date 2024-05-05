package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.RevistaDAO;
import entity.Revista;
import fabricas.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/validaRemoteRevistaNombre")
public class ValidaRemoteRevistaNombreServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String variable = req.getParameter("nombre");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		List<Revista> lstLista = daoRevista.listaPorNombreIgual(variable);
		String msg = "";
		if (lstLista.isEmpty()) {
			msg = "{\"valid\":true}";
		}else {
			msg = "{\"valid\":false}";
		}
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println(msg);
	}
	
}



