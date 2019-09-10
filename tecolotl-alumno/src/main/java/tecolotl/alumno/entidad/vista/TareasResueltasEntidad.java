package tecolotl.alumno.entidad.vista;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.StringJoiner;

@Entity
public class TareasResueltasEntidad {

    private String tarea;
    private Integer total;
    private Integer respuesta;

    @Id
    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareasResueltasEntidad.class.getSimpleName() + "[", "]")
                .add("tarea='" + tarea + "'")
                .add("total=" + total)
                .add("respuesta=" + respuesta)
                .toString();
    }
}
