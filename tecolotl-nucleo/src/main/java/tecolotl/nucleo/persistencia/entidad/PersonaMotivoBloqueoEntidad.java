package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;

@Entity
@Table(name = "persona_motivo_bloqueo", schema = "nucleo", uniqueConstraints = {
        @UniqueConstraint(name = "Valor", columnNames = {"valor"})
})
@SequenceGenerator(name = "generador_defecto", schema = "nucleo", sequenceName = "persona_motivo_bloqueo_seq")
@NamedQueries(value = {
        @NamedQuery(
                name = "PersonaMotivoBloqueo.buscaFiltro",
                query = "SELECT pmb FROM PersonaMotivoBloqueoEntidad pmb WHERE NOT pmb.valor = :valor")
})
public class PersonaMotivoBloqueoEntidad extends CatalagoEntidad{

    public PersonaMotivoBloqueoEntidad() {
    }

    public PersonaMotivoBloqueoEntidad(Short clave) {
        super(clave);
    }
}
