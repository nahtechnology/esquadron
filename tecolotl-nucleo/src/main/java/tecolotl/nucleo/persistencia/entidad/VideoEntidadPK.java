package tecolotl.nucleo.persistencia.entidad;

import java.io.Serializable;

public class VideoEntidadPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short idNivelLenguaje;
    private short idLenguaje;
    private int id;

    public short getIdNivelLenguaje() {
        return idNivelLenguaje;
    }

    public void setIdNivelLenguaje(short idNivelLenguaje) {
        this.idNivelLenguaje = idNivelLenguaje;
    }

    public short getIdLenguaje() {
        return idLenguaje;
    }

    public void setIdLenguaje(short idLenguaje) {
        this.idLenguaje = idLenguaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
