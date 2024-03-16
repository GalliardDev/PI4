package adda.ej2.ag;

import java.util.List;

import adda.ej2.ple.DatosProductosCategorias;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosProductosCategoriasAG<E,S> implements ValuesInRangeData<Integer,List<Integer>>{

    public DatosProductosCategoriasAG(String fichero) {
        DatosProductosCategorias.iniDatos(fichero);
    }

    @Override
    public Integer size() {
        return DatosProductosCategorias.getN();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        int n = DatosProductosCategorias.getN();
        int m = DatosProductosCategorias.getM();
        int pres = DatosProductosCategorias.getPresupuesto();
        boolean[] categoriaCubierta = new boolean[m];
        int[] precioAcumuladoPorCategoria = new int[m];
        int totalProductosSeleccionados = 0;
        int totalPrecio = 0;
        double totalValoracion = 0.0;

        // Inicializar arrays
        for (int i = 0; i < m; i++) {
            categoriaCubierta[i] = false;
            precioAcumuladoPorCategoria[i] = 0;
        }

        // Calcular valores acumulados y comprobar restricciones
        for (int i = 0; i < n; i++) {
            if (value.get(i) == 1) {
                totalProductosSeleccionados++;
                totalPrecio += DatosProductosCategorias.getPrecio(i);
                totalValoracion += DatosProductosCategorias.getValoracion(i);
                int categoria = DatosProductosCategorias.getCategoria(i);
                precioAcumuladoPorCategoria[categoria] += DatosProductosCategorias.getPrecio(i);
                categoriaCubierta[categoria] = true;
            }
        }

        // Comprobar si se cubren todas las categorías
        boolean todasLasCategoriasCubiertas = true;
        for (boolean cubierta : categoriaCubierta) {
            if (!cubierta) {
                todasLasCategoriasCubiertas = false;
                break;
            }
        }

        // Restricción: Todas las categorías deben estar cubiertas
        if (!todasLasCategoriasCubiertas) {
            return Double.NEGATIVE_INFINITY; // Penalización si no se cubren todas las categorías
        }

        // Restricción: La media de las valoraciones debe ser mayor o igual a 3
        double mediaValoraciones = totalValoracion / totalProductosSeleccionados;
        if (mediaValoraciones < 3) {
            return Double.NEGATIVE_INFINITY; // Penalización si la media de las valoraciones es menor a 3
        }

        // Restricción: El precio total de los productos de una misma categoría no puede superar el presupuesto
        for (int i = 0; i < m; i++) {
            if (precioAcumuladoPorCategoria[i] > pres) {
                return Double.NEGATIVE_INFINITY; // Penalización si el precio total de una categoría supera el presupuesto
            }
        }

        // Función objetivo: Minimizar el precio total de la cesta
        return .0-totalPrecio;
    }

    @Override
    public List<Integer> solucion(List<Integer> value) {
        return value;
    }

    @Override
    public Integer max(Integer i) {
        return DatosProductosCategorias.getM();
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }
}
