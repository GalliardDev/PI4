package adda.ej3.common;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeProductosTransportesAG implements ValuesInRangeData<Integer, SolucionProductosTransportes> {

	private static Integer prods;
	private static Integer dests;
	
	public InRangeProductosTransportesAG(String fichero) {
		DatosProductosTransportes.iniDatos(fichero);
		prods = DatosProductosTransportes.getN();
		dests = DatosProductosTransportes.getM();
	}
	
	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return prods * dests;
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = getCoste(ls);
		double errorDem = penalizaDemanda(ls);
		double errorProd = penalizaProductos(ls);
		double error = errorDem + errorProd;
		return -goal - 100000000 * error;
	}

	private double getCoste(List<Integer> ls) {
		double res = 0.;
		for(int i = 0; i < prods; i++) {
			for(int j = 0; j < dests; j++) {
				int pos = i * dests + j;
				res += ls.get(pos) * DatosProductosTransportes.getCoste(i, j);
			}
		}
		return res;
	}
	
	private double penalizaDemanda(List<Integer> ls) {
		double res = 0.;
		for(int j = 0; j < dests; j++) {
			double dem = 0.;
			for(int i = 0; i < prods; i++) {
				int pos = i * dests + j;
				dem += ls.get(pos);
			}
			res += AuxiliaryAg.distanceToEqZero(dem - 
					DatosProductosTransportes.getDestinos().get(j).demanda());
			res += 1000 * AuxiliaryAg.distanceToGeZero(dem -
					DatosProductosTransportes.getDestinos().get(j).demanda());
		}
		return res;
	}
	
	private double penalizaProductos(List<Integer> ls) {
		double res = 0.;
		for(int i = 0; i < prods; i++) {
			double cantDest = 0.;
			for(int j = 0; j < dests; j++) {
				int pos = i * dests + j;
				cantDest += ls.get(pos);
			}
			res += AuxiliaryAg.distanceToLeZero(cantDest - 
					DatosProductosTransportes.getProducto(i).cantidad());
		}
		return res;
	}

	@Override
	public SolucionProductosTransportes solucion(List<Integer> ls) {
		// TODO Auto-generated method stub
		return SolucionProductosTransportes.of_range(ls);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosProductosTransportes.getProducto(i / DatosProductosTransportes.getM()).cantidad() + 1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
