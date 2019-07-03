package tecolotl.profesor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "public", name="pruebas")
public class PruebaCipherEntity {
    private String contrasenia;
    private String key;
    /*@NotNull
    @Column
    @ColumnTransformer()
        read = "pgp_sym_decrypt(pswd, '2ssdfs23kj234k5kj35jkl3l2', 'compress-algo=0, cipher-algo=aes256')",
        write = "pgp_sym_encrypt(?, '2ssdfs23kj234k5kj35jkl3l2', 'compress-algo=0, cipher-algo=aes256')"
    )*/
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
