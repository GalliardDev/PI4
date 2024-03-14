package adda.ej1.ple;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosHuertos {
	public static int N;
	public static int M;
	private static List<Verdura> verduras;
	private static List<Huerto> huertos;
		
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		Integer aux = lineas.indexOf("// VARIEDADES");
		List<String> h = lineas.subList(1, aux);
		List<String> v = lineas.subList(aux + 1, lineas.size());
		N = v.size();
		M = h.size();
		huertos = Huerto.fromList(h);
		verduras = Verdura.fromList(v);
	}
	
	public static Verdura getVerdura(Integer i) {
		return verduras.get(i);
	}
	
	public static Integer getN() {
		return N;
	}
	
	public static Integer getM() {
		return M;
	}
	
	public static Integer getMetrosRequeridos(Integer i) {
		return verduras.get(i).getMetrosRequeridos();
	}
	
	public static Integer getMetrosDisponibles(Integer j) {
		return huertos.get(j).getMetrosDisponibles();
	}
	
	public static Integer incompatible(Integer i, Integer k) {
		return verduras.get(i).incompatible(k) ? 1 : 0;
	}
	
	public static void toConsole() {
		System.out.println("Verduras:\n" + verduras.stream()
							.map(v -> "    " + v.toString())
							.collect(Collectors.joining("\n"))
		);
		System.out.println("Huertos:\n" + huertos.stream()
		.map(h -> "    " + h.toString())
		.collect(Collectors.joining("\n"))
	    );	
		System.out.println("Total verduras: " + N);
		System.out.println("Total huertos: " + M);
	}
	
	public static void main(String[] args) {
		iniDatos("ficheros/ej1/Ejercicio1DatosEntrada1.txt");
		toConsole();
	}
}
