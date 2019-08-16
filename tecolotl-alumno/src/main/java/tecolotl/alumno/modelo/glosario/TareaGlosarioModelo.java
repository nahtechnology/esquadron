package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.modelo.TareaModelo;

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
