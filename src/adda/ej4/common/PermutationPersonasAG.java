package adda.ej4.common;

import java.util.List;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutationPersonasAG implements SeqNormalData<SolucionPersonas> {

	public PermutationPersonasAG(String fichero) {
		DatosPersonas.iniDatos(fichero);
	}
	
	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double error = 0., goal = 0.;
		for(int i = 0; i < ls.size() - 1; i+=2) {
			goal += DatosPersonas.getAfinidad(ls.get(i), ls.get(i+1));
			if(!DatosPersonas.idiomaComun(ls.get(i), ls.get(i+1))
				|| DatosPersonas.mismaNacionalidad(ls.get(i), ls.get(i+1))
				|| DatosPersonas.maximaDiferenciaEdad(ls.get(i), ls.get(i+1))) {
				error += 10000;
			}
		}
		return goal - 1000000 * error;
	}

	@Override
	public SolucionPersonas solucion(List<Integer> ls) {
		// TODO Auto-generated method stub
		return SolucionPersonas.of_list(ls);
	}

	@Override
	public Integer itemsNumber() {
		// TODO Auto-generated method stub
		return DatosPersonas.getN();
	}

}
