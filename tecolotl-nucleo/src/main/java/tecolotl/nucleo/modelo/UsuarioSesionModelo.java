package tecolotl.nucleo.modelo;

import java.util.Date;
import java.util.UUID;

public class UsuarioSesionModelo {

    private Integer galaxia;
    private String apodo;
    private Date momento;
    private UUID id;
    private Tipo tipo;

    public enum Tipo {
        ALUMNO, PROFESOR
    }

    public enum Registro {
        INICIO, CIERRE;

        public Integer getValue() {
            return ordinal() + 1;
        }
    }

    public Integer getGalaxia() {
        return galaxia;
    }

    public void setGalaxia(Integer galaxia) {
        this.galaxia = galaxia;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsuarioSesionModelo{");
        sb.append("galaxia=").append(galaxia);
        sb.append(", apodo='").append(apodo).append('\'');
        sb.append(", momento=").append(momento);
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
}
