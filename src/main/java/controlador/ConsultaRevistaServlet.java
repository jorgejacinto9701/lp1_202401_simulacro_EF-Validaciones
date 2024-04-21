package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import dao.RevistaDAO;
import entity.Revista;
import fabricas.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/consultaRevista")
public class ConsultaRevistaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nom = req.getParameter("nombre");
		String fre = req.getParameter("frecuencia");
		String mod = req.getParameter("modalidad");
		String est = req.getParameter("estado");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		RevistaDAO daoRevista = subFabrica.getRevistaDAO();
		
		List<Revista> lstSalida = daoRevista.listaRevistaCompleja("%"+nom+"%", "%"+fre+"%", Integer.parseInt(est), Integer.parseInt(mod));
		
		//2 Se convierte a JSON
		Gson gson = new Gson();
		String json = gson.toJson(lstSalida);

		//3 se envía al json al browser
		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.println(json);
		
		
	}
}
