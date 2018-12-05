package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="escuela", schema = "administracion")
@NamedQueries({
	@NamedQuery(name="LicenciaEscuelaEntidad.buscaestatus", query = "SELECT escuela.nombre, count(licencia.id_escuela) as total_licencias FROM administracion.escuela LEFT JOIN administracion.licencia ON escuela.clave_centro_trabajo = licencia.id_escuela GROUP BY licencia.id_escuela, escuela.nombre" )
})
public class LicenciaEscuelaEntidad {

	@Id
	@Column(name = "clave_centro_trabajo")
    private String claveCentroTrabajo;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_motivo_bloqueo", referencedColumnName = "id")
    private MotivoBloqueoEntidad motivoBloqueoEntidad;
    private boolean estatus;
    private int licencias;
    private int diasRestantes;
}
