package dao;

import java.util.List;

import entity.Revista;

public interface RevistaDAO {
	
	//PARA T1 => Registro
	public abstract int insertaRevista(Revista obj);
	
	//Para T2 ==> Consulta
	public abstract List<Revista> listaRevistaCompleja(String nombres,String frecuencia,int estado, int idModalidad);

	//Para EF ==> CRUD
	public abstract List<Revista> listaPorNombre(String filtro);
	public abstract int actualizaRevista(Revista obj);
	public abstract int eliminaRevista(int idRevista);
	public abstract Revista buscaRevista(int idRevista);
	
	//Para Validaciones
	public abstract List<Revista> listaPorNombreIgual(String nombre);
}

