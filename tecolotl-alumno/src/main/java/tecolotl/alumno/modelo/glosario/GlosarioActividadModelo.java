package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.modelo.ActividadModelo;

import java.util.StringJoiner;

public class GlosarioActividadModelo {
    private GlosarioModelo glosarioModelo;
    private ActividadModelo actividadModelo;

    public GlosarioModelo getGlosarioModelo() {
        return glosarioModelo;
    }

    public void setGlosarioModelo(GlosarioModelo glosarioModelo) {
        this.glosarioModelo = glosarioModelo;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioActividadModelo.class.getSimpleName() + "[", "]")
                .add("glosarioModelo=" + glosarioModelo)
                .add("actividadModelo=" + actividadModelo)
                .toString();
    }
}
