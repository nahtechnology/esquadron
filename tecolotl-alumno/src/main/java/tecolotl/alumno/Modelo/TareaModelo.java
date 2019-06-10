package tecolotl.alumno.modelo;


import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TareaModelo {

    @NotNull
    private Integer id;

    @NotNull
    @Valid
    private tecolotl.alumno.modelo.AlumnoModelo alumnoModelo;

    @NotNull
    private Date asignacion;

    public TareaModelo(){
    }

    public TareaModelo(Integer id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad){
        this.id = tareaEntidad.getId();
        this.alumnoModelo = new tecolotl.alumno.modelo.AlumnoModelo(tareaEntidad.getAlumnoEntidad());    //Preguntar Mañana a Toño
        this.asignacion = tareaEntidad.getAsignacion();
    }



    public TareaModelo(tecolotl.alumno.modelo.AlumnoModelo alumnoModelo, Date asignacion) {
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

    public tecolotl.alumno.modelo.AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(tecolotl.alumno.modelo.AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }
}
