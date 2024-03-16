package adda.ej4.ag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record Persona(String nacionalidad, 
		Integer edad, Set<String> idiomas, Map<Integer,Integer> afinidades) {
	public static Persona of(String s) {
        String[] parts = s.trim().split("->");
        String[] datos = parts[1].split(";");

        Integer edad = Integer.parseInt(datos[0].split("=")[1]);

        String[] idiomasArray = datos[1].substring(9, datos[1].length() - 1).split(",");
        Set<String> idiomas = new HashSet<>(Arrays.asList(idiomasArray));

        String nacionalidad = datos[2].split("=")[1];

        String[] afinidadesArray = datos[3].substring(12, datos[3].length() - 1)
        		.replace("(", "").replace(")", "").split(",");
        Map<Integer, Integer> afinidades = new HashMap<>();
        for (String par : afinidadesArray) {
            String[] valores = par.split(":");
            afinidades.put(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]));
        }

        return new Persona(nacionalidad, edad, idiomas, afinidades);
    }
	
	public Integer getAfinidad(Integer j) {
		return afinidades.get(j);
	}
}
