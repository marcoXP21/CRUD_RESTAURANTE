package syn.capa.apirest2.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import syn.capa.apirest2.beans.User;
import syn.capa.apirest2.service.impl.UserServiceImpl;

@Service
public class UserRepository {
	@Autowired
	private  UserServiceImpl userServiceImpl;
	
	@GetMapping("/reserva")
	public List<User> listarReservas() {
	    return userServiceImpl.listarReservas();
	    }
	@PostMapping("/reserva/crear")
    public String ingresarReserva(@RequestBody User user) {
        return userServiceImpl.crearreserva(user);
    }
	
	@PutMapping("/reserva/actualizar")
    public String actualizarReserva(@RequestBody User user) {
        return userServiceImpl.actualizareserva(user);
    }
	
	@DeleteMapping("/reserva/eliminar/{ReservaID}")
    public String eliminarReserva(@PathVariable Integer ReservaID) {
        return userServiceImpl.eliminarreserva(ReservaID);
    }
}
