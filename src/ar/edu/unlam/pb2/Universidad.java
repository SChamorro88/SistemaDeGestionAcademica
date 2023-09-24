package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Universidad {

	private int id;
	private String nombre;
	private Map<Integer, Materia> materiasDisponibles;
	private Set<Alumno> alumnos;
	private Set<Docente> docentes;
	private Map<Integer, CicloLectivo> cicloElectivos;
	private List<Comision> comisiones;
	private List<Aula> aulas;
	private List<Curso> cursos;
	private Map<String, Map<String, Integer>> notasPorAlumnoYCurso;
	private Curso curso;

	public Universidad(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.materiasDisponibles = new HashMap<Integer, Materia>();
		this.alumnos = new HashSet<Alumno>();
		this.docentes = new HashSet<Docente>();
		this.cicloElectivos = new HashMap<Integer, CicloLectivo>();
		this.comisiones = new ArrayList<Comision>();
		this.aulas = new ArrayList<Aula>();
		this.cursos = new ArrayList<Curso>();
		this.notasPorAlumnoYCurso = new HashMap<>();
	}

	public Map<Integer, Materia> getMaterias() {
		return materiasDisponibles;
	}

	public void setMaterias(Map<Integer, Materia> materias) {
		this.materiasDisponibles = materias;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Set<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(Set<Docente> docentes) {
		this.docentes = docentes;
	}

	public Map<Integer, CicloLectivo> getCicloElectivos() {
		return cicloElectivos;
	}

	public void setCicloElectivos(Map<Integer, CicloLectivo> cicloElectivos) {
		this.cicloElectivos = cicloElectivos;
	}

	public List<Comision> getComisiones() {
		return comisiones;
	}

	public void setComisiones(List<Comision> comisiones) {
		this.comisiones = comisiones;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Map<String, Map<String, Integer>> getNotasPorAlumnoYCurso() {
		return notasPorAlumnoYCurso;
	}

	public void setNotasPorAlumnoYCurso(Map<String, Map<String, Integer>> notasPorAlumnoYCurso) {
		this.notasPorAlumnoYCurso = notasPorAlumnoYCurso;
	}

	public Map<Integer, Materia> getMateriasDisponibles() {
		return materiasDisponibles;
	}

	public void setMateriasDisponibles(Map<Integer, Materia> materiasDisponibles) {
		this.materiasDisponibles = materiasDisponibles;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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
		if (!materiasDisponibles.containsValue(materia)) {
			this.materiasDisponibles.put(materia.getId(), materia);
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
		return this.materiasDisponibles.size();
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

		return materiasDisponibles.get(codigoMateria);
	}

	private boolean existeMateria(Integer codigoMateria) {

		return materiasDisponibles.containsKey(codigoMateria);
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

	public void asignarCursoAMateria(Curso curso, Materia materia) {
		if (curso != null && materia != null) {
			materia.asociarCursoAMateria(curso);
		} else {
			throw new IllegalArgumentException("El curso o la materia son nulos.");
		}
	}

	public void asignarMateriaACurso(Curso curso, Materia materia) {
		if (curso != null && materia != null) {
			curso.asociarMateriaACurso(materia);
		} else {
			throw new IllegalArgumentException("El curso o la materia son nulos.");
		}
	}

	public Set<Materia> obtenerListadoMateriasAprobadasParaUnAlumno(int idAlumno) {
		// Buscar al alumno por su ID (debe implementarse una lógica para encontrar al
		// alumno)
		Alumno alumno = buscarAlumnoPorId(idAlumno);

		if (alumno == null) {
			return null; // Retornar null si no se encuentra el alumno
		}

		// Obtener la lista de materias aprobadas para el alumno
		Set<Materia> materiasAprobadas = alumno.getMateriasAprobadas();

		// Filtrar las materias aprobadas y devolver la lista resultante
		return materiasAprobadas;
	}

	private Alumno buscarAlumnoPorId(int idAlumno) {
		for (Alumno alumno : alumnos) {
			if (alumno.getId() == idAlumno) {
				return alumno;
			}
		}
		throw new IllegalArgumentException("no existe alumno");

	}

	public Materia[] obtenerMateriasQueFaltanCursarParaUnAlumno(Integer idAlumno) {
		Alumno alumno = buscarAlumnoPorId(idAlumno);

		if (alumno == null) {
			throw new IllegalArgumentException("No existe este alumno");
		}

		List<Materia> materiasCursadas = alumno.getMateriasCursadas();

		List<Materia> materiasFaltanCursar = new ArrayList<Materia>();

		for (Materia materia : materiasDisponibles.values()) {
			if (!materiasCursadas.contains(materia)) {
				materiasFaltanCursar.add(materia);
			}
		}
		return materiasFaltanCursar.toArray(new Materia[0]);
	}

	public String obtenerReporteDeNotasDeAlumnosDeCurso(int idCurso) {
		Curso curso = buscarCursoPorId(idCurso);
		if (curso == null) {
			throw new IllegalArgumentException("Curso no encontrado");
		}
		Materia materia = curso.getMateria();
		List<Alumno> alumnos = curso.getAlumnosInscritos();

		StringBuilder reporte = new StringBuilder();
		reporte.append("ID del Curso: ").append(idCurso).append("\n");
		reporte.append("Materia: ").append(materia.getNombre()).append("\n");
		reporte.append("Dni\tNombre\tApellido\tNota\n");

		for (Alumno alumno : alumnos) {
			Double nota = alumno.obtenerNotaEnMateria(materia);
			// Agregar la información del alumno y su nota al reporte
			reporte.append(alumno.getDni()).append("\t").append(alumno.getNombre()).append("\t")
					.append(alumno.getApellido()).append("\t").append(nota).append("\n");
			
		}
		return reporte.toString();
	}

	private Curso buscarCursoPorId(int idCurso) {
		for (Curso curso : cursos) {
			if (curso.getId() == idCurso) {
				return curso;
			}
		}
		throw new IllegalArgumentException("No se encuentra el id de curso");
	}

}
