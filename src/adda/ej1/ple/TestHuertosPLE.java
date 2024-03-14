package adda.ej1.ple;

import java.io.IOException;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestHuertosPLE {	
	public static void model(String fichero) throws IOException {
		DatosHuertos.iniDatos(fichero);
		AuxGrammar.generate(DatosHuertos.class, "modelos/ej1.lsi", "modelos/gurobi/ej1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("modelos/gurobi/ej1.lp");
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		Locale.setDefault(Locale.of("en", "US"));
		model("ficheros/ej1/Ejercicio1DatosEntrada1.txt");
		//model("ficheros/ej1/Ejercicio1DatosEntrada2.txt");
		//model("ficheros/ej1/Ejercicio1DatosEntrada3.txt");
	}
}
