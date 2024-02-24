package adda.ejercicios.ple;

import java.io.IOException;

import adda.ejercicios.ple.common.DatosMulticonjunto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class MainPLE {

	public static void main(String[] args) throws IOException {
		// 1. Ver y entender datos de entrada (resolver).
		
		// 2. Crear clase para cargar datos de entrada y cargar datos de entrada.
		DatosMulticonjunto.iniDatos("src/main/resources/ejemplo1_1.txt");
		
		// 3. Escribir LSI.
		
		// 4. Ejecutar Gurobi
		AuxGrammar.generate(DatosMulticonjunto.class,
				"src/main/resources/ejemplo1_1.lsi",
				"src/main/resources/ejemplo1_1.lp");
		GurobiSolution solution = 
				GurobiLp.gurobi("src/main/resources/ejemplo1_1.lp");
	
		// 5. Mostrar datos
		System.out.println(solution);
		
	}

}
