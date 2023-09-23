package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Curso {
	private Integer id;
	private LocalDate fechaCurso;
	private Turnos turno;
	private Integer cupoMaximo;
	private Integer cupoActual;
	private List<Alumno> alumnosInscritos;
	private Set<Materia> correlativasRequeridas;
	private Materia materia;
	private CicloLectivo cicloLectivo;
	
	public Curso(Integer id, LocalDate fechaCurso, Turnos turno, int cupoMaximo) {
		this.id = id;
		this.fechaCurso = fechaCurso;
		this.turno = turno;
		this.cupoMaximo = cupoMaximo;
		this.alumnosInscritos = new ArrayList<>();
		this.correlativasRequeridas = new HashSet<>();
		this.cupoMaximo = cupoMaximo;
		this.alumnosInscritos = new ArrayList<Alumno>();
		this.correlativasRequeridas = new HashSet<Materia>();
	}

	public Curso(int id, Materia materia, CicloLectivo cicloLectivo, Turnos turno, Integer cupoMaximo) {
		this.id = id;
		this.materia = materia;
		this.cicloLectivo = cicloLectivo;
		this.turno = turno;
		this.cupoMaximo = cupoMaximo;
		this.cupoActual = 0;
		this.alumnosInscritos = new ArrayList<Alumno>();
		this.correlativasRequeridas = new HashSet<Materia>();
	}
	
	
	
	
	public Integer getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(Integer cupoActual) {
		this.cupoActual = cupoActual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaCurso() {
		return fechaCurso;
	}

	public void setFechaCurso(LocalDate fechaCurso) {
		this.fechaCurso = fechaCurso;
	}

	public Turnos getTurno() {
		return turno;
	}

	public void setTurno(Turnos turno) {
		this.turno = turno;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public List<Alumno> getAlumnosInscritos() {
		return alumnosInscritos;
	}

	public void setAlumnosInscritos(List<Alumno> alumnosInscritos) {
		this.alumnosInscritos = alumnosInscritos;
	}

	public void agregarCorrelativa(Materia correlativa) {
		correlativasRequeridas.add(correlativa);
	}

	public Set<Materia> getCorrelativas() {
		return correlativasRequeridas;
	}

	public Set<Materia> getCorrelativasRequeridas() {
		return correlativasRequeridas;
	}

	public void setCorrelativasRequeridas(Set<Materia> correlativasRequeridas) {
		this.correlativasRequeridas = correlativasRequeridas;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public void setCupoMaximo(Integer cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

		public void agregarAlumnoInscrito(Alumno alumno) {
		if (alumno != null) {
			if (cupoActual < cupoMaximo) {
				alumnosInscritos.add(alumno);
				cupoActual++;
			}
		}
	
		
	}

	public boolean estaInscrito(Alumno alumno) {
		
		return alumnosInscritos.contains(alumno);
	}
	
	

}
