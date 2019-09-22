package tecolotl.alumno.modelo.hablar;

import tecolotl.alumno.entidad.hablar.TareaHablarEntidad;

public class HablarModelo {

    private Integer id;
    private String tarjeta;

    public HablarModelo() {
    }

    public HablarModelo(Integer id) {
        this.id = id;
    }

    public HablarModelo(TareaHablarEntidad tareaHablarEntidad) {
        this.id = tareaHablarEntidad.getTareaHablarEntidadPK().getHablarEntidad().getId();
        this.tarjeta = tareaHablarEntidad.getTareaHablarEntidadPK().getHablarEntidad().getTarjeta();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}
