package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "glosario_actividad", schema = "alumno")
@NamedQueries( value = {
        @NamedQuery(name = "GlosarioActividadEntidad.busca", query = "SELECT ga FROM GlosarioActividadEntidad ga"),
        @NamedQuery(
                name = "GlosarioActividadEntidad.buscaActividad",
                query = "SELECT ga FROM GlosarioActividadEntidad ga JOIN FETCH ga.glosarioActividadEntidadPK.glosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg " +
                        "WHERE ga.glosarioActividadEntidadPK.actividadEntidad.id = :idActivdad"
        ),
        @NamedQuery(
                name = "GlosarioActividadEntidad.buscaPalabraNoActividad",
                query = "SELECT ga FROM GlosarioActividadEntidad ga JOIN FETCH ga.glosarioActividadEntidadPK.glosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg " +
                        "WHERE ga.glosarioActividadEntidadPK.actividadEntidad.id <> :idActivdad AND g.glosarioEntidadPK.palabra LIKE :palabra"
        )
})
public class GlosarioActividadEntidad {

    private GlosarioActividadEntidadPK glosarioActividadEntidadPK;
    private List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista;

    public GlosarioActividadEntidad() {
    }

    public GlosarioActividadEntidad(GlosarioActividadEntidadPK glosarioActividadEntidadPK) {
        this.glosarioActividadEntidadPK = glosarioActividadEntidadPK;
    }

    @EmbeddedId
    public GlosarioActividadEntidadPK getGlosarioActividadEntidadPK() {
        return glosarioActividadEntidadPK;
    }

    public void setGlosarioActividadEntidadPK(GlosarioActividadEntidadPK glosarioActividadEntidadPK) {
        this.glosarioActividadEntidadPK = glosarioActividadEntidadPK;
    }

    @OneToMany(mappedBy = "tareaGlosarioActividadEntidadPK.glosarioActividadEntidad")
    public List<TareaGlosarioActividadEntidad> getTareaGlosarioActividadEntidadLista() {
        return tareaGlosarioActividadEntidadLista;
    }

    public void setTareaGlosarioActividadEntidadLista(List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista) {
        this.tareaGlosarioActividadEntidadLista = tareaGlosarioActividadEntidadLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlosarioActividadEntidad that = (GlosarioActividadEntidad) o;
        return glosarioActividadEntidadPK.equals(that.glosarioActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(glosarioActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioActividadEntidad.class.getSimpleName() + "[", "]")
                .add("glosarioActividadEntidadPK=" + glosarioActividadEntidadPK)
                .toString();
    }

}
