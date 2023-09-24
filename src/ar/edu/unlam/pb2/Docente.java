package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Docente {

	private Integer dni;
	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimientoDocente;
	private LocalDate fechaIngesoDocente;
	private Set<Curso> cursosAsignados;

	public Docente(Integer id, Integer dni, String nombre, String apellido, LocalDate fechaNacimientoDocente,
			LocalDate fechaIngesoDocente) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimientoDocente = fechaNacimientoDocente;
		this.fechaIngesoDocente = fechaIngesoDocente;
		this.cursosAsignados = new HashSet<Curso>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimientoDocente() {
		return fechaNacimientoDocente;
	}

	public void setFechaNacimientoDocente(LocalDate fechaNacimientoDocente) {
		this.fechaNacimientoDocente = fechaNacimientoDocente;
	}

	public LocalDate getFechaIngesoDocente() {
		return fechaIngesoDocente;
	}

	public void setFechaIngesoDocente(LocalDate fechaIngesoDocente) {
		this.fechaIngesoDocente = fechaIngesoDocente;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	// -------------------------------------------------------------------------------

	public void asignarCurso(Curso curso) {
		cursosAsignados.add(curso);
	}

	public boolean tieneCurso(Curso curso) {
		return cursosAsignados.contains(curso);
	}

}
