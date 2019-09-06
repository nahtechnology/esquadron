package tecolotl.alumno.modelo.vista;

import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;

public class TareaResuetasModelo {

    private String tarea;
    private Integer total;
    private Integer respuesta;

    public TareaResuetasModelo() {
    }

    public TareaResuetasModelo(TareasResueltasEntidad tareasResueltasEntidad) {
        this.tarea = tareasResueltasEntidad.getTarea();
        this.total = tareasResueltasEntidad.getTotal();
        this.respuesta = tareasResueltasEntidad.getRespuesta();
    }

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

}
