package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;

@Entity
@Table(name = "coordinador_motivo_bloqueo", schema = "administracion")
@SequenceGenerator(name = "generador_defecto", schema = "administracion", sequenceName = "coordinador_motivo_bloqueo_seq")
@NamedQueries(value = {
        @NamedQuery(
                name = "CoordinadorMotivoBloqueoEntidad.buscaFiltro",
                query = "SELECT c FROM CoordinadorMotivoBloqueoEntidad c WHERE NOT c.valor = :tipoCatalogo")
})
public class CoordinadorMotivoBloqueoEntidad extends CatalagoEntidad {

    public CoordinadorMotivoBloqueoEntidad() {
    }

    public CoordinadorMotivoBloqueoEntidad(Short clave) {
        super(clave);
    }
}
