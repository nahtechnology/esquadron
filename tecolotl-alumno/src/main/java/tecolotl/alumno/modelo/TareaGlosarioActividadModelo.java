package tecolotl.alumno.modelo;

import java.util.StringJoiner;

public class TareaGlosarioActividadModelo {
    private GlosarioActividadModelo glosarioActividadModelo;
    private TareaModelo tareaModelo;

    public GlosarioActividadModelo getGlosarioActividadModelo() {
        return glosarioActividadModelo;
    }

    public void setGlosarioActividadModelo(GlosarioActividadModelo glosarioActividadModelo) {
        this.glosarioActividadModelo = glosarioActividadModelo;
    }

    public TareaModelo getTareaModelo() {
        return tareaModelo;
    }

    public void setTareaModelo(TareaModelo tareaModelo) {
        this.tareaModelo = tareaModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaGlosarioActividadModelo.class.getSimpleName() + "[", "]")
                .add("glosarioActividadModelo=" + glosarioActividadModelo)
                .add("tareaModelo=" + tareaModelo)
                .toString();
    }
}
