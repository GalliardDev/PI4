package adda.ej3.ag;

import java.util.List;
import adda.ej3.ple.DatosProductosTransportes;
import adda.ej3.ple.Producto;
import adda.ej3.ple.Destino;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosProductosTransportesAG<E,S> implements ValuesInRangeData<Integer,List<Integer>> {

    public DatosProductosTransportesAG(String fichero) {
        DatosProductosTransportes.iniDatos(fichero);
    }

    @Override
    public Integer size() {
        return DatosProductosTransportes.getN() * DatosProductosTransportes.getM();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        int n = DatosProductosTransportes.getN();
        int m = DatosProductosTransportes.getM();
        List<Producto> productos = DatosProductosTransportes.productos;
        List<Destino> destinos = DatosProductosTransportes.destinos;
        double totalCoste = 0.0;

        // Calcular el coste total de almacenamiento
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cantidadEnviada = value.get(i * m + j);
                totalCoste += cantidadEnviada * productos.get(i).getCoste(j);
            }
        }

        // Penalizar si no se cumple la demanda mínima en algún destino
        for (int j = 0; j < m; j++) {
            int totalEnviado = 0;
            for (int i = 0; i < n; i++) {
                totalEnviado += value.get(i * m + j);
            }
            if (totalEnviado < destinos.get(j).getDemanda()) {
                return Double.NEGATIVE_INFINITY;
            }
        }

        // Penalizar si se excede la cantidad disponible de algún producto
        for (int i = 0; i < n; i++) {
            int totalEnviado = 0;
            for (int j = 0; j < m; j++) {
                totalEnviado += value.get(i * m + j);
            }
            if (totalEnviado > productos.get(i).getCantidad()) {
                return Double.NEGATIVE_INFINITY;
            }
        }

        // Función objetivo: Minimizar el coste total de almacenamiento
        return -totalCoste;
    }

    @Override
    public List<Integer> solucion(List<Integer> value) {
        return value;
    }

    @Override
    public Integer max(Integer i) {
        return DatosProductosTransportes.getDemanda(i / DatosProductosTransportes.getM());
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }
}
