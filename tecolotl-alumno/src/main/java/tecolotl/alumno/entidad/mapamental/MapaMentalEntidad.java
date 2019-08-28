package tecolotl.alumno.entidad.mapamental;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "mapamental", schema = "alumno")
@SequenceGenerator(name = "generador", schema = "alumno", sequenceName = "mapamental_seq")
@NamedQueries({
        @NamedQuery(
                name = "EscribirEntidad.buscaPorActivdad",
                query = "SELECT e FROM MapaMentalEntidad e JOIN e.mapaMentalActividadEntidadLista mm WHERE mm.actividadEntidad.id = :idActividad")
})
public class MapaMentalEntidad {

    private Integer id;
    private String pregunta;
    private List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista;

    public MapaMentalEntidad() {
    }

    public MapaMentalEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "generador", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pregunta")
    @NotNull
    @Size(min = 2, max = 200)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @OneToMany(mappedBy = "mapaMentalEntidad", cascade = {CascadeType.PERSIST})
    public List<MapaMentalActividadEntidad> getMapaMentalActividadEntidadLista() {
        return mapaMentalActividadEntidadLista;
    }

    public void setMapaMentalActividadEntidadLista(List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista) {
        this.mapaMentalActividadEntidadLista = mapaMentalActividadEntidadLista;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pregunta='" + pregunta + "'")
                .toString();
    }
}
