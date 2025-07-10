package daoImpl;

import java.math.BigDecimal;

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
import entidad.CuentaPrestamoddlDTO;
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
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cuenta> listaCuentas = new ArrayList<>();

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM cuentas";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();

				cuenta.setCbu(rs.getString("CBU"));
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
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
		}
		return listaCuentas;
	}

	@Override
	public Cuenta read(String cbu) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cuenta cuenta = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM cuentas WHERE CBU = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cbu);
			rs = ps.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta();

				cuenta.setCbu(rs.getString("CBU"));
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
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
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
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1, cuenta.getCbu());
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
            Conexion.cerrarConexion(conn);
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
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(query);
			ps.setString(1, numeroCuenta);
			rs = ps.executeQuery();

			existe = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
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
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(query);
			ps.setString(1, cbu);
			rs = ps.executeQuery();
			existe = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
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
            conn = Conexion.getConexion();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }
        return contador;
	}

	@Override
	public boolean actualizarCuenta(Cuenta cuenta) {
		Connection conn = null;
		PreparedStatement ps = null;
		int filasAfectadas = 0;

		try {
			conn = Conexion.getConexion();
			String sql = "UPDATE cuentas SET Numero_Cuenta = ?, DNI = ?, Saldo = ?, ID_Tipo_Cuenta = ?, Activa = ? WHERE CBU = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, cuenta.getNumeroCuenta());
			ps.setString(2, cuenta.getCliente().getDni());
			ps.setBigDecimal(3, cuenta.getSaldo());
			ps.setInt(4, cuenta.getTipoCuenta().getID());
			ps.setBoolean(5, cuenta.isActiva());
			ps.setString(6, cuenta.getCbu());
			
			filasAfectadas = ps.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
	        System.err.println("Error actualizando cuenta: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try { 
				if (ps != null) ps.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
			Conexion.cerrarConexion(conn);
	    }
		
		if(filasAfectadas == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean eliminarCuenta(String cbu) {
		Connection conn = null; 
		PreparedStatement ps = null;
		int filasAfectadas = 0;
		
		 try {
		        conn = Conexion.getConexion();
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
		        Conexion.cerrarConexion(conn);
		    }

		    return false;
	}
	
	public List<CuentaPrestamoddlDTO> CargarDDl(String dni) {
		List<CuentaPrestamoddlDTO> listaCuentasDDL = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "SELECT CBU, Numero_Cuenta FROM Cuentas WHERE DNI = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dni);
			rs = ps.executeQuery();

			while (rs.next()) {
				CuentaPrestamoddlDTO cuenta = new CuentaPrestamoddlDTO();

				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));

				listaCuentasDDL.add(cuenta);
			}
			
		} catch (SQLException e) {
			System.err.println("ERROR DAO: Error al obtener las cuentas en DDL de SolicitarPrestamo.jsp " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
		}
		
		
		return listaCuentasDDL;
		
	}
	
	@Override
	public BigDecimal obtenerSaldo(String cbu) {
	    BigDecimal saldo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

	    try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("SELECT Saldo FROM Cuentas WHERE CBU = ? AND Activa = 1");
	        ps.setString(1, cbu);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            saldo = rs.getBigDecimal("Saldo");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
		}

		return saldo;
	}
	
	
	@Override
	public boolean actualizarSaldo(String cbu, BigDecimal nuevoSaldo) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean actualizado = false;

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement("UPDATE Cuentas SET Saldo = ? WHERE CBU = ? AND Activa = 1");
	        ps.setBigDecimal(1, nuevoSaldo);
	        ps.setString(2, cbu);

	        if (ps.executeUpdate() > 0) {
	            conn.commit();
	            actualizado = true;
	        } else {
	            conn.rollback();
	        }

	    } catch (SQLException e) {
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }

	    return actualizado;
	}
	
	public String obtenerDniTitular(String cbu) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String dni = null;

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement("SELECT DNI FROM Cuentas WHERE CBU = ?");
	        ps.setString(1, cbu);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            dni = rs.getString("DNI");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }
	    return dni;
	}

	public String obtenerNombreTitular(String cbu) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String nombre = null;

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(
	            "SELECT cl.Nombre FROM Cuentas cu JOIN Clientes cl ON cu.DNI = cl.DNI WHERE cu.CBU = ?");
	        ps.setString(1, cbu);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            nombre = rs.getString("Nombre");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }
	    return nombre;
	}
	
	public String obtenerCBUPorDNI(String dni) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String cbu = null;

	    try {
	        conn = Conexion.getConexion();
	        String query = "SELECT CBU FROM cuentas WHERE DNI = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, dni);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            cbu = rs.getString("CBU");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }
	    return cbu;
	}


	public List<Cuenta> obtenerCuentasPorDni(String dni) {
	    List<Cuenta> cuentas = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String sql = "SELECT * FROM Cuentas WHERE ACTIVA = 1 AND DNI = ?";
	    
	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, dni);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();

	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));

	            Date fechaSQL = rs.getDate("Fecha_Creacion");
	            if (fechaSQL != null) {
	                cuenta.setFechaCreacion(fechaSQL.toLocalDate());
	            }

	            cuenta.setSaldo(rs.getBigDecimal("Saldo"));
	            cuenta.setActiva(rs.getBoolean("Activa"));

	            TiposDeCuentas tipo = new TiposDeCuentas();
	            tipo.setID(rs.getInt("ID_Tipo_Cuenta"));
	            cuenta.setTipoCuenta(tipo);

	            Cliente cliente = new Cliente();
	            cliente.setDni(dni);
	            cuenta.setCliente(cliente);

	            cuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return cuentas;
	}
	   
	public boolean actualizarSaldoPorNumeroCuenta(String numeroCuenta, BigDecimal nuevoSaldo) {
	    boolean actualizado = false;
	    Connection conn = null;
	    PreparedStatement ps = null;

	    String sql = "UPDATE Cuentas SET Saldo = ? WHERE Numero_Cuenta = ? AND Activa = 1";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(sql);
	        ps.setBigDecimal(1, nuevoSaldo);
	        ps.setString(2, numeroCuenta);

	        if (ps.executeUpdate() > 0) {
	            conn.commit();
	            actualizado = true;
	        } else {
	            conn.rollback();
	        }

	    } catch (SQLException e) {
	        try { if (conn != null) conn.rollback(); } catch (SQLException e2) { e2.printStackTrace(); }
	        e.printStackTrace();
	    } finally {
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return actualizado;
	}
	
	public Cuenta obtenerPorNumero(String numeroCuenta) {
	    Cuenta cuenta = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String sql =  "SELECT c.*, " +
	             "cli.DNI, cli.Nombre AS NombreCliente, cli.Apellido, " +
	             "tipo.ID_Tipo_Cuenta, tipo.Descripcion AS Descripcion_TipoCuenta " +
	             "FROM Cuentas c " +
	             "INNER JOIN Clientes cli ON c.DNI = cli.DNI " + 
	             "INNER JOIN tipos_cuenta tipo ON c.ID_Tipo_Cuenta = tipo.ID_Tipo_Cuenta " +
	             "WHERE c.Numero_Cuenta = ? AND c.Activa = 1";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, numeroCuenta);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
	            cuenta.setSaldo(rs.getBigDecimal("Saldo"));

	            Date fechaSQL = rs.getDate("Fecha_Creacion");
	            if (fechaSQL != null) {
	                cuenta.setFechaCreacion(fechaSQL.toLocalDate());
	            }

	            cuenta.setActiva(rs.getBoolean("Activa"));

	            // Cliente
	            Cliente cliente = new Cliente();
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setNombre(rs.getString("NombreCliente"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cuenta.setCliente(cliente);

	            // Tipo de Cuenta
	            TiposDeCuentas tipo = new TiposDeCuentas();
	            tipo.setID(rs.getInt("ID_Tipo_Cuenta"));
	            tipo.setDescripcion(rs.getString("Descripcion_TipoCuenta"));
	            cuenta.setTipoCuenta(tipo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return cuenta;
	}
	
	
	public List<Cuenta> obtenerCuentasConCliente() {
	    List<Cuenta> lista = new ArrayList<>();
	    String sql = "SELECT c.Numero_Cuenta, c.Saldo, c.ID_Tipo_Cuenta, c.DNI, cl.Nombre, cl.Apellido " +
	                 "FROM Cuentas c JOIN Clientes cl ON c.DNI = cl.DNI";

	    try (Connection con = Conexion.getConexion();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
	            cuenta.setSaldo(rs.getBigDecimal("Saldo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));

	            cuenta.setCliente(cliente);
	            lista.add(cuenta);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	public List<Cuenta> obtenerCuentasConTipoPorDni(String dni) {
	    List<Cuenta> cuentas = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String sql = "SELECT c.*, t.Descripcion AS TipoDescripcion " +
	                 "FROM Cuentas c " +
	                 "JOIN Tipos_Cuenta t ON c.ID_Tipo_Cuenta = t.ID_Tipo_Cuenta " +
	                 "WHERE c.Activa = 1 AND c.DNI = ?";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, dni);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();

	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));

	            Date fechaSQL = rs.getDate("Fecha_Creacion");
	            if (fechaSQL != null) {
	                cuenta.setFechaCreacion(fechaSQL.toLocalDate());
	            }

	            cuenta.setSaldo(rs.getBigDecimal("Saldo"));
	            cuenta.setActiva(rs.getBoolean("Activa"));

	            // Tipo de cuenta completo
	            TiposDeCuentas tipo = new TiposDeCuentas();
	            tipo.setID(rs.getInt("ID_Tipo_Cuenta"));
	            tipo.setDescripcion(rs.getString("TipoDescripcion"));
	            cuenta.setTipoCuenta(tipo);

	            Cliente cliente = new Cliente();
	            cliente.setDni(dni);
	            cuenta.setCliente(cliente);

	            cuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return cuentas;
	}
}

	


	
	
	
	


	


