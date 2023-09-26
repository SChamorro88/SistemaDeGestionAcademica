package ar.edu.unlam.pb2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

	/*
	 * ACLARACION: SE USO IllegalArgumentException para generar mensajes
	 * personalizados para no crear "n" cantidad de excepciones especificas y
	 * sobre-saturar. Además tambien Stream y expresiones Lambda para reducir lineas
	 * de codigo.
	 */
	// ---------------------------------------------------------------------------
	@Before
	public void setUp() {
		Integer idUniversidad = 1;
		String nombreUniversidad = "Universidad De La Matanza";
		universidad = new Universidad(idUniversidad, nombreUniversidad);

		Integer idMateria = 1;
		String nombreMateria = "Matematica";
		materia = new Materia(idMateria, nombreMateria);

		Integer idAlumno = 1;
		Integer dni = 1231245;
		LocalDate fechaIngeso = LocalDate.of(2021, 03, 01);
		LocalDate fechaNacimiento = LocalDate.of(1999, 07, 21);
		alumno = new Alumno(idAlumno, dni, "Pedro", "Perez", fechaNacimiento, fechaIngeso);

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
		Integer idComsion = 1;
		comision = new Comision(idComsion, materia, cicloLectivo, turno);

		Integer id = 1;
		Integer cantidadAlumnos = 30;
		aula = new Aula(id, cantidadAlumnos);

		Integer idCurso = 1;
		Integer cupoMaximo = 30;
		curso = new Curso(idCurso, materia, cicloLectivo, turno, cupoMaximo);

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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaAgregarUnaMateriaDelMismoId() {
		universidad.agregarMateria(materia);
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNOSePuedaAgregarUnAlumnoConElMismoDni() {
		universidad.agregarAlumno(alumno);
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaAgregarUnDocenteConElMismoDni() {
		universidad.agregarDocente(docente);
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaAgregarUnCicloLectivoConElMismoId() {
		universidad.agregarCicloLectivo(cicloLectivo);
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaAgregarUnComisionParaLaMismaMateriaCicloYTurno() {
		universidad.agregarComision(comision);
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaAsignarMateriaCorrelativaSiUnoOAmbosCodigosDeMateriaNoExisten() {
		Materia materia1 = new Materia(0, "MAT1");
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaEliminarMateriaCorrelativaSiUnoOAmbosCodigosDeMateriaNoExisten() {
		Materia materia1 = new Materia(0, "MAT1");
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

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaInscribirAlumnoACursoSiNoEstaDentroDeLaFechaPermitida() {
		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);

		// Verificar que se pueda inscribir al alumno al curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 6, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);

		assertTrue(curso.estaInscrito(alumno));
	}

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaInscribirAlumnoACursoSiElCursoNoEstaDadoDeAlta() {
		universidad.agregarAlumno(alumno);

		// Verificar que se pueda inscribir al alumno al curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);

		assertTrue(curso.estaInscrito(alumno));
	}

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaInscribirAlumnoACursoPorqueExcedioElCupo() {
		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);

		curso.setCupoActual(30);
		curso.agregarAlumnoInscrito(alumno);

		// Verificar que se pueda inscribir al alumno al curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);

		assertTrue(curso.estaInscrito(alumno));
	}

	@Test(expected = IllegalArgumentException.class)
	public void QueNoSePuedaInscribirAlumnoACursoQueEsteIncriptoEnOtroCursoElMismoDiaYTurno() {
		Materia quimica = new Materia(3, "Química");
		Curso curso2 = new Curso(2, quimica, cicloLectivo, Turnos.NOCHE, 20);

		curso.setFechaCurso(LocalDate.of(2023, 8, 15));
		curso2.setFechaCurso(LocalDate.of(2023, 8, 15));
		curso2.setTurno(Turnos.MANIANA);

		universidad.agregarAlumno(alumno);
		universidad.agregarCurso(curso);

		universidad.agregarCurso(curso);
		universidad.agregarCurso(curso2);

		// Verificar que se pueda inscribir al alumno al curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 8, 15);
		universidad.inscribirAlumnoACurso(alumno, curso, fechaInscripcion);
		universidad.inscribirAlumnoACurso(alumno, curso2, fechaInscripcion);

		assertTrue(curso.estaInscrito(alumno));
	}
	// ----------------------------------------------------------------------------------------

	@Test
	public void queSePuedaAsignarProfesoresALCurso() {
		universidad.agregarCurso(curso);
		curso.asignarDocente(docente);

		assertTrue(curso.tieneProfesor(docente));

		assertTrue(docente.tieneCurso(curso));
	}

	@Test(expected = IllegalArgumentException.class)
	public void queNoSePuedaAsignarProfesoresALCursoPorQueNoExisteElDocente() {
		universidad.agregarCurso(curso);
		curso.asignarDocente(null);

		assertTrue(curso.tieneProfesor(docente));

		assertTrue(docente.tieneCurso(curso));
	}

	@Test(expected = AssertionError.class)
	public void queAlAsignarUnProfesorA21AlumnosLanceUnError() {

		for (int i = 0; i < 21; i++) { // Agregar 21 alumnos
			Alumno alumno = new Alumno(200 + i, "Nombre" + i, "Apellido" + i);
			curso.agregarAlumnoInscrito(alumno);
		}
		universidad.agregarCurso(curso);

		curso.asignarDocente(docente);

		Integer valorEsperado2 = 21;
		Integer valorObtenido2 = curso.getAlumnosInscritos().size();
		assertEquals(valorEsperado2, valorObtenido2);

		Integer valorEsperado = 2;
		Integer valorObtenido = curso.getDocentesAsignados().size();
		assertEquals(valorEsperado, valorObtenido);

	}

	// ----------------------------------------------------------------------------------------

	@Test
	public void queSePuedaRegistrarUnaNotaDeUnAlumno() {
		Alumno alumno = new Alumno(1, 12345233, "Juan", "Perez", LocalDate.of(1990, 1, 1), LocalDate.now());
		Materia materia = new Materia(1, "Matemáticas");

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
		Nota nota = new Nota("1erParc", 6.0, fisica);
		curso.registrarNota(curso.getId(), alumno.getId(), nota);

		assertTrue(alumno.getNotasRegistradas().contains(nota));

	}
	// -------------------------------------------------------------------------------------------

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
	// -------------------------------------------------------------------------------------------

	@Test
	public void QueSePuedaObtenerMateriasQueFaltanCursarParaUnAlumno() {
		Materia matematicas = new Materia(1, "Matemáticas");
		Materia fisica = new Materia(2, "Física");

		alumno.agregarMateriaCursada(matematicas);
		alumno.agregarMateriaCursada(fisica);

		// Agregar materias disponibles al sistema (usando el ID como clave)
		universidad.agregarMateria(matematicas);
		universidad.agregarMateria(fisica);
		universidad.agregarMateria(new Materia(3, "Química"));

		universidad.agregarAlumno(alumno);

		Materia[] materiasFaltanCursar = universidad.obtenerMateriasQueFaltanCursarParaUnAlumno(1);
		assertArrayEquals(new Materia[] { new Materia(3, "Química") }, materiasFaltanCursar);

	}
	// -------------------------------------------------------------------------------------------

	@Test
	public void ObtenerReporteDeNotasDeAumnosDeCurso() {
		// Crear algunas materias
		Materia matematicas = new Materia(1, "Matemáticas");
		Materia fisica = new Materia(2, "Física");
		Materia quimica = new Materia(3, "Química");

		universidad.agregarMateria(quimica);
		universidad.agregarMateria(matematicas);
		universidad.agregarMateria(fisica);

		universidad.agregarMateriaCorrelativa(1, 2);
		universidad.agregarMateriaCorrelativa(2, 3);

		// Crear algunos alumnos
		Alumno alumno1 = new Alumno(2, 6785543, "Juan", "Gomez", LocalDate.of(1999, 02, 11),
				LocalDate.of(2021, 03, 15));
		Alumno alumno2 = new Alumno(3, 5676546, "Maria", "Rojas", LocalDate.of(2000, 05, 22),
				LocalDate.of(2021, 03, 15));
		// Asignar notas a los alumnos en las materias
		alumno.asignarNotaEnMateria(matematicas, 8.5);
		alumno1.asignarNotaEnMateria(matematicas, 7.0);
		alumno2.asignarNotaEnMateria(matematicas, 5.0);

		// Crear cursos y agregar alumnos
		Curso curso1 = new Curso(1, matematicas, cicloLectivo, Turnos.MANIANA, 30);
		Curso curso2 = new Curso(2, quimica, cicloLectivo, Turnos.NOCHE, 20);

		// Inscribir alumno a un curso
		curso1.agregarAlumnoInscrito(alumno);
		curso1.agregarAlumnoInscrito(alumno1);
		curso1.agregarAlumnoInscrito(alumno2);

		// Agregar cursos al sistema
		universidad.agregarCurso(curso1);
		universidad.agregarCurso(curso2);

		String reporte = universidad.obtenerReporteDeNotasDeAlumnosDeCurso(1);
		String resultadoEsperado = "ID del Curso: 1\n" + "Materia: Matemáticas\n" + "Dni\tNombre\tApellido\tNota\n"
				+ "1231245\tPedro\tPerez\t8.5\n" + "6785543\tJuan\tGomez\t7.0\n" + "5676546\tMaria\tRojas\t5.0\n";

		assertEquals(resultadoEsperado, reporte);
	}

	@Test
	public void QueSePuedaCalcularElPromedio() {
		Materia quimica = new Materia(3, "Química");
		universidad.agregarAlumno(alumno);

		Curso curso1 = curso;
		Curso curso2 = new Curso(2, quimica, cicloLectivo, Turnos.NOCHE, 20);
		universidad.agregarCurso(curso1);
		universidad.agregarCurso(curso2);
		
		Nota nota = new Nota(curso1,"1erPar", 8.5, curso.getMateria());
		Nota nota2 = new Nota(curso2,"1erPar", 7.0, quimica);
			
		alumno.registrarNota(nota);
		alumno.registrarNota(nota2);
		
		double valorObtenido = universidad.calcularPromedio(alumno.getId());
		
		double valorEsperado = 7.75;
		assertEquals(valorEsperado,valorObtenido,0.01);
		
	}

}
