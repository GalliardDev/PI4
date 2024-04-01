package adda.ej1.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import adda.ej1.common.DatosHuertos.Huerto;
import adda.ej1.common.DatosHuertos.Verdura;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeVerdurasAG implements ValuesInRangeData<Integer, SolucionHuertos>{

	public InRangeVerdurasAG(String fichero) {
		DatosHuertos.iniDatos(fichero);
	}
	
	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosHuertos.getN();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		// TODO Auto-generated method stub
		Double goal = (double) ls.stream().filter(n -> n > 0).count();
		Double error = 0.;
		Map<Huerto, List<Verdura>> huertoVerduras = new HashMap<>();
		
		for(int i = 0; i < size(); i++) {
			if(ls.get(i) > 0) {
				Huerto huerto = DatosHuertos.getHuerto(ls.get(i) - 1);
				
				if(huertoVerduras.containsKey(huerto)) {
					Verdura verdura = DatosHuertos.getVerdura(i);
					huertoVerduras.get(huerto).add(verdura);
				} else {
					Verdura verdura = DatosHuertos.getVerdura(i);
					huertoVerduras.put(huerto, new ArrayList<>(List.of(verdura)));
				}
			}
		}
		
		error += penalizacionIncompatibilidad(huertoVerduras);
		
		error += huertoVerduras.entrySet().stream()
				.mapToInt(
					e -> Math.abs(e.getKey().metrosDisponibles() - 
						e.getValue().stream()
						.mapToInt(Verdura::metrosRequeridos)
						.sum())
				).sum();
		
		return goal - 100000 * error;
	}
	
	

	private double penalizacionIncompatibilidad(Map<Huerto, List<Verdura>> huertoVerduras) {
		double error = 0.;
		for(Entry<Huerto, List<Verdura>> e: huertoVerduras.entrySet()) {
			for(int i = 0; i < e.getValue().size() - 1; i++) {
				List<Verdura> vs = e.getValue();
				boolean b = vs.get(i).incompatibles().contains(vs.get(i+1).nombre());
				error += b ? 1000 : 0;
			}
		}
		return error;
	}

	@Override
	public SolucionHuertos solucion(List<Integer> ls) {
		// TODO Auto-generated method stub
		return SolucionHuertos.of_range(ls);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosHuertos.getM() + 1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
