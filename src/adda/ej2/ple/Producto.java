package adda.ej2.ple;

public class Producto {
	private Integer id;
	private Integer precio;
	private Integer categoria;
	private Integer valoracion;
	
	public static Producto of(String s) {
		String[] partes = s.split(":");
		Integer id = Integer.parseInt(partes[0]);
		Integer precio = Integer.parseInt(partes[1]);
		Integer categoria = Integer.parseInt(partes[2]);
		Integer valoracion = Integer.parseInt(partes[3]);
		return new Producto(id, precio, categoria, valoracion);
	}
	
	private Producto(Integer id, Integer precio, Integer categoria, Integer valoracion) {
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
