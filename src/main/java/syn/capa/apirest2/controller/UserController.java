package syn.capa.apirest2.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import syn.capa.apirest2.beans.User;
import syn.capa.apirest2.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/reserva")
	public List<User> listarReservas() {
	    return userService.listarReservas();
	    }
	
	@GetMapping("/reserva/{ReservaID}")
    public User obtenerReservaId(@PathVariable Integer ReservaID) {
        return userService.buscarreservaId(ReservaID);
    }
	
	@PostMapping("/reserva/crear")
    public String ingresarReserva(@RequestBody User user) {
        return userService.crearreserva(user);
    }
	
	@PutMapping("/reserva/actualizar")
    public String actualizarReserva(@RequestBody User user) {
        return userService.actualizareserva(user);
    }
	
	@DeleteMapping("/reserva/eliminar/{ReservaID}")
    public String eliminarReserva(@PathVariable Integer ReservaID) {
        return userService.eliminarreserva(ReservaID);
    }
}
