package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class ControlSesionEntidad {

    private Date tiempo;
    private TipoRegistroEntidad tipoRegistroEntidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tiempo")
    @Id
    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_registro")
    public TipoRegistroEntidad getTipoRegistroEntidad() {
        return tipoRegistroEntidad;
    }

    public void setTipoRegistroEntidad(TipoRegistroEntidad tipoRegistroEntidad) {
        this.tipoRegistroEntidad = tipoRegistroEntidad;
    }
}
