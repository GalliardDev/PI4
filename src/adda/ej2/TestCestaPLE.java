package adda.ej2;

import java.io.IOException;
import java.util.Locale;

import adda.ej2.common.DatosCesta;
import adda.util.Titles;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestCestaPLE {
	public static void model(String fichero) throws IOException {
		DatosCesta.iniDatos(fichero);
		AuxGrammar.generate(DatosCesta.class, "modelos/ej2.lsi", "modelos/gurobi/ej2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("modelos/gurobi/ej2.lp");
		System.out.println(solution.toString());
	}
	
	public static void main(String[] args) throws IOException {	
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(Titles.F1);
		model("ficheros/ej2/Ejercicio2DatosEntrada1.txt");
		System.out.println(Titles.F2);
		model("ficheros/ej2/Ejercicio2DatosEntrada2.txt");
		System.out.println(Titles.F3);
		model("ficheros/ej2/Ejercicio2DatosEntrada3.txt");
	}
}
