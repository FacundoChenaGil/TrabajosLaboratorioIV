package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ICuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TiposDeCuentas;

public class CuentaDaoImpl implements ICuentaDao {

	private TipoDeCuentaDaoImpl tdc;
	private ClienteDaoImpl cl;

	public CuentaDaoImpl() {
		this.tdc = new TipoDeCuentaDaoImpl();
		this.cl = new ClienteDaoImpl();
	}

	@Override
	public List<Cuenta> readAll() {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
		Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cuenta> listaCuentas = new ArrayList<>();

		try {
			String sql = "SELECT * FROM cuentas";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();

				cuenta.setCBU(rs.getString("CBU"));
				Date sqlDate = rs.getDate("Fecha_Creacion");
				cuenta.setFechaCreacion(sqlDate != null ? sqlDate.toLocalDate() : null);
				cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
				cuenta.setSaldo(rs.getBigDecimal("Saldo"));
				cuenta.setActiva(rs.getBoolean("Activa"));
				cuenta.setTipoCuenta(tdc.getTipoCuentaPorID(rs.getInt("ID_Tipo_Cuenta")));
				cuenta.setCliente(cl.obtenerClientePorDni(rs.getString("DNI")));

				listaCuentas.add(cuenta);
			}
		} catch (SQLException e) {
			System.err.println("ERROR DAO: Error al obtener todas las cuentas " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			// NO CERRAR LA CONEXIÓN DEL SINGLETON AQUÍ
		}
		return listaCuentas;
	}

	@Override
	public Cuenta read(String cbu) {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
		Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cuenta cuenta = null;

		try {
			String sql = "SELECT * FROM cuentas WHERE CBU = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cbu);
			rs = ps.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta();

				cuenta.setCBU(rs.getString("CBU"));
				Date sqlDate = rs.getDate("Fecha_Creacion");
				cuenta.setFechaCreacion(sqlDate != null ? sqlDate.toLocalDate() : null);
				cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
				cuenta.setSaldo(rs.getBigDecimal("Saldo"));
				cuenta.setActiva(rs.getBoolean("Activa"));
				cuenta.setTipoCuenta(tdc.getTipoCuentaPorID(rs.getInt("ID_Tipo_Cuenta")));
				cuenta.setCliente(cl.obtenerClientePorDni(rs.getString("DNI")));
			}

		} catch (SQLException e2) {
			System.err.println("ERROR DAO: Error al obtener la cuenta con CBU: " + cbu + e2.getMessage());
			e2.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			// NO CERRAR LA CONEXIÓN DEL SINGLETON AQUÍ
		}

		return cuenta;
	}

	@Override
	public int insertarCuenta(Cuenta cuenta) {
		String query = "INSERT INTO Cuentas (CBU, Numero_Cuenta, Fecha_Creacion, Saldo, Activa, ID_Tipo_Cuenta, DNI) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int filasAfectadas = 0;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1, cuenta.getCBU());
            ps.setString(2, cuenta.getNumeroCuenta());
            ps.setDate(3, Date.valueOf(cuenta.getFechaCreacion()));
            ps.setBigDecimal(4, cuenta.getSaldo());
            ps.setBoolean(5, cuenta.isActiva());
            ps.setInt(6, cuenta.getTipoCuenta().getID());
            ps.setString(7, cuenta.getCliente().getDni());

            filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return filasAfectadas;
	}

	@Override
	public boolean existeNumeroCuenta(String numeroCuenta) {
		String query = "SELECT 1 FROM Cuentas WHERE Numero_Cuenta = ? AND Activa = 1 LIMIT 1";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean existe = false;

		try {
			conn = Conexion.getConexion().getSQLConexion();
			ps = conn.prepareStatement(query);
			ps.setString(1, numeroCuenta);
			rs = ps.executeQuery();

			existe = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return existe;
	}

	@Override
	public boolean existeCBU(String cbu) {
		String query = "SELECT 1 FROM Cuentas WHERE CBU = ? AND Activa = 1 LIMIT 1";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean existe = false;

		try {
			conn = Conexion.getConexion().getSQLConexion();
			ps = conn.prepareStatement(query);
			ps.setString(1, cbu);
			rs = ps.executeQuery();
			existe = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return existe;
	}

	@Override
	public int contarCuentasPorDni(String dni) {
		String query = "SELECT COUNT(*) FROM Cuentas WHERE DNI = ? AND Activa = 1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contador = 0;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contador;
	}

	@Override
	public boolean actualizarCuenta(Cuenta cuenta) {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
		Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
		PreparedStatement ps = null;
		int filasAfectadas = 0;

		try {
			String sql = "UPDATE cuentas SET Numero_Cuenta = ?, DNI = ?, Saldo = ?, ID_Tipo_Cuenta = ?, Activa = ? WHERE CBU = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, cuenta.getNumeroCuenta());
			ps.setString(2, cuenta.getCliente().getDni());
			ps.setBigDecimal(3, cuenta.getSaldo());
			ps.setInt(4, cuenta.getTipoCuenta().getID());
			ps.setBoolean(5, cuenta.isActiva());
			ps.setString(6, cuenta.getCBU());
			
			filasAfectadas = ps.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
	        System.err.println("Error actualizando cuenta: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }
		
		if(filasAfectadas == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean eliminarCuenta(String cbu) {
		Conexion conexionSingleton = Conexion.getConexion();
		Connection conn = conexionSingleton.getSQLConexion(); 
		PreparedStatement ps = null;
		int filasAfectadas = 0;
		
		 try {
		        String sql = "UPDATE cuentas SET Activa = 0 WHERE CBU = ?";

		        ps = conn.prepareStatement(sql);
		        ps.setString(1, cbu);

		        filasAfectadas = ps.executeUpdate();

		        if (filasAfectadas == 1) {
		            conn.commit();
		            return true;
		        } else {
		            conn.rollback();
		        }

		    } catch (SQLException e) {
		        System.err.println("Error al eliminar (baja lógica) la cuenta con CBU " + cbu + ": " + e.getMessage());
		        try {
		            conn.rollback();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    } finally {
		        try {
		            if (ps != null) ps.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return false;
	}

}
