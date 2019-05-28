package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TareaModelo {

    @NotNull
    private Integer id;
    @NotNull
    private AlumnoEntidad alumnoEntidad;

    @NotNull
    private Date asignacion;

    public TareaModelo(){
    }

    public TareaModelo(TareaEntidad tareaEntidad){
        this.id = tareaEntidad.getId();
        this.alumnoEntidad = tareaEntidad.getAlumnoEntidad();
        this.asignacion = tareaEntidad.getAsignacion();
    }

    public TareaModelo(Integer id, AlumnoEntidad alumnoEntidad, Date asignacion) {
        this.id = id;
        this.alumnoEntidad = alumnoEntidad;
        this.asignacion = asignacion;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }
}
