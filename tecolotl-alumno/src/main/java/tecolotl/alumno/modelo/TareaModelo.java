package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.modelo.mapamental.MapaMentalBaseModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.validacion.escribir.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TareaModelo {

    private Integer id;
    private Date asignacion;
    private List<MapaMentalBaseModelo> mapaMentalBaseModeloLista;
    private List<GlosarioModelo> glosarioModeloLista;

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
    public List<MapaMentalBaseModelo> getMapaMentalBaseModeloLista() {
        return mapaMentalBaseModeloLista;
    }

    public void setMapaMentalBaseModeloLista(List<MapaMentalBaseModelo> mapaMentalBaseModeloLista) {
        this.mapaMentalBaseModeloLista = mapaMentalBaseModeloLista;
    }

    @NotNull(groups = {GlosarioNuevoValidacion.class})
    @Valid
    @Size(min = 1, groups = {GlosarioNuevoValidacion.class})
    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaModelo that = (TareaModelo) o;
        return id.equals(that.id) &&
                asignacion.equals(that.asignacion) &&
                mapaMentalBaseModeloLista.equals(that.mapaMentalBaseModeloLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asignacion, mapaMentalBaseModeloLista);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName()).append("=[")
                .append("id=").append(id).append(',')
                .append(" asignacion=").append(asignacion).append(',')
                .append(" escribirBaseModeloLista=[");
        for (MapaMentalBaseModelo mapaMentalBaseModelo : mapaMentalBaseModeloLista) {
            stringBuilder.append(mapaMentalBaseModelo.toString());
        }
        stringBuilder.append(']').append(']');
        return stringBuilder.toString();
    }
}
