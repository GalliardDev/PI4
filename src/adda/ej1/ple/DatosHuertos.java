package adda.ej1.ple;

import java.util.List;
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
		huertos = Huerto.fromList(h);
		verduras = Verdura.fromList(v);
		System.out.println(huertos);
		System.out.println(verduras);
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
}
