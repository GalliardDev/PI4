package adda.ejercicios.ag;

import org.apache.commons.math3.genetics.StoppingCondition;

import adda.ejercicios.ple.common.DatosMulticonjunto;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class MainAG {
	public static void main(String[] args) {
		// 1. Ver y entender datos problema.
		
		// 2. Crear clase datos y cargar datos.
		DatosMulticonjunto.iniDatos("src/main/resources/ejemplo1_1.txt");
		
		// 3. Decidir tipo de cromosoma.
		
		// 4. Crear clase solucion.
		
		// 5. Implementar cromosoma.
		
		// 6. Constantes del AG.
		AlgoritmoAG.ELITISM_RATE = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 90;
		
		StoppingConditionFactory.NUM_GENERATIONS = 3000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 0;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		
		// 7. Crear y ejecutar AG.
		var ap = AlgoritmoAG.of(new Cromo());
		
		// 8. Mostrar solucion
		System.out.println(ap);
	}
}
