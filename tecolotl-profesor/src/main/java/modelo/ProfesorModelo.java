package modelo;

import javax.validation.constraints.NotNull;

public class ProfesorModelo {

    @NotNull
    private Integer id;
    //@NotNull
    //private EscuelaModelo escuelaModelo;

    public ProfesorModelo() {
    }

    public ProfesorModelo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public EscuelaModelo getEscuelaModelo() {
        return escuelaModelo;
    }

    public void setEscuelaModelo(EscuelaModelo escuelaModelo) {
        this.escuelaModelo = escuelaModelo;
    }*/
}
