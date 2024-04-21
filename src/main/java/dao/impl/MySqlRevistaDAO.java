package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.RevistaDAO;
import entity.Modalidad;
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

	@Override
	public List<Revista> listaRevistaCompleja(String nombres, String frecuencia, int estado, int idModalidad) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Revista> lstSalida = new ArrayList<Revista>();
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el sql
			String sql = "SELECT r.*, m.descripcion FROM revista r inner join "
					+ " modalidad m on m.idModalidad = r.idModalidad where "
					+ "	r.nombre like ? and "
					+ " r.frecuencia like ? and  "
					+ " (?=-1 or r.idModalidad=?) and "
					+ " r.estado =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, frecuencia);
			pstm.setInt(3, idModalidad);
			pstm.setInt(4, idModalidad);
			pstm.setInt(5, estado);
			
			System.out.println("SQL => " + pstm);
			
			//3 Se ejcuta el SQL
			rs =  pstm.executeQuery();
			Revista objRevista;
			Modalidad objModalidad;
			while(rs.next()) {
				objRevista = new Revista();
				objRevista.setIdRevista(rs.getInt(1));
				objRevista.setNombre(rs.getString(2));
				objRevista.setFrecuencia(rs.getString(3));
				objRevista.setFechaCreacion(rs.getDate(4));
				objRevista.setFechaRegistro(rs.getDate(5));
				objRevista.setEstado(rs.getInt(6));
				
				objModalidad = new Modalidad();
				objModalidad.setIdModalidad(rs.getInt(7));
				objModalidad.setDescripcion(rs.getString(8));
				objRevista.setModalidad(objModalidad);
	
				lstSalida.add(objRevista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
		return lstSalida;
	}

}





