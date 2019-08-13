package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.validacion.EscribirNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TareaModelo {

    private Integer id;
    private Date asignacion;
    private List<EscribirBaseModelo> escribirBaseModeloLista;

    public TareaModelo() {
    }

    public TareaModelo(TareaEntidad tareaEntidad) {
        this.id = tareaEntidad.getId();
        this.asignacion = tareaEntidad.getAsignacion();
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @NotNull(groups = {EscribirNuevoValidacion.class})
    @Valid
    @Size(min = 1, groups = {EscribirNuevoValidacion.class})
    public List<EscribirBaseModelo> getEscribirBaseModeloLista() {
        return escribirBaseModeloLista;
    }

    public void setEscribirBaseModeloLista(List<EscribirBaseModelo> escribirBaseModeloLista) {
        this.escribirBaseModeloLista = escribirBaseModeloLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaModelo that = (TareaModelo) o;
        return id.equals(that.id) &&
                asignacion.equals(that.asignacion) &&
                escribirBaseModeloLista.equals(that.escribirBaseModeloLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asignacion, escribirBaseModeloLista);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName()).append("=[")
                .append("id=").append(id).append(',')
                .append(" asignacion=").append(asignacion).append(',')
                .append(" escribirBaseModeloLista=[");
        for (EscribirBaseModelo escribirBaseModelo: escribirBaseModeloLista) {
            stringBuilder.append(escribirBaseModelo.toString());
        }
        stringBuilder.append(']').append(']');
        return stringBuilder.toString();
    }
}
