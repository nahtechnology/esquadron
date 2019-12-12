package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Cacheable
@Table(name = "glosario", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(name = "GlosarioEntidad.busca", query = "SELECT g FROM GlosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg"),
    @NamedQuery(
        name = "GlosarioEntidad.buscaPalabra",
        query = "SELECT g FROM GlosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg WHERE g.glosarioEntidadPK.palabra LIKE :palabra"
    ),
    @NamedQuery(
        name = "GlosarioEntidad.buscaNoImganeIdTarea",
        query = "SELECT new GlosarioEntidad(g.glosarioEntidadPK, g.significado) FROM GlosarioEntidad g JOIN g.glosarioActividadEntidadLista ga " +
                "JOIN ga.tareaGlosarioActividadEntidadLista tga WHERE tga.tareaGlosarioActividadEntidadPK.tareaEntidad.id = :idTarea"
    )
})
public class GlosarioEntidad {

    private GlosarioEntidadPK glosarioEntidadPK;
    private String significado;
    private List<GlosarioActividadEntidad> glosarioActividadEntidadLista;

    public GlosarioEntidad() {
    }

    public GlosarioEntidad(GlosarioEntidadPK glosarioEntidadPK) {
        this.glosarioEntidadPK = glosarioEntidadPK;
    }

    public GlosarioEntidad(GlosarioEntidadPK glosarioEntidadPK, String significado) {
        this(glosarioEntidadPK);
        this.significado = significado;
    }

    @EmbeddedId
    public GlosarioEntidadPK getGlosarioEntidadPK() {
        return glosarioEntidadPK;
    }

    public void setGlosarioEntidadPK(GlosarioEntidadPK glosarioEntidadPK) {
        this.glosarioEntidadPK = glosarioEntidadPK;
    }

    @Basic
    @Column(name = "significado")
    @NotNull
    @Size(min = 2, max = 100)
    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @OneToMany(mappedBy = "glosarioActividadEntidadPK.glosarioEntidad", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    public List<GlosarioActividadEntidad> getGlosarioActividadEntidadLista() {
        return glosarioActividadEntidadLista;
    }

    public void setGlosarioActividadEntidadLista(List<GlosarioActividadEntidad> glosarioActividadEntidadLista) {
        this.glosarioActividadEntidadLista = glosarioActividadEntidadLista;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioEntidad.class.getSimpleName() + "[", "]")
                .add("glosarioEntidad=" + glosarioEntidadPK.toString())
                .add("palabra='" + glosarioEntidadPK + "'")
                .add("significado='" + significado + "'")
                .toString();
    }
}
