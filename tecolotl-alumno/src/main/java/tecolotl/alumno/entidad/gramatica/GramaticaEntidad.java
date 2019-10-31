package tecolotl.alumno.entidad.gramatica;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "gramatica", schema = "alumno")
@NamedQueries( value = {
        @NamedQuery(name = "GramaticaEntidad.busca", query = "SELECT ge from GramaticaEntidad ge"),
        @NamedQuery(
                name = "GramaticaEntidad.buscaActvidad",
                query = "SELECT g FROM GramaticaEntidad g WHERE g.gramaticaEntidadPK.actividadEntidad.id = :idActividad"
        )
})
public class GramaticaEntidad implements Serializable {

    private GramaticaEntidadPK gramaticaEntidadPK;
    private String palabra;

    public GramaticaEntidad() {
    }

    public GramaticaEntidad(GramaticaEntidadPK gramaticaEntidadPK) {
        this.gramaticaEntidadPK = gramaticaEntidadPK;
    }

    @EmbeddedId
    public GramaticaEntidadPK getGramaticaEntidadPK() {
        return gramaticaEntidadPK;
    }

    public void setGramaticaEntidadPK(GramaticaEntidadPK gramaticaEntidadPK) {
        this.gramaticaEntidadPK = gramaticaEntidadPK;
    }

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "palabra")
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GramaticaEntidad.class.getSimpleName() + "[", "]")
                .add("gramaticaEntidadPK=" + gramaticaEntidadPK)
                .add("palabra='" + palabra + "'")
                .toString();
    }
}
