package adda.ej3.ple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto {
	private Integer numero;
	private Integer unidades;
	private Map<Integer,Integer> destinosCostes;
	
	public static Producto of(String s) {
		String[] partes = s.split("->");
		Integer numero = Integer.parseInt(partes[0].trim().substring(1));
		String[] partes2 = partes[1].trim().split(";");
		Integer unidades = Integer.parseInt(partes2[0].trim().split("=")[1]);
		String[] partes3 = partes2[1].trim().split("=");
		String[] partes4 = partes3[1].trim().split(",");
		Map<Integer,Integer> destinosCostes = new HashMap<>();
		for(String prod : partes4) {
			String[] partes5 = prod.trim().split(":");
			Integer destino = Integer.parseInt(partes5[0].trim().substring(1));
			Integer coste = Integer.parseInt(partes5[1].trim().substring(0,
					partes5[1].trim().length()-1));
			destinosCostes.put(destino, coste);
		}
		return new Producto(numero, unidades, destinosCostes);
	}
	
	private Producto(Integer numero, Integer unidades, Map<Integer,Integer> destinosCostes) {
		this.numero = numero;
		this.unidades = unidades;
		this.destinosCostes = destinosCostes;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public Integer getCantidad() {
		return unidades;
	}
	
	public Integer getCoste(Integer j) {
		return destinosCostes.get(j);
	}
	
	public static List<Producto> fromList(List<String> ls) {
		return ls.stream().map(Producto::of).toList();
	}
	
	@Override
	public String toString() {
		return "P"+numero+"(uds="+unidades+",destCost="+destinosCostes+")";
	}
}
