package adda.ej1.ple;

import java.util.List;

public class Huerto {
	private Integer numero;
	private Integer metrosDisponibles;
	
	public static Huerto of(String s) {
		String[] partes = s.split(":");
        Integer numero = Integer.parseInt(partes[0].trim().replace("H", ""));
        String[] props = partes[1].replace(",", "").split(";");
        
		int metrosDisponibles = Integer.parseInt(props[0].split("=")[1].trim());
        return new Huerto(numero, metrosDisponibles);
	}
	
	private Huerto(Integer numero, Integer metros) {
		this.numero = numero;
		this.metrosDisponibles = metros;
	}
	
	public Integer getNumero() {
		return this.numero;
	}
	
	public Integer getMetrosDisponibles() {
		return this.metrosDisponibles;
	}
	
	public static List<Huerto> fromList(List<String> ls) {
		return ls.stream().map(Huerto::of).toList();
	}
	
	@Override
	public String toString() {
		return "H"+numero+"("+metrosDisponibles+")";
	}
}
