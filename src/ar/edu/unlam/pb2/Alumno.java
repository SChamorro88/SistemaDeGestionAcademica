package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Alumno {

	private int id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private Integer dni;
	private Set<Materia> materiasAprobadas;
	private List<Curso> cursosInscritos;
	private List<Nota> notasRegistradas;
	private List<Materia> materiasCursadas;
	private Map<Materia, Double> notasPorMateria;

	public Alumno(int id, Integer dni, String nombre, String apellido, LocalDate fechaNacimiento,
			LocalDate fechaIngreso) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.dni = dni;
		this.materiasAprobadas = new HashSet<>();
		this.cursosInscritos = new ArrayList<Curso>();
		this.notasRegistradas = new ArrayList<Nota>();
		this.materiasCursadas = new ArrayList<Materia>();
		this.notasPorMateria = new HashMap<Materia, Double>();

	}

	public Map<Materia, Double> getNotasPorMateria() {
		return notasPorMateria;
	}

	public void setNotasPorMateria(Map<Materia, Double> notasPorMateria) {
		this.notasPorMateria = notasPorMateria;
	}

	public Alumno(int id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public List<Materia> getMateriasCursadas() {
		return materiasCursadas;
	}

	public void setMateriasCursadas(List<Materia> materiasCursadas) {
		this.materiasCursadas = materiasCursadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Set<Materia> getMateriasAprobadas() {
		return materiasAprobadas;
	}

	public void setMateriasAprobadas(Set<Materia> materiasAprobadas) {
		this.materiasAprobadas = materiasAprobadas;
	}

	public List<Curso> getCursosInscritos() {
		return cursosInscritos;
	}

	public void setCursosInscritos(List<Curso> cursosInscritos) {
		this.cursosInscritos = cursosInscritos;
	}

	public List<Nota> getNotasRegistradas() {
		return notasRegistradas;
	}

	public void setNotasRegistradas(List<Nota> notasRegistradas) {
		this.notasRegistradas = notasRegistradas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(dni, other.dni);
	}
	// -----------------------------------------------------------------------------------------------------
	public boolean tieneCorrelativaAprobada(Materia correlativa) {
		for (Materia materiaAprobada : materiasAprobadas) {
			if (materiaAprobada.equals(correlativa) && materiaAprobada.getnotaMateria() >= 4) {
				return true;
			}
		}
		return false;
	}

	public boolean estaInscritoEnOtroCurso(Curso curso) {
		for (Curso cursoInscrito : cursosInscritos) {
			if (cursoInscrito != curso && cursoInscrito.getFechaCurso().equals(curso.getFechaCurso())
					&& cursoInscrito.getTurno() == curso.getTurno()) {
				return true;

			}
		}
		return false;

	}

	public void inscribirseACurso(Curso curso) {
		if (curso != null) {
			if (!cursosInscritos.contains(curso)) {
				cursosInscritos.add(curso);
				curso.agregarAlumnoInscrito(this);
			}
		}

	}

	public boolean existeNotaDelMismoTipo(Nota nota) {
		// Verifica que ya existen una nota del mismo tipo en las notas registradas
		return notasRegistradas.stream().anyMatch(n -> n.getTipo().equals(nota.getTipo()));
	}

	public void registrarNota(Nota nota) {
		notasRegistradas.add(nota);

	}

	public void aprobarMateria(Materia fisica) {
		materiasAprobadas.add(fisica);

	}

	public void agregarMateriaCursada(Materia materia) {
		materiasCursadas.add(materia);
	}

	public void asignarNotaEnMateria(Materia materia, Double nota) {
		notasPorMateria.put(materia, nota);
	}

	public double obtenerNotaEnMateria(Materia materia) {
		// Verificar si el alumno tiene una nota registrada para la materia dada
		if (notasPorMateria.containsKey(materia)) {
			return notasPorMateria.get(materia);
		} else {
			return -1.0; // Si no hay nota registrada, puedes devolver un valor especial (por ejemplo,
							// -1)
		}
	}
}
