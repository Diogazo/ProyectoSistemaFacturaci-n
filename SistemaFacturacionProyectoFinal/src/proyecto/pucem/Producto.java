package proyecto.pucem;

public class Producto {
    private String codProducto;
	private String nombreProducto;
    private double precio;
    private int unidades;
    private double total;
    
    public Producto(String codProducto, String nombreProducto, double precio, int unidades) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.unidades = unidades;
		this.setTotal(this.precio*this.unidades);
		}
    public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}