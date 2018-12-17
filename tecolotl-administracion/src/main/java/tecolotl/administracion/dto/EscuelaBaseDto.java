package tecolotl.administracion.dto;

public class EscuelaBaseDto {

    private String claveCentroTrabajo;
    private String nombre;

    public EscuelaBaseDto() {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaBaseDto(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
