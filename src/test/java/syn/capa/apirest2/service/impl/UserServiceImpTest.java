package syn.capa.apirest2.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import syn.capa.apirest2.beans.User;

public class UserServiceImpTest {

	@Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private Connection connection;

    @Mock
    private CallableStatement callableStatement;
    
    @Mock
    private ResultSet resultSet;
    
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testBuscarreservaId_ReservaExiste() throws SQLException {
        Integer reservaId = 1;
        User mockUser = new User();
        // Simula los datos que devolvería el ResultSet
        when(resultSet.next()).thenReturn(true);  // La reserva existe
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("NombreCliente")).thenReturn("Marcos Gómez");

        // Configura la simulación del CallableStatement y Connection
        when(connection.prepareCall("{call SP_BUSCAR_RESERVA_POR_ID(?)}")).thenReturn(callableStatement);
        when(callableStatement.executeQuery()).thenReturn(resultSet);

        // Simula la ejecución del jdbcTemplate.execute()
        when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
            var action = (Object) invocation.getArgument(0);
            return ((ConnectionCallback) action).doInConnection(connection);
        });

        // Llama al método bajo prueba
        User result = userServiceImpl.buscarreservaId(reservaId);

        // Verifica que se hayan llamado los métodos correctos
        verify(callableStatement).setInt(1, reservaId);
        assertNotNull(result);
        assertEquals("Marcos Gómez", result.getNombreCliente()); // Asegúrate que sea el nombre esperado
    }
    
    @Test
    void testCrearreserva() throws SQLException {
        // Crea un objeto User simulado
        User mockUser = new User();
        mockUser.setReservaID(1);
        mockUser.setNombreCliente("Manuel");
        mockUser.setTelefonoCliente("123456789");
        mockUser.setFechaReserva("2024-09-15");
        mockUser.setHoraReserva("19:00");
        mockUser.setNumeroPersonas(4);
        mockUser.setComentarios("Mesa junto a la ventana");
        mockUser.setEstado("Confirmado");

        // Configura la simulación del CallableStatement y Connection
        when(connection.prepareCall("{call SP_InsertarReserva(?,?,?,?,?,?,?,?)}")).thenReturn(callableStatement);

        // Simula el comportamiento del CallableStatement al configurar los parámetros
        doNothing().when(callableStatement).setInt(1, mockUser.getReservaID());
        doNothing().when(callableStatement).setString(2, mockUser.getNombreCliente());
        doNothing().when(callableStatement).setString(3, mockUser.getTelefonoCliente());
        doNothing().when(callableStatement).setString(4, mockUser.getFechaReserva());
        doNothing().when(callableStatement).setString(5, mockUser.getHoraReserva());
        doNothing().when(callableStatement).setInt(6, mockUser.getNumeroPersonas());
        doNothing().when(callableStatement).setString(7, mockUser.getComentarios());
        doNothing().when(callableStatement).setString(8, mockUser.getEstado());

        // Simula el comportamiento del CallableStatement al ejecutar el procedimiento
        //doNothing().when(callableStatement).execute();

        // Simula la ejecución del jdbcTemplate.execute()
        when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
            ConnectionCallback<?> action = invocation.getArgument(0);
            return action.doInConnection(connection);
        });

        // Llama al método bajo prueba
        String result = userServiceImpl.crearreserva(mockUser);

        // Verifica que se hayan llamado los métodos correctos
        verify(callableStatement).setInt(1, mockUser.getReservaID());
        verify(callableStatement).setString(2, mockUser.getNombreCliente());
        verify(callableStatement).setString(3, mockUser.getTelefonoCliente());
        verify(callableStatement).setString(4, mockUser.getFechaReserva());
        verify(callableStatement).setString(5, mockUser.getHoraReserva());
        verify(callableStatement).setInt(6, mockUser.getNumeroPersonas());
        verify(callableStatement).setString(7, mockUser.getComentarios());
        verify(callableStatement).setString(8, mockUser.getEstado());
        verify(callableStatement).execute();

        // Verifica que el resultado sea el esperado
        assertEquals("ingreso con exito", result);
    }

    @Test
    void testEliminarreserva() throws SQLException {
        // ReservaID simulado
        Integer reservaId = 1;

        // Configura la simulación del CallableStatement y Connection
        when(connection.prepareCall("{call SP_EliminarReserva(?)}")).thenReturn(callableStatement);

        // Simula el comportamiento del CallableStatement al configurar el parámetro
        doNothing().when(callableStatement).setInt(1, reservaId);

        // Simula el comportamiento del CallableStatement al ejecutar el procedimiento
        //doNothing().when(callableStatement).execute();

        // Simula la ejecución del jdbcTemplate.execute()
        when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
            ConnectionCallback<?> action = invocation.getArgument(0);
            return action.doInConnection(connection);
        });

        // Llama al método bajo prueba
        String result = userServiceImpl.eliminarreserva(reservaId);

        // Verifica que se hayan llamado los métodos correctos
        verify(callableStatement).setInt(1, reservaId);
        verify(callableStatement).execute();

        // Verifica que el resultado sea el esperado
        assertEquals("eliminado con exito", result);
    }

    

}
