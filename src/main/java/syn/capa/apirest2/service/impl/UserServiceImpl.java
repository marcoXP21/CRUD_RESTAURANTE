package syn.capa.apirest2.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import syn.capa.apirest2.beans.User;
import syn.capa.apirest2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String saludo(String nombre) {
		return "Hola ".concat(nombre);
	}
	@Override
	public User buscarreservaId(Integer ReservaID) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_BUSCAR_RESERVA_POR_ID(?)}")) {
				cs.setInt(1, ReservaID);
				try (ResultSet rs = cs.executeQuery()) {
					if (rs.next()) {
						return mapearUsuario(rs);
					} else {
						return null;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
	}


	@Override
	public String crearreserva(User user) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_InsertarReserva(?,?,?,?,?,?,?,?)}")) {

				cs.setInt(1, user.getReservaID());
				cs.setString(2, user.getNombreCliente());
				cs.setString(3, user.getTelefonoCliente());
				cs.setString(4, user.getFechaReserva());
				cs.setString(5, user.getHoraReserva());
				cs.setInt(6, user.getNumeroPersonas());
				cs.setString(7, user.getComentarios());
				cs.setString(8, user.getEstado());
				cs.execute();

				return "ingreso con exito";
				

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
	}
	@Override
	public String actualizareserva(User user) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_ActualizarReserva(?,?,?,?,?,?,?,?)}")) {

				cs.setInt(1, user.getReservaID());
				cs.setString(2, user.getNombreCliente());
				cs.setString(3, user.getTelefonoCliente());
				cs.setString(4, user.getFechaReserva());
				cs.setString(5, user.getHoraReserva());
				cs.setInt(6, user.getNumeroPersonas());
				cs.setString(7, user.getComentarios());
				cs.setString(8, user.getEstado());
				cs.execute();

				return "Actualizado con exito";
				

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
	}
	@Override
	public String eliminarreserva(Integer ReservaID) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_EliminarReserva(?)}")) {

				cs.setInt(1, ReservaID);

				cs.execute();

				return "eliminado con exito";
				

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	@Override
	public List<User> listarReservas() {
		return jdbcTemplate.execute((Connection conn) -> {
	        try (CallableStatement cs = conn.prepareCall("{call SP_ListarReservas()}")) {
	            try (ResultSet rs = cs.executeQuery()) {
	                List<User> reservas = new ArrayList<>();
	                while (rs.next()) {
	                    reservas.add(mapearUsuario(rs));
	                }
	                return reservas;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return Collections.emptyList();
	        }
	    });
	}
	private User mapearUsuario(ResultSet rs) throws SQLException {
		User usuario = new User();
		usuario.setReservaID(rs.getInt("ReservaID"));
		usuario.setNombreCliente(rs.getString("NombreCliente"));
		usuario.setTelefonoCliente(rs.getString("TelefonoCliente"));
		usuario.setFechaReserva(rs.getString("FechaReserva"));
		usuario.setHoraReserva(rs.getString("HoraReserva"));
		usuario.setNumeroPersonas(rs.getInt("NumeroPersonas"));
		usuario.setComentarios(rs.getString("Comentarios"));
		usuario.setEstado(rs.getString("Estado"));

		return usuario;
	}
}
