package proyecto.pucem;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ListaProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelLocal;
	private ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
	private int indiceProducto = -1;
	
	public ListaProductos(Dialog owner, boolean modal, DefaultTableModel model, ArrayList<Producto> productos, JLabel lblSubtotal, JLabel lblIVA, JLabel lblTotal) {
		super(owner, modal);
		setBounds(100, 100, 572, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 348, 208);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombe", "Precio", "Unidad", "Total"
			}
		));
		scrollPane.setViewportView(table);
		{
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCantidad.setBounds(368, 17, 76, 14);
			contentPanel.add(lblCantidad);
		}
		modelLocal = (DefaultTableModel) table.getModel();
		
		if (!FrmProducto.getProductos().isEmpty()) {
			agregarProductos();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = 	new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enviarProducto(model, productos, lblSubtotal, lblIVA, lblTotal);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void agregarProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>(FrmProducto.getProductos());
		
		for (Producto producto : productos) {
			Object[] fila=new Object[5];
			fila[0]= producto.getCodProducto();
			fila[1]= producto.getNombreProducto();
			fila[2]= producto.getPrecio();
			fila[3]= producto.getUnidades();
			fila[4]= producto.getTotal();
			this.modelLocal.addRow(fila);
			
			JButton btnProducto = new JButton("Elegir");
			btnProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarProducto(productos.indexOf(producto));
				}
			});
			btnProducto.setBounds(425, 32+(productos.indexOf(producto)*18), 89, 18);
			this.contentPanel.add(btnProducto);
			
			JTextField txtProducto = new JTextField();
			txtProducto.setBounds(370, 30+(productos.indexOf(producto)*18), 50, 17);
			txtProducto.setText("");
			this.camposTexto.add(txtProducto);
			this.contentPanel.add(txtProducto);
		}
	}
	private void seleccionarProducto(int i) {
		this.indiceProducto = i;
	}
	private void enviarProducto(DefaultTableModel model, ArrayList<Producto> productos, JLabel lblSubtotal, JLabel lblIVA, JLabel lblTotal) {
	    if (this.indiceProducto == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!validarCantidad()) {
	        return;
	    }
	    Producto producto = new Producto(
	            FrmProducto.getProductos().get(indiceProducto).getCodProducto(),
	            FrmProducto.getProductos().get(indiceProducto).getNombreProducto(),
	            FrmProducto.getProductos().get(indiceProducto).getPrecio(),
	            Integer.parseInt(this.camposTexto.get(indiceProducto).getText())
	    );
	    productos.add(producto);

	    FrmProducto.getProductos().get(indiceProducto).setUnidades(FrmProducto.getProductos().get(indiceProducto).getUnidades() - producto.getUnidades());
	    Object[] fila = new Object[5];
	    fila[0] = productos.get(productos.size() - 1).getCodProducto();
	    fila[1] = productos.get(productos.size() - 1).getNombreProducto();
	    fila[2] = productos.get(productos.size() - 1).getPrecio();
	    fila[3] = productos.get(productos.size() - 1).getUnidades();
	    fila[4] = productos.get(productos.size() - 1).getTotal();
	    model.addRow(fila);

	    double total = 0;
	    for (Producto productoSum : productos) {
	        total += productoSum.getTotal();
	    }
	    double iva = total * 0.12;
	    String ivaS = String.format("%.2f", iva);

	    lblSubtotal.setText("Subtotal: " + total);
	    lblIVA.setText("IVA: " + ivaS);
	    lblTotal.setText("Total: " + (total + iva));

	    dispose();
	}
	private boolean validarCantidad() {
		try {
			int a = Integer.parseInt(this.camposTexto.get(indiceProducto).getText());
			if (a > FrmProducto.getProductos().get(indiceProducto).getUnidades()) {
				throw new IndexOutOfBoundsException("");
			}
			return true;
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Escoja un numero menor al total de cantidad del producto", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}catch (Exception e){
			this.camposTexto.get(indiceProducto).setText("1");
			validarCantidad();
		}
		return true;
	}	
}