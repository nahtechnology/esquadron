package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class EscribirEntidadPK implements Serializable {
    private List<ActividadEntidad> actividadEntidadLista;
    private Short contador;

    @OneToMany
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
     public List<ActividadEntidad> getActividadEntidadLista() {
        return actividadEntidadLista;
    }

    public void setActividadEntidadLista(List<ActividadEntidad> actividadEntidadLista) {
        this.actividadEntidadLista = actividadEntidadLista;
    }

    @Basic
    @Column(name = "contador")
    @NotNull
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscribirEntidadPK that = (EscribirEntidadPK) o;
        return actividadEntidadLista.equals(that.actividadEntidadLista) &&
                contador.equals(that.contador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadEntidadLista, contador);
    }
}
