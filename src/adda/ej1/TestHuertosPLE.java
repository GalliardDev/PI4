package adda.ej1;

import java.io.IOException;
import java.util.Locale;

import adda.ej1.common.DatosHuertos;
import adda.util.Titles;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestHuertosPLE {	
	public static void model(String fichero) throws IOException {
		DatosHuertos.iniDatos(fichero);
		AuxGrammar.generate(DatosHuertos.class, "modelos/ej1.lsi", "modelos/gurobi/ej1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("modelos/gurobi/ej1.lp");
		System.out.println(solution.toString());
	}
	
	public static void main(String[] args) throws IOException {	
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(Titles.F1);
		model("ficheros/ej1/Ejercicio1DatosEntrada1.txt");
		System.out.println(Titles.F2);
		model("ficheros/ej1/Ejercicio1DatosEntrada2.txt");
		System.out.println(Titles.F3);
		model("ficheros/ej1/Ejercicio1DatosEntrada3.txt");
	}
}
