package adda.ej1.ple;

import java.util.ArrayList;
import java.util.List;

public class Verdura {
	private Integer variedad;
	private Integer metros;
	private List<Integer> incompatibilidades;
	
	public static Verdura of(String s) {
        String[] parts = s.split("->|metrosrequeridos=|; incomp=|,");
        int metros = Integer.parseInt(parts[2].trim());
        List<Integer> incompatibilidades = new ArrayList<>();
        for (int i = 2; i < parts.length; i++) {
            if (!parts[i].isEmpty() && parts[i].startsWith("V")) {
                incompatibilidades.add(Integer.parseInt(parts[i].substring(1).replace(";", "")));
            }
        }
        int variedad = Integer.parseInt(s.substring(1, 2));
        return new Verdura(variedad, metros, incompatibilidades);
    }
	
	private Verdura(Integer variedad, Integer metros, List<Integer> incompatibilidades) {
		this.variedad = variedad;
		this.metros = metros;
		this.incompatibilidades = incompatibilidades;
	}
	
	public Integer getVariedad() {
		return this.variedad;
	}
	
	public Integer getMetrosRequeridos() {
		return this.metros;
	}
	
	public List<Integer> getIncompatibilidades() {
		return this.incompatibilidades;
	}
	
	public boolean incompatible(Integer k) {
		return incompatibilidades.contains(k);
	}
	
	public static List<Verdura> fromList(List<String> ls) {
		return ls.stream().map(Verdura::of).toList();
	}
	
	@Override
	public String toString() {
		return "V"+variedad+"("+metros+";"+incompatibilidades+")";
	}
}
