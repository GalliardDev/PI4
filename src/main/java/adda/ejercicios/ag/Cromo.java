package adda.ejercicios.ag;

import java.util.List;
import java.util.stream.Collectors;

import adda.ejercicios.ple.common.DatosMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Cromo<I,L> implements ValuesInRangeData<Integer,List<Integer>> {

	@Override
	public java.lang.Integer size() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		// TODO Auto-generated method stub
		Integer numNum = contarNumeros(value);
		Integer diffSuma = diferenciaSuma(value);
		return .0- numNum - (1000 * diffSuma);
	}
	
	private Integer contarNumeros(List<Integer> value) {
		return value.stream().collect(Collectors.counting()).intValue();
	}
	
	private Integer diferenciaSuma(List<Integer> value) {
		Integer suma = 0;
		for(Integer i = 0; i < size(); i++) {
			suma += DatosMulticonjunto.getElemento(i) * value.get(i);
		}
		return Math.abs(suma-DatosMulticonjunto.getSuma());
	}

	@Override
	public List<Integer> solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Integer max(java.lang.Integer i) {
		// TODO Auto-generated method stub
		return DatosMulticonjunto.getMultiplicidad(i);
	}

	@Override
	public Integer min(java.lang.Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
