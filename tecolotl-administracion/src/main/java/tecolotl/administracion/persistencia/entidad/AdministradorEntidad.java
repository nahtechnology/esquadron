package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;


@Entity
@Table(name = "administrador", schema = "administracion")
@NamedQueries(value = {
        @NamedQuery(
                name = "AdministradorEntidad.buscaApodo",
                query = "SELECT a FROM AdministradorEntidad a WHERE a.apodo = :apodo")
})
public class AdministradorEntidad extends PersonaEntidad {

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
