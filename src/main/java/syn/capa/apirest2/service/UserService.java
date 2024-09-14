package syn.capa.apirest2.service;

import java.util.List;

import syn.capa.apirest2.beans.User;


public interface UserService {


	public String saludo(String nombre) ;

	public User buscarreservaId(Integer ReservaID);


	public String crearreserva(User user);

	public String actualizareserva(User user);
	
	public String eliminarreserva(Integer ReservaID) ;


	public List<User> listarReservas() ;
}

/*
cs.setInt(1, libro.getId());
cs.setString(2, libro.getTitulo());
cs.setString(3, libro.getAutor());
cs.setString(4, libro.getGenero());
cs.setInt(5, libro.getAnioPubli());
cs.setString(6, libro.getIsbn());
cs.setString(7, libro.getDescripcion());
cs.execute(); // Ejecuta el procedimiento almacenado
return true;
} catch (SQLException e) {
e.printStackTrace();
return false;
}
});
}
*/