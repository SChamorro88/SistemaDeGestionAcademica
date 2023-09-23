package ar.edu.unlam.pb2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TestSistemaDeGestionAcademica {
	private Universidad universidad;
	private Materia materia;
	private Alumno alumno;
	private Docente docente;
	private CicloLectivo cicloElectivo;
	private Comision comision;
	private Aula aula;

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
		LocalDate fechaInicioCicloElectivo = LocalDate.of(2023, 03, 15);
		LocalDate fechaFinalizacionCicloElectivo = LocalDate.of(2023, 07, 30);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 02, 10);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 03, 03);
		cicloElectivo = new CicloLectivo(idCiclo, fechaInicioCicloElectivo, fechaFinalizacionCicloElectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		Turnos turno = Turnos.MANIANA;
		comision = new Comision(1, materia, cicloElectivo, turno);

		Integer id = 1;
		Integer cantidadAlumnos = 30;
		aula = new Aula(id, cantidadAlumnos);

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
		assertNotNull(cicloElectivo);
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
		universidad.agregarCicloLectivo(cicloElectivo);

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
	//---------------------------------------------------------------------------------------
	@Test
	public void QueSePuedaInscribirAlumnoACurso() {
		sdf
	}
	
}
