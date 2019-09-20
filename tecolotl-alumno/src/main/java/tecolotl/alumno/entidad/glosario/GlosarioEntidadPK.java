package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GlosarioEntidadPK implements Serializable {

    private String palabra;
    private ClaseGlosarioEntidad claseGlosarioEntidad;

    public GlosarioEntidadPK() {
    }

    public GlosarioEntidadPK(String palabra, ClaseGlosarioEntidad claseGlosarioEntidad) {
        this.palabra = palabra;
        this.claseGlosarioEntidad = claseGlosarioEntidad;
    }

    @Basic
    @Column(name = "palabra")
    @NotNull
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase_glosario", referencedColumnName = "clave")
    @NotNull
    public ClaseGlosarioEntidad getClaseGlosarioEntidad() {
        return claseGlosarioEntidad;
    }

    public void setClaseGlosarioEntidad(ClaseGlosarioEntidad claseGlosarioEntidad) {
        this.claseGlosarioEntidad = claseGlosarioEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlosarioEntidadPK that = (GlosarioEntidadPK) o;
        return palabra.equals(that.palabra) &&
                claseGlosarioEntidad.equals(that.claseGlosarioEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabra, claseGlosarioEntidad);
    }

    @Override
    public String toString() {
        return "GlosarioEntidadPK{" +
                "palabra='" + palabra + '\'' +
                ", claseGlosarioEntidad=" + claseGlosarioEntidad +
                '}';
    }

}
