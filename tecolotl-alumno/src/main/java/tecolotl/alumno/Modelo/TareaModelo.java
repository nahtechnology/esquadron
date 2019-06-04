package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TareaModelo {

    @NotNull
    private Integer id;

    @NotNull
    @Valid
    private AlumnoModelo alumnoModelo;

    @NotNull
    private Date asignacion;

    public TareaModelo(){
    }

    public TareaModelo(Integer id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad){
        this.id = tareaEntidad.getId();
        this.alumnoModelo = new AlumnoModelo(tareaEntidad.getAlumnoEntidad());    //Preguntar Mañana a Toño
        this.asignacion = tareaEntidad.getAsignacion();
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
}
