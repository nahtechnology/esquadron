package tecolotl.nucleo.persistencia.entidad;

import tecolotl.nucleo.persistence.enumeracion.NivelLenguajeEnumeracion;

import javax.persistence.*;

@Entity
@Table(name = "tarea", schema = "nucleo")
public class TareaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int puntaje;

    private int tiempo;

    @Column(name = "pegunta_detonadora")
    private String preguntaDetonadora;

    private String video;

    private String transcripcion;

    @Enumerated(EnumType.STRING)
    private NivelLenguajeEnumeracion nivelLenguaje;
}
