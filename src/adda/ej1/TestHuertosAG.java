package adda.ej1;

import java.util.Locale;

import adda.ej1.common.InRangeVerdurasAG;
import adda.util.Titles;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestHuertosAG {
	@SuppressWarnings({ "rawtypes" })
	private static void test(String fichero) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = 
				StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		InRangeVerdurasAG p = new InRangeVerdurasAG(fichero);
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
				
		System.out.println("================================");
		System.out.println(alg.bestSolution());
		System.out.println("================================");
	}
	
	public static void main(String[] args) {
		System.out.println(Titles.F1);
		test("ficheros/ej1/Ejercicio1DatosEntrada1.txt");
		System.out.println(Titles.F2);
		test("ficheros/ej1/Ejercicio1DatosEntrada2.txt");
		System.out.println(Titles.F3);
		test("ficheros/ej1/Ejercicio1DatosEntrada3.txt");
	}
}
