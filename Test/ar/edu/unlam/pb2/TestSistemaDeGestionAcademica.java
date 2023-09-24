package ar.edu.unlam.pb2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class TestSistemaDeGestionAcademica {
	private Universidad universidad;
	private Materia materia;
	private Alumno alumno;
	private Docente docente;
	private CicloLectivo cicloLectivo;
	private Comision comision;
	private Aula aula;
	private Curso curso;

	// ---------------------------------------------------------------------------
	@Before
	public void setUp() {
		universidad = new Universidad(1, "Universidad De La Matanza");

		materia = new Materia(1, "Matematica");

		Integer dni = 1231245;
		LocalDate fechaIngeso = LocalDate.of(2021, 03, 01);
		LocalDate fechaNacimiento = LocalDate.of(1999, 07, 21);
		alumno = new Alumno(1, dni, "Pedro", "Perez", fechaNacimiento, fechaIngeso);

		Integer dniDocente = 124542134;
		LocalDate fechaIngesoDocente = LocalDate.of(2021, 03, 01);
		LocalDate fechaNacimientoDocente = LocalDate.of(1999, 07, 21);
		docente = new Docente(1, dniDocente, "Pedro", "Perez", fechaNacimientoDocente, fechaIngesoDocente);

		Integer idCiclo = 1;
		LocalDate fechaInicioCicloElectivo = LocalDate.of(2024, 7, 31);
		LocalDate fechaFinalizacionCicloElectivo = LocalDate.of(2024, 7, 31);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 8, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 8, 31);
		cicloLectivo = new CicloLectivo(idCiclo, fechaInicioCicloElectivo, fechaFinalizacionCicloElectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		Turnos turno = Turnos.MANIANA;
		comision = new Comision(1, materia, cicloLectivo, turno);

		Integer id = 1;
		Integer cantidadAlumnos = 30;
		aula = new Aula(id, cantidadAlumnos);

		curso = new Curso(1, materia, cicloLectivo, turno, 30);
	}

	// ---------------------------------------------------------------------------
	@Test
	public void QueSePuedaCrearUniversidad() {
		assertNotNull(universidad);
	}

	@Test
	public void QueSePuedaCrearMateria() {
		assertNotNull(materia);
	}

	@Test
	public void QueSePuedaCrearAlumno() {
		assertNotNull(alumno);
	}

	@Test
	public void QueSePuedaCrearDocente() {
		assertNotNull(docente);
	}

	@Test
	public void QueSePuedaCrearCicloLectivo() {
		assertNotNull(cicloLectivo);
	}

	@Test
	public void QueSePuedaCrearComision() {
		assertNotNull(comision);
	}

	@Test
	public void QueSePuedaCrearAula() {
		assertNotNull(aula);
	}

	// ---------------------------------------------------------------------------

	@Test
	public void QueSePuedaAgregarUnaMateria() {
		universidad.agregarMateria(materia);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeMaterias();

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void QueSePuedaAgregarUnAlumno() {
		universidad.agregarAlumno(alumno);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeAlumnos();

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void QueSePuedaAgregarUnDocente() {
		universidad.agregarDocente(docente);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeDocentes();

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void QueSePuedaAgregarUnCicloLectivo() {
		universidad.agregarCicloLectivo(cicloLectivo);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeCiclosLectivos();

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void QueSePuedaAgregarUnComision() {
		universidad.agregarComision(comision);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeComisiones();

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void QueSePuedaAgregarUnAula() {
		universidad.agregarAula(aula);

		Integer valorEsperado = 1;
		Integer valorObtenido = universidad.obtenerCantidadDeAulas();

		assertEquals(valorEsperado, valorObtenido);

	}
	// --------------------------------------------------------------------------------------

	@Test
	public void QueSePuedaAsignarMateriaCorrelativa() {
		Materia materia1 = new Materia(1, "MAT1");
		Materia materia2 = new Materia(2, "MAT2");
		Materia materia3 = new Materia(3, "MAT3");

		universidad.agregarMateria(materia1);
		universidad.agregarMateria(materia2);
		universidad.agregarMateria(materia3);

		universidad.agregarMateriaCorrelativa(1, 2);
		universidad.agregarMateriaCorrelativa(2, 3);

		// Verificar que las materias correlativas se han agregado correctamente
		assertTrue(materia1.getMateriasCorrelativas().contains(materia2));
		assertTrue(materia2.getMateriasCorrelativas().contains(materia1));
		assertTrue(materia2.getMateriasCorrelativas().contains(materia3));
		assertTrue(materia3.getMateriasCorrelativas().contains(materia2));

	}
	// -----------------------------------------------------------------------------------------

	@Test
	public void QueSePuedaEliminarMateriaCorrelativa() {
		Materia materia1 = new Materia(1, "MAT1");
		Materia materia2 = new Materia(2, "MAT2");
		Materia materia3 = new Materia(3, "MAT3");

		universidad.agregarMateria(materia1);
		universidad.agregarMateria(materia2);
		universidad.agregarMateria(materia3);

		universidad.agregarMateriaCorrelativa(1, 2);
		universidad.agregarMateriaCorrelativa(2, 3);

		// Verificar que las materias correlativas se han agregado correctamente
		assertTrue(materia1.getMateriasCorrelativas().contains(materia2));
		assertTrue(materia2.getMateriasCorrelativas().contains(materia1));
		assertTrue(materia2.getMateriasCorrelativas().contains(materia3));
		assertTrue(materia3.getMateriasCorrelativas().contains(materia2));

		// Eliminar la correlativa
		universidad.eliminarCorrelativa(1, 2);

		// Verificar que la correlativa se haya eliminado correctamente
		assertFalse(materia1.getMateriasCorrelativas().contains(materia2));
		assertFalse(materia2.getMateriasCorrelativas().contains(materia1));

	}

	// ---------------------------------------------------------------------------------------
	@Test
	public void QueSePuedaInscribirAlumnoACurso() {
		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);

		// Verificar que se pueda inscribir al alumno al curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);

		assertTrue(curso.estaInscrito(alumno));
	}

	// ----------------------------------------------------------------------------------------

	@Test
	public void queSePuedaAsignarProfesoresALCurso() {
		curso.asignarDocente(docente);

		assertTrue(curso.tieneProfesor(docente));

		assertTrue(docente.tieneCurso(curso));
	}

	// ----------------------------------------------------------------------------------------

	@Test
	public void queSePuedaRegistrarUnaNotaDeUnAlumno() {
		Alumno alumno = new Alumno(1, 12345233, "Juan", "Perez", LocalDate.of(1990, 1, 1), LocalDate.now());
		Materia materia = new Materia(1, "Matemáticas");
		CicloLectivo cicloLectivo = new CicloLectivo(1, LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now());
		Comision comision = new Comision(1, materia, cicloLectivo, Turnos.MANIANA);
		Aula aula = new Aula(1, 30);

		Materia fisica = new Materia(2, "Física");
		universidad.agregarMateria(materia);
		universidad.agregarMateria(fisica);
		universidad.agregarMateriaCorrelativa(1, 2);

		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);

		alumno.inscribirseACurso(curso);
		alumno.aprobarMateria(fisica);

		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);
		// Crear una nota válida
		Nota nota = new Nota("1erParc", 6, fisica);
		curso.registrarNota(curso.getId(), alumno.getId(), nota);

		assertTrue(alumno.getNotasRegistradas().contains(nota));

	}

	@Test
	public void queSePuedoObtenerListadoMateriasAprobadasParaUnAlumno() {
		Materia matematica = new Materia(1, "Matematicas");
		Materia fisica = new Materia(2, "Fisica");

		universidad.agregarMateria(matematica);
		universidad.agregarMateria(fisica);
		universidad.agregarMateriaCorrelativa(1, 2);

		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);
		
		alumno.aprobarMateria(matematica);
		alumno.aprobarMateria(fisica);

		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);

		// Llamar al método para obtener las materias aprobadas para el alumno con ID 1
		Set<Materia> materiasAprobadas = universidad.obtenerListadoMateriasAprobadasParaUnAlumno(1);
		// Lista de nombres de materias esperadas
		List<String> nombresMateriasEsperadas = Arrays.asList("Matematicas", "Fisica");

		// Convertir las listas a arrays y verificar que sean iguales

		String[] aprobadasArray = materiasAprobadas.stream().map(Materia::getNombre).toArray(String[]::new);
		String[] esperadasArray = nombresMateriasEsperadas.toArray(new String[0]);

		assertEquals(2, materiasAprobadas.size());

		assertArrayEquals(esperadasArray, aprobadasArray);

	}

}
