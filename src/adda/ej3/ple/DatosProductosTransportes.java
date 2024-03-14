package adda.ej3.ple;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosProductosTransportes {
	public static int N; // productos
	public static int M; // destinos
	private static List<Producto> productos;
	private static List<Destino> destinos;
	
	public static void toConsole() {
		System.out.println("Productos:\n" + productos.stream()
		.map(v -> "    " + v.toString())
		.collect(Collectors.joining("\n"))
		);
		System.out.println("Destinos:\n" + destinos.stream()
		.map(h -> "    " + h.toString())
		.collect(Collectors.joining("\n"))
		);	
		System.out.println("Total productos: " + N);
		System.out.println("Total destinos: " + M);
	}
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		Integer aux = lineas.indexOf("// PRODUCTOS Id_producto -> unidades=integer;coste_almacenamiento=(destino:coste)");
		destinos = Destino.fromList(lineas.subList(1, aux));
		productos = Producto.fromList(lineas.subList(aux + 1, lineas.size()));
		toConsole();
	}
	
	public static Integer getN() {
		return N;
	}
	
	public static Integer getM() {
		return M;
	}
	
	public static Integer getCantidad(Integer i) {
		return productos.get(i).getCantidad();
	}
	
	public static Integer getDemanda(Integer j) {
		return destinos.get(j).getDemanda();
	}
	
	public static Integer getCoste(Integer i, Integer j) {
		return productos.get(i).getCoste(j);
	}
}
