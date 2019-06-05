package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TareaModelo {

    @NotNull
    private Integer id;

    @NotNull
    @Valid
    private AlumnoModelo alumnoModelo;

    @NotNull
    private Date asignacion;

    private List<TareaGlosarioModelo> tareaGlosarioModeloLista;

    public TareaModelo(){
    }

    public TareaModelo(Integer id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad){
        this(tareaEntidad.getId());
        this.asignacion = tareaEntidad.getAsignacion();
        tareaGlosarioModeloLista = tareaEntidad.getTareaGlosarioActividadEntidadList()
                .stream().map(TareaGlosarioModelo::new).collect(Collectors.toList());
    }

    public TareaModelo(AlumnoModelo alumnoModelo, Date asignacion) {
        this.alumnoModelo = alumnoModelo;
        this.asignacion = asignacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<TareaGlosarioModelo> getTareaGlosarioModeloLista() {
        return tareaGlosarioModeloLista;
    }

    public void setTareaGlosarioModeloLista(List<TareaGlosarioModelo> tareaGlosarioModeloLista) {
        this.tareaGlosarioModeloLista = tareaGlosarioModeloLista;
    }
}
