package tecolotl.administracion.modelo.coordinador;

import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.validacion.escuela.CoordinadorLlavePrimaria;
import tecolotl.administracion.validacion.escuela.CoordinadorNuevoValidacion;
import tecolotl.nucleo.modelo.PersonaModelo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.StringJoiner;

public class CoordinadorModelo extends PersonaModelo {

    private String claveCentroTrabajo;
    private Short contador;
    private String correoEletronico;
    private CoordinadorMotivoBloqueoModelo coordinadorMotivoBloqueoModelo;

    public CoordinadorModelo() {
    }

    public CoordinadorModelo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public CoordinadorModelo(String claveCentroTrabajo, Short contador) {
        this.claveCentroTrabajo = claveCentroTrabajo;
        this.contador = contador;
    }

    public CoordinadorModelo(CoordinadorEntidad coordinadorEntidad) {
        super(coordinadorEntidad);
        contador = coordinadorEntidad.getCoordinadorEntidadPK().getContador();
        claveCentroTrabajo = coordinadorEntidad.getCoordinadorEntidadPK().getEscuelaEntidad().getClaveCentroTrabajo();
        correoEletronico = coordinadorEntidad.getCorreoEletronico();
        coordinadorMotivoBloqueoModelo = new CoordinadorMotivoBloqueoModelo(coordinadorEntidad.getCoordinadorMotivoBloqueoEntidad().getClave());
    }

    @NotNull(groups = {CoordinadorLlavePrimaria.class, CoordinadorNuevoValidacion.class})
    @Size(min = 10, max = 14, groups = {CoordinadorLlavePrimaria.class, CoordinadorNuevoValidacion.class})
    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    @NotNull(groups = {CoordinadorLlavePrimaria.class})
    @Min(value = 1, groups = {CoordinadorLlavePrimaria.class})
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @NotNull(groups = {CoordinadorNuevoValidacion.class})
    @Size(max = 100, min = 3, groups = {CoordinadorNuevoValidacion.class})
    public String getCorreoEletronico() {
        return correoEletronico;
    }

    public void setCorreoEletronico(String correoEletronico) {
        this.correoEletronico = correoEletronico;
    }

    public CoordinadorMotivoBloqueoModelo getCoordinadorMotivoBloqueoModelo() {
        return coordinadorMotivoBloqueoModelo;
    }

    public void setCoordinadorMotivoBloqueoModelo(CoordinadorMotivoBloqueoModelo coordinadorMotivoBloqueoModelo) {
        this.coordinadorMotivoBloqueoModelo = coordinadorMotivoBloqueoModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoordinadorModelo.class.getSimpleName() + "[", "]")
                .add("claveCentroTrabajo='" + claveCentroTrabajo + "'")
                .add("contador=" + contador)
                .add("correoEletronico='" + correoEletronico + "'")
                .add(super.toString())
                .toString();
    }

}
