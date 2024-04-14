package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ModalidadDAO;
import entity.Modalidad;
import util.MySqlDBConexion;

public class MySqlModalidadDAO implements ModalidadDAO{

	@Override
	public List<Modalidad> lista() {
		List<Modalidad> salida = new ArrayList<Modalidad>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el sql
			String sql = "select * from modalidad";
			pstm = conn.prepareStatement(sql);
			
			System.out.println("SQL => " + pstm);
			
			//3 Traer la data
			rs = pstm.executeQuery();
		
			Modalidad obj = null;
			while(rs.next()) {
				obj = new Modalidad();
				obj.setIdModalidad(rs.getInt(1));
				obj.setDescripcion(rs.getString(2));
				salida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

}
