package adda.ej2.ple;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosProductos {
	public static int N;
	public static int M;
	public static int PRES;
	private static List<Producto> productos;
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		Integer presupuesto = Integer.parseInt(lineas.get(0).replace("Presupuesto = ", ""));
		List<String> aux = lineas.subList(2, lineas.size());
		PRES = presupuesto;
		productos = aux.stream().map(Producto::of).toList();
		N = productos.size();
		M = productos.stream()
				.map(Producto::getCategoria)
				.collect(Collectors.toSet())
				.size();
	}
	
	public static Integer getN() {
		return N;
	}
	
	public static Integer getM() {
		return M;
	}
	
	public static Integer getPrecio(Integer i) {
		return productos.get(i).getPrecio();
	}
	
	public static Integer getMediaValoraciones(Integer i) {
		return productos.stream()
			.mapToInt(p -> p.getValoracion())
			.sum() / N;
	}
	
	public static Integer getPresupuesto() {
		return PRES;
	}
	
	public static Integer tieneCategoria(Integer i, Integer j) {
		return productos.get(i).tieneCategoria(j) ? 1 : 0;
	}
	
	public static void toConsole() {
		System.out.println("Presupuesto = " + PRES);
		System.out.println("Productos:\n" + productos.stream()
							.map(p -> "    " + p.toString())
							.collect(Collectors.joining("\n"))
		);
		System.out.println("Total productos: " + N);
		System.out.println("Total categorias: " + M);
	}
	
	public static void main(String[] args) {
		iniDatos("ficheros/ej2/Ejercicio2DatosEntrada1.txt");
		toConsole();
	}
}
