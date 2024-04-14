package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.RevistaDAO;
import entity.Revista;
import util.MySqlDBConexion;

public class MySqlRevistaDAO implements RevistaDAO {

	
	@Override
	public int insertaRevista(Revista obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el sql
			String sql = "insert into revista values(null,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getFrecuencia());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setDate(4, obj.getFechaRegistro());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getModalidad().getIdModalidad());
			
			System.out.println("SQL => " + pstm);
			
			//3 ejecuta el sql a la base datos
			//salida tendra el numero de instados
			salida = pstm.executeUpdate();
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
