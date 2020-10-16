package tecolotl.profesor.vista;

import org.hibernate.annotations.Immutable;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.profesor.entidad.GrupoEntidad;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(schema = "profesor", name = "calificacion_coordinador")
@NamedQueries(
        @NamedQuery(
                name = "CalificacionCoordinadorVista.promedioAlumno",
                query = "SELECT new CalificacionCoordinadorVista(a.nombre, a.apellidoMaterno, a.apellidoPaterno, avg(cc.mapaMentalpromedio), avg(cc.relacionarimagenPromedio), " +
                        "avg(cc.gramaticaPromedio), avg(cc.relacionaroracionPromedio), avg(cc.oracionesPromedio), avg(cc.completarPromedio)) " +
                        "FROM CalificacionCoordinadorVista cc JOIN cc.alumnoEntidad a WHERE cc.alumnoEntidad.id = :idAlumno GROUP BY cc.alumnoEntidad.id"
        )
)
public class CalificacionCoordinadorVista {

    private UUID id;
    private AlumnoEntidad alumnoEntidad;
    private GrupoEntidad grupoEntidad;
    private Short mapaMentalpromedio;
    private Short relacionarimagenPromedio;
    private Short gramaticaPromedio;
    private Short relacionaroracionPromedio;
    private Short oracionesPromedio;
    private Short completarPromedio;

    public CalificacionCoordinadorVista() {
    }

    public CalificacionCoordinadorVista(String nombre, String apellidoMaterno, String apellidoPaterno, Double mapaMentalpromedio,
                                        Double relacionarimagenPromedio, Double gramaticaPromedio, Double relacionaroracionPromedio,
                                        Double oracionesPromedio, Double completarPromedio) {
        AlumnoEntidad alumnoEntidad = new AlumnoEntidad();
        alumnoEntidad.setNombre(nombre);
        alumnoEntidad.setApellidoPaterno(apellidoPaterno);
        alumnoEntidad.setApellidoMaterno(apellidoMaterno);
        this.mapaMentalpromedio = mapaMentalpromedio.shortValue();
        this.relacionarimagenPromedio = relacionarimagenPromedio.shortValue();
        this.gramaticaPromedio = gramaticaPromedio.shortValue();
        this.relacionaroracionPromedio = relacionaroracionPromedio.shortValue();
        this.oracionesPromedio = oracionesPromedio.shortValue();
        this.completarPromedio = completarPromedio.shortValue();
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    public GrupoEntidad getGrupoEntidad() {
        return grupoEntidad;
    }

    public void setGrupoEntidad(GrupoEntidad grupoEntidad) {
        this.grupoEntidad = grupoEntidad;
    }

    @Column(name = "mapa_mental_promedio")
    public Short getMapaMentalpromedio() {
        return mapaMentalpromedio;
    }

    public void setMapaMentalpromedio(Short mapaMentalpromedio) {
        this.mapaMentalpromedio = mapaMentalpromedio;
    }

    @Column(name = "relacionar_imagen_promedio")
    public Short getRelacionarimagenPromedio() {
        return relacionarimagenPromedio;
    }

    public void setRelacionarimagenPromedio(Short relacionarimagenPromedio) {
        this.relacionarimagenPromedio = relacionarimagenPromedio;
    }

    @Column(name = "gramatica_promedio")
    public Short getGramaticaPromedio() {
        return gramaticaPromedio;
    }

    public void setGramaticaPromedio(Short gramaticaPromedio) {
        this.gramaticaPromedio = gramaticaPromedio;
    }

    @Column(name = "relacionar_oracion_promedio")
    public Short getRelacionaroracionPromedio() {
        return relacionaroracionPromedio;
    }

    public void setRelacionaroracionPromedio(Short relacionaroracionPromedio) {
        this.relacionaroracionPromedio = relacionaroracionPromedio;
    }

    @Column(name = "oraciones_promedio")
    public Short getOracionesPromedio() {
        return oracionesPromedio;
    }

    public void setOracionesPromedio(Short oracionesPromedio) {
        this.oracionesPromedio = oracionesPromedio;
    }

    @Column(name = "completar_promedio")
    public Short getCompletarPromedio() {
        return completarPromedio;
    }

    public void setCompletarPromedio(Short completarPromedio) {
        this.completarPromedio = completarPromedio;
    }
}
