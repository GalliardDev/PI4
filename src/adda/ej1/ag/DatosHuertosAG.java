package adda.ej1.ag;

import java.util.List;

import adda.ej1.ple.DatosHuertos;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosHuertosAG<I,L> implements ValuesInRangeData<Integer,List<Integer>>{
		
	public DatosHuertosAG(String fichero) {
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
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosHuertos.getM();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
	    int[][] huertos = new int[DatosHuertos.getM()][DatosHuertos.getN()];
	    double goal = 0.0;
	    double error = 0.0;
	    int variedadesPlantadas = 0;

	    for (int i = 0; i < DatosHuertos.getM(); i++) {
	        for (int j = 0; j < DatosHuertos.getN(); j++) {
	            huertos[i][j] = 0;
	        }
	    }

	    for (int i = 0; i < ls.size(); i++) {
	        int huerto = ls.get(i);
	        int variedad = i;
	        boolean compatible = true;
	        
	        for (int k = 0; k < DatosHuertos.getN(); k++) {
	            if (huertos[huerto][k] == 1 && DatosHuertos.incompatible(variedad, k) == 1) {
	            	compatible = false;
	                break;
	            }
	        }

	        if (compatible) {
	            huertos[huerto][variedad] = 1; 
	            variedadesPlantadas++;
	        } else {
	            error++;
	        }
	    }

	    goal = variedadesPlantadas;
	    return goal - 10000 * error;
	}


	@Override
	public List<Integer> solucion(List<Integer> ls) {
		// TODO Auto-generated method stub
		return ls;
	}

}
