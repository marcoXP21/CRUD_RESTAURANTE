package syn.capa.apirest2.controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.Operation;
import syn.capa.apirest2.beans.User;
import syn.capa.apirest2.service.UserService;

@Controller
public class UserViewController {
	
	
	@Autowired
	private  UserService userService;
	
	@GetMapping("/reservas")
	@Operation(description = "Lista todas las reservas", summary = "Obtiene una lista de todas las reservas")
	public String listarReservas(Model model) {
	    List<User> reservas = userService.listarReservas(); // Llama al servicio para obtener las reservas
	    model.addAttribute("reservas", reservas); // Agrega la lista de reservas al modelo
	    return "ReservaID"; // Nombre de la plantilla Thymeleaf que mostrará las reservas
	}

	@GetMapping("/reservas/{ReservaID}")
	@Operation(description = "Encuentra una reserva por ID", summary = "Proporciona un ID para buscar una reserva específica en la base de datos")
	public String obtenerReservaId(@PathVariable Integer ReservaID, Model model) {
	    User reserva = userService.buscarreservaId(ReservaID); // Llama al servicio para buscar la reserva
	    model.addAttribute("reserva", reserva); // Agrega el objeto User al modelo
	    return "ObtenerReservaID"; // Nombre de la plantilla Thymeleaf que mostrará los detalles de la reserva
	}
	

    @GetMapping("reservas/crear")
    @Operation(description = "Muestra el formulario para crear una nueva reserva", summary = "Proporciona el formulario para ingresar los datos de una nueva reserva")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("reservas", new User()); // Agrega un objeto User vacío al modelo
        return "CrearReserva"; // Nombre de la plantilla Thymeleaf para el formulario de creación
    }

    @PostMapping("reservas/crear")
    @Operation(description = "Crea una nueva reserva", summary = "Recibe los datos del formulario y guarda una nueva reserva en la base de datos")
    public String ingresarReserva(@ModelAttribute User user) {
        userService.crearreserva(user); // Llama al servicio para crear una nueva reserva
        return "redirect:/reservas"; // Redirige a la lista de reservas después de la creación
    }
    
/*	
	
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
    }*/
}
