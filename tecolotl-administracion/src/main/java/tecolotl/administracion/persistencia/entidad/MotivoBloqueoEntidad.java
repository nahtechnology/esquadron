package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;

@Entity
@Table(name = "motivo_bloqueo", schema ="administracion")
@SequenceGenerator(name = "generador_defecto", sequenceName = "motivo_bloqueo_seq", schema = "administracion", allocationSize = 1)
@NamedQueries({
		@NamedQuery(name = "MotivoBloqueoEntidad.busca", query = "SELECT m FROM MotivoBloqueoEntidad m WHERE NOT m.valor = :valor ORDER BY m.valor")
})
public class MotivoBloqueoEntidad extends CatalagoEntidad {

	public MotivoBloqueoEntidad() { }

	public MotivoBloqueoEntidad(Short clave) {
		super(clave);
	}

}
