package adda.ej4.ag;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosPersonas {
	private static int N;
	private static List<Persona> personas;
	
	public static void toConsole() {
		System.out.println("Personas:\n" + personas.stream()
							.map(v -> "    " + v.toString())
							.collect(Collectors.joining("\n"))
		);
		System.out.println("Total personas: " + N);
	}
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		personas = lineas.stream()
				.skip(1L)
				.map(Persona::of)
				.toList();
		N = personas.size();
		toConsole();
	}
	
	public static Integer getN() {
		return N;
	}
	
	public static Integer getAfinidad(Integer i, Integer j) {
		return personas.get(i).getAfinidad(j);
	}
	
	public static Integer getEdad(Integer i) {
		return personas.get(i).edad();
	}
	
	public static Set<String> getIdiomas(Integer i) {
		return personas.get(i).idiomas();
	}
	
	public static String getNacionalidad(Integer i) {
		return personas.get(i).nacionalidad();
	}
}
