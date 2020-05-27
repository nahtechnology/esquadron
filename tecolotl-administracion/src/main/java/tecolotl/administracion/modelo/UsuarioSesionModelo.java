package tecolotl.administracion.modelo;

public class UsuarioSesionModelo {

    String apodo;
    String claveCentroTrabajo;

    public UsuarioSesionModelo() {
    }

    public UsuarioSesionModelo(String apodo, String claveCentroTrabajo) {
        this.apodo = apodo;
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }
}
