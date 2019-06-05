package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaGlosarioActividadEntidad;

public class TareaGlosarioModelo {

    private TareaModelo tareaModelo;

    public TareaGlosarioModelo() {
    }

    public TareaGlosarioModelo(TareaGlosarioActividadEntidad tareaGlosarioActividadEntidad) {
        tareaModelo = new TareaModelo(tareaGlosarioActividadEntidad.getTareaGlosarioActividadEntidadPK().getTareaEntidad());
    }

    public TareaModelo getTareaModelo() {
        return tareaModelo;
    }

    public void setTareaModelo(TareaModelo tareaModelo) {
        this.tareaModelo = tareaModelo;
    }

}
