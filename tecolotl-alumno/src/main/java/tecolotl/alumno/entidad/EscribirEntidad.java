package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "escribir", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "EscribirEntidad.busca", query = "SELECT a FROM EscribirEntidad a"),
        @NamedQuery(name = "EscribirEntidad.buscaActividad", query = "SELECT b FROM EscribirEntidad b WHERE b.escribirEntidadPK.actividadEntidad.id = :id")
})
public class EscribirEntidad {
    private EscribirEntidadPK escribirEntidadPK;
    private String pregunta;

    @EmbeddedId
    public EscribirEntidadPK getEscribirEntidadPK() {
        return escribirEntidadPK;
    }

    public void setEscribirEntidadPK(EscribirEntidadPK escribirEntidadPK) {
        this.escribirEntidadPK = escribirEntidadPK;
    }

    @Basic
    @Column(name = "pregunta")
    @NotNull
    @Size(min = 2, max = 100)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
