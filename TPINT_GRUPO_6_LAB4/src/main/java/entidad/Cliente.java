package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cliente {
	private String dni; // PRIMARY KEY
    private String cuil; // UNIQUE
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private LocalDate fechaNacimiento; // Usar LocalDate para tipo DATE en SQL
    private String direccion;
    private String localidad;
    private String provincia;
    private String correoElectronico; // UNIQUE
    private String telefono;
    private Usuario usuario; // Objeto Usuario, no solo el String Usuario
    private boolean activo; // Para la baja lógica
    private LocalDate fechaAlta; // Solo para reporte
    private Cuenta cuenta; // Solo para reporte
    private BigDecimal saldoTotal; // Solo para reportes

    public Cliente() {
    }

    public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Cliente(String dni, String cuil, String nombre, String apellido, String sexo, String nacionalidad,
                   LocalDate fechaNacimiento, String direccion, String localidad, String provincia,
                   String correoElectronico, String telefono, Usuario usuario, boolean activo) {
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.usuario = usuario;
        this.activo = activo;
    }

    // --- Getters y Setters ---

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cliente [\n" +
               "  DNI=" + dni + ",\n" +
               "  CUIL=" + cuil + ",\n" +
               "  Nombre=" + nombre + ",\n" +
               "  Apellido=" + apellido + ",\n" +
               "  Sexo=" + sexo + ",\n" +
               "  Nacionalidad=" + nacionalidad + ",\n" +
               "  FechaNacimiento=" + fechaNacimiento + ",\n" +
               "  Direccion=" + direccion + ",\n" +
               "  Localidad=" + localidad + ",\n" +
               "  Provincia=" + provincia + ",\n" +
               "  CorreoElectronico=" + correoElectronico + ",\n" +
               "  Telefono=" + telefono + ",\n" +
               "  Usuario=" + (usuario != null ? usuario.getUsuario() : "N/A") + ",\n" + // Mostrar solo el nombre de usuario
               "  Activo=" + activo + "\n" +
               "]";
    }	
}
