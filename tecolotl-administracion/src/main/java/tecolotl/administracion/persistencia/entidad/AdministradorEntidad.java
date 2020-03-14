package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;

@Entity
@Table(name = "admnistrador", schema = "administracion")
@NamedQueries(value = {
        @NamedQuery(
                name = "AdministradorEntidad.buscaApodo",
                query = "SELECT a FROM AdministradorEntidad a WHERE a.apodo = :apodo")
})
public class AdministradorEntidad extends PersonaEntidad {

    @Id
    @Override
    public String getApodo() {
        return super.getApodo();
    }

}
