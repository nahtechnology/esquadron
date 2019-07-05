package tecolotl.profesor.entidad;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "pruebas")
@SequenceGenerator(name = "secuencia", schema = "public", sequenceName = "pruebas_id_seq")
@NamedQueries(
    @NamedQuery(name = "PruebaCipherEntidad.BuscaPSWD", query = "SELECT pswd FROM PruebaCipherEntidad pswd")
)
public class PruebaCipherEntidad {
    private Integer id;
    private String password;
    private String key;

    @Id
    @GeneratedValue(generator = "secuencia", strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "pswd")
    @ColumnTransformer(
            read = "pgp_sym_decrypt(password, key, 'compress-algo=0, cipher-algo=aes256')",
            write = "pgp_sym_encrypt(?, key, 'compress-algo=0, cipher-algo=aes256')"
    )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
