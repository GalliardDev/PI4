package adda.ej3.ple;

import java.io.IOException;
import java.util.Locale;

import adda.util.Titles;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestProductosTransportesPLE {
	public static void model(String fichero) throws IOException {
		DatosProductosTransportes.iniDatos(fichero);
		AuxGrammar.generate(DatosProductosTransportes.class, "modelos/ej3.lsi", "modelos/gurobi/ej3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("modelos/gurobi/ej3.lp");
		System.out.println(solution.toString());
	}
	
	public static void main(String[] args) throws IOException {	
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(Titles.F1);
		model("ficheros/ej3/Ejercicio3DatosEntrada1.txt");
		System.out.println(Titles.F2);
		model("ficheros/ej3/Ejercicio3DatosEntrada2.txt");
		System.out.println(Titles.F3);
		model("ficheros/ej3/Ejercicio3DatosEntrada3.txt");
	}
}
