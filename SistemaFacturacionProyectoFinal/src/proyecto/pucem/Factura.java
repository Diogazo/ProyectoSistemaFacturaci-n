package proyecto.pucem;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Cliente cliente;
    private List<Producto> productos;
    private double subtotal;
    private double iva;
    private double total;

    public Factura() {
        productos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotales();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    private void calcularTotales() {
        subtotal = 0;
        for (Producto producto : productos) {
            subtotal += producto.getPrecio();
        }
        iva = subtotal * 0.12;
        total = subtotal + iva;
    }
}