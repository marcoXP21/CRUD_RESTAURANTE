package syn.capa.apirest2.beans;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	private Integer ReservaID;
    private String NombreCliente;
    private String TelefonoCliente;	
    private String FechaReserva;
    private String HoraReserva;
    private Integer NumeroPersonas;
    private String Comentarios;
    private String Estado;
    
}
