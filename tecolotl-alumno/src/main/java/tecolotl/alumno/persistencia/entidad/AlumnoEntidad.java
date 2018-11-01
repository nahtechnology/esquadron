package tecolotl.alumno.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@Table(name="alumno")
@NamedQuery(name="AlumnoEntidad.findAll", query="SELECT a FROM AlumnoEntidad a")
public class AlumnoEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="apellido_materno")
	private String apellidoMaterno;

	@Column(name="apellido_paterno")
	private String apellidoPaterno;

	private String apodo;

	private String contrasenia;

	@Column(name="contrasenia_padre_familia")
	private String contraseniaPadreFamilia;

	@Column(name="correo_electronico")
	private String correoElectronico;

	@Column(name="correo_padre_familia")
	private String correoPadreFamilia;

	@Temporal(TemporalType.DATE)
	private Date nacimiento;

	@Column(name="nivel_ingles")
	private String nivelIngles;

	private String nombre;

	//bi-directional many-to-one association to GradoEscolarEntidad
	@ManyToOne
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