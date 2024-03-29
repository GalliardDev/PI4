package adda.ej3.ag;

import java.util.Locale;

import adda.util.Titles;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestProductosTransportesAG {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void test(String fichero) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		DatosProductosTransportesAG p = new DatosProductosTransportesAG(fichero);
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("================================");
		System.out.println(alg.bestSolution());
		System.out.println("================================");
	}
	
	public static void main(String[] args) {
		System.out.println(Titles.F1);
		test("ficheros/ej3/Ejercicio3DatosEntrada1.txt");
		System.out.println(Titles.F2);
		test("ficheros/ej3/Ejercicio3DatosEntrada2.txt");
		System.out.println(Titles.F3);
		test("ficheros/ej3/Ejercicio3DatosEntrada3.txt");
	}
}
