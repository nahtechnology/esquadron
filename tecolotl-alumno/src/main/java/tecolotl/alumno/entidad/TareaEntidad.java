package tecolotl.alumno.entidad;

import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(name = "tarea", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(
        name = "TareaEntidad.busca",
        query = "SELECT t FROM TareaEntidad t"),
    @NamedQuery(
        name = "TareaEntidad.buscaActividad",
        query = "SELECT t FROM TareaEntidad t JOIN t.tareaGlosarioActividadEntidadLista tga WHERE " +
            "t.alumnoEntidad.id = :IdAlumno GROUP BY t ORDER BY t.id"),
    @NamedQuery(
        name = "TareaEntidad.buscaAlumnoGrupo",
        query = "SELECT t FROM TareaEntidad t WHERE t.alumnoEntidad.id = :idAlumno AND t.idGrupo = :idGrupo"),
    @NamedQuery(
        name = "TareaEntidad.aumentaReprodecciones",
        query = "UPDATE TareaEntidad t SET t.reproducciones = t.reproducciones + :reproducciones WHERE t.id = :idTarea"),
    @NamedQuery(
        name = "TareaEntidad.respuesta",
        query = "UPDATE TareaEntidad t SET t.respuesta = :respuesta, t.calificacion = :calificacion, t.resolviendoTranscript = FALSE WHERE t.id = :idTarea"),
    @NamedQuery(
        name = "TareaEntidad.estatusRespondiendo",
        query = "UPDATE TareaEntidad t SET t.resolviendoTranscript = :estatus WHERE t.id = :idTarea"),
    @NamedQuery(
        name = "TareaEntidad.eliminaGrupo",
        query = "DELETE FROM TareaEntidad t WHERE t.id IN (SELECT t.id FROM TareaEntidad t join t.tareaGlosarioActividadEntidadLista tga " +
                "WHERE tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad.glosarioActividadEntidadPK.actividadEntidad.id = :idActividad) AND " +
                "t.idGrupo = :idGrupo"),
    @NamedQuery(
        name = "TareaEntidad.actualizaGrupo",
        query = "UPDATE TareaEntidad t SET t.idGrupo = :nuevoGrupo WHERE t.alumnoEntidad.id = :idAlumno AND t.idGrupo = :viejoGrupo")
})
public class TareaEntidad {

    private UUID id;
    private Short reproducciones;
    private Date asignacion;
    private String respuesta;
    private Date horaRespuesta;
    private boolean resolviendoTranscript;
    private AlumnoEntidad alumnoEntidad;
    private UUID idGrupo;
    private Short calificacion;
    private boolean verRespuesta;
    private List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista;
    private List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista;
    private List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista;

    public TareaEntidad() {
    }

    public TareaEntidad(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reproducciones")
    public Short getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Short reproducciones) {
        this.reproducciones = reproducciones;
    }

    @Basic
    @Column(name = "asignacion", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @Basic
    @Column(name = "respuesta")
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Basic
    @Column(name = "hora_respuesta", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Basic
    @Column(name = "resolviendo_transcript")
    public boolean isResolviendoTranscript() {
        return resolviendoTranscript;
    }

    public void setResolviendoTranscript(boolean resolviendoTranscript) {
        this.resolviendoTranscript = resolviendoTranscript;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @Basic
    @NotNull
    @Column(name = "id_grupo")
    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Basic
    @Column(name = "calificacion", insertable = false)
    public Short getCalificacion() {
        return calificacion;
    }

    @Basic
    @Column(name = "ver_respuesta")
    public boolean isVerRespuesta() {
        return verRespuesta;
    }

    public void setVerRespuesta(boolean verRespuesta) {
        this.verRespuesta = verRespuesta;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }

    @OneToMany(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad", cascade = {CascadeType.PERSIST})
    public List<TareaGlosarioActividadEntidad> getTareaGlosarioActividadEntidadLista() {
        return tareaGlosarioActividadEntidadLista;
    }

    public void setTareaGlosarioActividadEntidadLista(List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista) {
        this.tareaGlosarioActividadEntidadLista = tareaGlosarioActividadEntidadLista;
    }

    @OneToMany(mappedBy = "tareaMapaMentalActividadEntidadPK.tareaEntidad", cascade = CascadeType.PERSIST)
    public List<TareaMapaMentalActividadEntidad> getTareaMapaMentalActividadEntidadLista() {
        return tareaMapaMentalActividadEntidadLista;
    }

    public void setTareaMapaMentalActividadEntidadLista(List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista) {
        this.tareaMapaMentalActividadEntidadLista = tareaMapaMentalActividadEntidadLista;
    }

    @OneToMany(mappedBy = "tareaRelacionarActividadEntidadPK.tareaEntidad")
    public List<TareaRelacionarActividadEntidad> getTareaRelacionarActividadEntidadLista() {
        return tareaRelacionarActividadEntidadLista;
    }

    public void setTareaRelacionarActividadEntidadLista(List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista) {
        this.tareaRelacionarActividadEntidadLista = tareaRelacionarActividadEntidadLista;
    }

    @PrePersist
    public void creaUUID(){
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("reproducciones=" + reproducciones)
                .add("asignacion=" + asignacion)
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .add("resolviendoTranscript=" + resolviendoTranscript)
                .add("alumnoEntidad=" + alumnoEntidad)
                .add("calificacion=" + calificacion)
                .add("tareaGlosarioActividadEntidadLista=" + tareaGlosarioActividadEntidadLista)
                .add("tareaMapaMentalActividadEntidadLista=" + tareaMapaMentalActividadEntidadLista)
                .add("tareaRelacionarActividadEntidadLista=" + tareaRelacionarActividadEntidadLista)
                .toString();
    }
}
