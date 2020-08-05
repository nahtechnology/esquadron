package tecolotl.administracion.persistencia.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(schema = "administracion", name = "licencia_tiempo")
@NamedQuery(
        name = "LicenciaTiempoVista.buscaMinimoDias",
        query = "SELECT lt FROM LicenciaTiempoVista lt WHERE lt.dias < :tiempo")
public class LicenciaTiempoVista {

    private String claveCentroTrabajo;
    private int totalLicencia;
    private int dias;

    @Id
    @Column(name = "clave_centro_trabajo")
    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    @Column(name = "total_licencia")
    public int getTotalLicencia() {
        return totalLicencia;
    }

    public void setTotalLicencia(int totalLicencia) {
        this.totalLicencia = totalLicencia;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
