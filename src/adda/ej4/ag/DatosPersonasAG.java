package adda.ej4.ag;

import java.util.List;
import java.util.Set;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosPersonasAG implements SeqNormalData<List<Integer>> {
	public DatosPersonasAG(String fichero) {
		DatosPersonas.iniDatos(fichero);
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
	    double totalAfinidad = 0.0;

	    for (int i = 0; i < value.size(); i += 2) {
	        Integer persona1Index = value.get(i);
	        Integer persona2Index = (i + 1 < value.size()) ? value.get(i + 1) : null;

	        if (persona2Index == null) {
	            break;
	        }

	        Integer afinidad = DatosPersonas.getAfinidad(persona1Index, persona2Index);
	        Integer edadPersona1 = DatosPersonas.getEdad(persona1Index);
	        Integer edadPersona2 = DatosPersonas.getEdad(persona2Index);
	        Set<String> idiomasPersona1 = DatosPersonas.getIdiomas(persona1Index);
	        Set<String> idiomasPersona2 = DatosPersonas.getIdiomas(persona2Index);
	        String nacionalidadPersona1 = DatosPersonas.getNacionalidad(persona1Index);
	        String nacionalidadPersona2 = DatosPersonas.getNacionalidad(persona2Index);

	        if (edadPersona1 - edadPersona2 <= 5 && edadPersona2 - edadPersona1 <= 5
	            && !nacionalidadPersona1.equals(nacionalidadPersona2)
	            && idiomasComunes(idiomasPersona1, idiomasPersona2)) {
	            totalAfinidad += afinidad;
	        }
	    }

	    return totalAfinidad;
	}

	private boolean idiomasComunes(Set<String> idiomas1, Set<String> idiomas2) {
	    for (String idioma : idiomas1) {
	        if (idiomas2.contains(idioma)) {
	            return true;
	        }
	    }
	    return false;
	}


	@Override
	public List<Integer> solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Integer itemsNumber() {
		// TODO Auto-generated method stub
		return DatosPersonas.getN();
	}

}
