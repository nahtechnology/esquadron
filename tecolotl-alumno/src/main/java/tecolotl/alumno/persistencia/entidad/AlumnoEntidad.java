package tecolotl.alumno.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@Table(name="alumno", schema="alumno")
@NamedQueries({
	@NamedQuery(name="AlumnoEntidad.findAll", query="SELECT a FROM AlumnoEntidad a"),
	@NamedQuery(name = "AlumnoEntidad.buscarId", query= "SELECT t FROM AlumnoEntidad t WHERE t.nombre=:nombre AND t.contrasenia=:password")
})
public class AlumnoEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Size(min= 2, max = 70)
	@Column(name="apellido_materno")
	private String apellidoMaterno;
	@NotNull
	@Size(min= 2, max = 50)
	@Column(name="apellido_paterno")
	private String apellidoPaterno;
	@NotNull
	@Size(min= 1, max = 15)
	private String apodo;
	@NotNull
	@Size(min= 1, max = 15)
	private String contrasenia;
	@NotNull
	@Size(min= 1, max = 15)
	@Column(name="contrasenia_padre_familia")
	private String contraseniaPadreFamilia;
	@Size(min= 7, max = 100)
	@Column(name="correo_electronico")
	private String correoElectronico;
	@NotNull
	@Size(min= 7, max = 100)
	@Column(name="correo_padre_familia")
	private String correoPadreFamilia;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date nacimiento;
	@NotNull
	@Size(min= 2, max = 2)
	@Column(name="nivel_ingles")
	private String nivelIngles;
	@NotNull
	@Size(min= 0, max = 3)
	private String nombre;

	//bi-directional many-to-one association to GradoEscolarEntidad
	@NotNull
	@Size(min= 0, max = 3)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grado_escolar")
	private GradoEscolarEntidad gradoEscolarBean;

	public AlumnoEntidad() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApodo() {
		return this.apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContraseniaPadreFamilia() {
		return this.contraseniaPadreFamilia;
	}

	public void setContraseniaPadreFamilia(String contraseniaPadreFamilia) {
		this.contraseniaPadreFamilia = contraseniaPadreFamilia;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoPadreFamilia() {
		return this.correoPadreFamilia;
	}

	public void setCorreoPadreFamilia(String correoPadreFamilia) {
		this.correoPadreFamilia = correoPadreFamilia;
	}

	public Date getNacimiento() {
		return this.nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNivelIngles() {
		return this.nivelIngles;
	}

	public void setNivelIngles(String nivelIngles) {
		this.nivelIngles = nivelIngles;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GradoEscolarEntidad getGradoEscolarBean() {
		return this.gradoEscolarBean;
	}

	public void setGradoEscolarBean(GradoEscolarEntidad gradoEscolarBean) {
		this.gradoEscolarBean = gradoEscolarBean;
	}

}