package adda.ej2.ple;

public class ProductoCategoria {
	private Integer id;
	private Integer precio;
	private Integer categoria;
	private Integer valoracion;
	
	public static ProductoCategoria of(String s) {
		String[] partes = s.split(":");
		Integer id = Integer.parseInt(partes[0]);
		Integer precio = Integer.parseInt(partes[1]);
		Integer categoria = Integer.parseInt(partes[2]);
		Integer valoracion = Integer.parseInt(partes[3]);
		return new ProductoCategoria(id, precio, categoria, valoracion);
	}
	
	private ProductoCategoria(Integer id, Integer precio, Integer categoria, Integer valoracion) {
		this.id = id; 
		this.precio = precio;
		this.categoria = categoria;
		this.valoracion = valoracion;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getPrecio() {
		return precio;
	}
	
	public Integer getCategoria() {
		return categoria;
	}
	
	public Integer getValoracion() {
		return valoracion;
	}
	
	public boolean tieneCategoria(Integer i) {
		return categoria.equals(i);
	}
	
	@Override
	public String toString() {
		return "P"+id+"[prec="+precio+",cate="+categoria+",valo="+valoracion+"]";
	}
}
