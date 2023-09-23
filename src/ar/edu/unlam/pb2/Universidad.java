package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Universidad {

	private int id;
	private String nombre;
	private Map<Integer, Materia> materias;
	private Set<Alumno> alumnos;
	private Set<Docente> docentes;
	private Map<Integer, CicloLectivo> cicloElectivos;
	private List<Comision> comisiones;
	private List<Aula> aulas;
	private List<Curso> cursos;

	public Universidad(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.materias = new HashMap<Integer, Materia>();
		this.alumnos = new HashSet<Alumno>();
		this.docentes = new HashSet<Docente>();
		this.cicloElectivos = new HashMap<Integer, CicloLectivo>();
		this.comisiones = new ArrayList<Comision>();
		this.aulas = new ArrayList<Aula>();
		this.cursos = new ArrayList<Curso>();
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

	// -------------------------------------------------------------------------------------------
	public void agregarMateria(Materia materia) {
		if (!materias.containsValue(materia)) {
			this.materias.put(materia.getId(), materia);
		} else {
			throw new IllegalArgumentException("Materia ya existente");
		}

	}

	public void agregarAlumno(Alumno alumno) {
		if (!alumnos.contains(alumno)) {
			this.alumnos.add((Alumno) alumno);
		} else {
			throw new IllegalArgumentException("Alumno ya existente");
		}
	}

	public void agregarCicloLectivo(CicloLectivo nuevoCiclo) {
		Boolean existeCiclo = cicloElectivos.values().stream().anyMatch(c -> c.getIdCiclo() == nuevoCiclo.getIdCiclo());

		if (existeCiclo) {
			throw new IllegalArgumentException("Error: ya existe un ciclo electivo con este ID");
		} else {
			cicloElectivos.put(nuevoCiclo.getIdCiclo(), nuevoCiclo);
		}

	}

	public void agregarDocente(Docente docente) {
		this.docentes.add(docente);

	}

	public void agregarComision(Comision comision) {
		// Verificar si ya existe una comisión para la misma materia, ciclo lectivo y
		// turno
		boolean existeComision = comisiones.stream().anyMatch(c -> c.getMateria().equals(comision.getMateria())
				&& c.getCicloElectivo().equals(comision.getCicloElectivo()) && c.getTurno() == comision.getTurno());

		if (existeComision) {
			throw new IllegalArgumentException(
					"Error: Ya existe una comisión para la misma materia, ciclo lectivo y turno.");
		} else {
			comisiones.add(comision);
		}
	}

	public void agregarAula(Aula aula) {
		this.aulas.add(aula);
	}

	// -------------------------------------------------------------------------------------------
	public Integer obtenerCantidadDeDocentes() {
		return this.docentes.size();
	}

	public Integer obtenerCantidadDeCiclosLectivos() {
		return this.cicloElectivos.size();
	}

	public Integer obtenerCantidadDeAlumnos() {
		return this.alumnos.size();
	}

	public Integer obtenerCantidadDeMaterias() {
		return this.materias.size();
	}

	public Integer obtenerCantidadDeComisiones() {
		return this.comisiones.size();
	}

	public Integer obtenerCantidadDeAulas() {
		return this.aulas.size();
	}

	// ------------------------------------------------------------------------------------------------------

	public void agregarMateriaCorrelativa(Integer codigoMateria, Integer codigoMateriaCorrelativa) {
		if (!existeMateria(codigoMateria) || !existeMateria(codigoMateriaCorrelativa)) {
			throw new IllegalArgumentException("Uno o ambos codigos de materia no existen");
		} else {

			Materia materiaPrincipal = getMateriaPorCodigo(codigoMateria);
			Materia materiaCorrelativa = getMateriaPorCodigo(codigoMateriaCorrelativa);

			materiaPrincipal.agregarMateriaCorrelativa(materiaCorrelativa);
			materiaCorrelativa.agregarMateriaCorrelativa(materiaPrincipal);
		}
	}

	private Materia getMateriaPorCodigo(Integer codigoMateria) {

		return materias.get(codigoMateria);
	}

	private boolean existeMateria(Integer codigoMateria) {

		return materias.containsKey(codigoMateria);
	}

	public void eliminarCorrelativa(Integer codigoMateria, Integer codigoMateriaCorrelativa) {

		if (!existeMateria(codigoMateria) || !existeMateria(codigoMateriaCorrelativa)) {
			throw new IllegalArgumentException("Uno o ambos codigos de materia no existen");
		} else {

			Materia materiaPrincipal = getMateriaPorCodigo(codigoMateria);
			Materia materiaCorrelativa = getMateriaPorCodigo(codigoMateriaCorrelativa);

			materiaPrincipal.eliminarMateriaCorrelativa(materiaCorrelativa);
			materiaCorrelativa.eliminarMateriaCorrelativa(materiaPrincipal);
		}
	}
	// -----------------------------------------------------------------------------------------------

	public void inscribirAlumnoACurso(Alumno alumno, Curso curso, LocalDate fechaInscripcion) {
		// Verificar que el alumno y el curso estén dados de alta
		if (!alumnos.contains(alumno) || !cursos.contains(curso)) {
			throw new IllegalArgumentException("Error: El alumno y/o el curso no están dados de alta.");
		}
		// Verificar que el alumno tenga al menos cursadas todas las correlativas con
		// nota >= 4
		for (Materia correlativa : curso.getMateria().getMateriasCorrelativas()) {
			if (!alumno.tieneCorrelativaAprobada(correlativa)) {
				throw new IllegalArgumentException("Error: El alumno no cumple con todas las correlativas necesarias.");

			}
		}
		// Verificar que la inscripción esté dentro del período permitido
		if (!curso.getCicloLectivo().estaEnPeriodoDeInscripcion(fechaInscripcion)) {
			throw new IllegalArgumentException("Error: La inscripción no está dentro del período permitido.");

		}
		// Verificar que no se exceda el cupo máximo del curso
		if (curso.getCupoActual() >= curso.getCupoMaximo()) {
			throw new IllegalArgumentException("Error: El curso ha alcanzado su cupo máximo de alumnos.");

		}
		// Verificar si el alumno ya está inscrito en otro curso para el mismo día y
		// turno
		if (alumno.estaInscritoEnOtroCurso(curso)) {
			throw new IllegalArgumentException(
					"Error: El alumno ya está inscrito en otro curso para el mismo día y turno.");

		}

		// Realizar la inscripción
		alumno.inscribirseACurso(curso);
		curso.agregarAlumnoInscrito(alumno);
		curso.incrementarCupoActual();

	}

	public void agregarCurso(Curso curso) {
		cursos.add(curso);

	}

}
