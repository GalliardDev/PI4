package adda.ej3.ple;

import java.util.List;

public class Destino {
	private Integer numero;
	private Integer demanda;
	
	public static Destino of(String s) {
		String[] partes = s.split(":");
        Integer numero = Integer.parseInt(partes[0].trim().replace("D", ""));
        String[] props = partes[1].replace(",", "").split(";");
        
		int demanda = Integer.parseInt(props[0].split("=")[1].trim());
        return new Destino(numero, demanda);
	}
	
	private Destino(Integer numero, Integer demanda) {
		this.numero = numero;
		this.demanda = demanda;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public Integer getDemanda() {
		return demanda;
	}
	
	public static List<Destino> fromList(List<String> ls) {
		return ls.stream().map(Destino::of).toList();
	}
	
	@Override
	public String toString() {
		return "D"+numero+"("+demanda+")";
	}
}
