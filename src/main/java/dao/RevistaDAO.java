package dao;

import java.util.List;

import entity.Revista;

public interface RevistaDAO {
	
	public abstract int insertaRevista(Revista obj); 
	public abstract List<Revista> listaRevistaCompleja(String nombres,String frecuencia,int estado, int idModalidad);
}

