package entidad;

public class Usuario {
	private String usuario; // Corresponde a la clave primaria 'Usuario' en la tabla Usuarios
    private String clave;
    private TipoUsuario tipoUsuario;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, TipoUsuario tipoUsuario, boolean activo) {
        this.usuario = usuario;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        this.activo = activo;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario [Usuario=" + usuario + ", Clave=*****, TipoUsuario=" + (tipoUsuario != null ? tipoUsuario.getDescripcion() : "N/A") + ", Activo=" + activo + "]";
    }
}
