package adda.ej2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import adda.ej2.common.DatosCesta.Producto;
import us.lsi.ag.BinaryData;

public class BinaryCestaAG implements BinaryData<SolucionCesta>{
	public BinaryCestaAG(String fichero) {
		DatosCesta.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosCesta.getN();
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0., error = 0.;
		List<Producto> productos = new ArrayList<>();
		
		if(ls.stream().filter(p -> p == 1).count() < DatosCesta.getM()) {
			error += 1000000000;
		} else {
			// goal de precio
			goal += getGoal(ls, productos);
			
			// no cubrir todas las categorias
			error += cumpleOrErrorSquared(productos.stream()
					.map(Producto::categoria).distinct().count(),
					p -> p.equals((long) DatosCesta.getM()));
			
			// no cumplir media > 3
			error += cumpleOrErrorSquared(productos.stream()
					.mapToDouble(Producto::valoracion).sum()
					- 3 * ls.stream().filter(p -> p > 0).count(), p -> p > 0);
			
			// superar limite de presupuesto por categoria
			error += cumpleOrError(
					productos.stream().collect(
							Collectors.groupingBy(Producto::categoria, Collectors.summingInt(Producto::precio))),
					p -> p <= DatosCesta.getPresupuesto());
		}
		return -goal - 1000000000 * error;
	}
	
	private double getGoal(List<Integer> ls, List<Producto> productos) {
		double res = 0.;
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) > 0) {
				Producto p = DatosCesta.getProducto(i);
				productos.add(p);
				res += p.precio();
			}
		}
		return res;
	}
	
	private <T extends Number> double cumpleOrErrorSquared(T res, Predicate<T> p) {
		return p.test(res) ? 0. : Math.pow(res.doubleValue() * 10, 2);
	}
	
	private double cumpleOrError(Map<Integer, Integer> res, Predicate<Double> p) {
		double ac = 0.;
		for(Entry<Integer, Integer> e : res.entrySet()) {
			double sum = e.getValue();
			ac += p.test(sum) ? 0 : sum;
		}
		return Math.pow(ac, 2);
	}

	@Override
	public SolucionCesta solucion(List<Integer> ls) {
		// TODO Auto-generated method stub
		return SolucionCesta.of_list(ls);
	}
	
}
