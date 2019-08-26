package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tema", schema = "alumno")
@SequenceGenerator(name = "generador_defecto", sequenceName = "tema_seq", schema = "alumno")
@NamedQuery(name = "TemaEntidad.busca", query = "SELECT t FROM TemaEntidad t")
public class TemaEntidad extends CatalagoEntidad {

    private List<ActividadEntidad> actividadEntidadLista;

    public TemaEntidad() {
    }

    public TemaEntidad(Short clave) {
        super(clave);
    }

    @OneToMany(mappedBy = "temaEntidad")
    public List<ActividadEntidad> getActividadEntidadLista() {
        return actividadEntidadLista;
    }

    public void setActividadEntidadLista(List<ActividadEntidad> actividadEntidadLista) {
        this.actividadEntidadLista = actividadEntidadLista;
    }
}
