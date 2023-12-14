package proyecto.pucem;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmFactura extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblCedula;
	private JLabel lblNombres;
	private JLabel lblTelfono;
	private JLabel lblApellidos;
	private JLabel lblEmail;
	private JLabel lblDireccin;
	private JTable table;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private DefaultTableModel model;
	private JLabel lblIVA;
	private JLabel lblTotal;
	private JLabel lblSubtotal;

	public FrmFactura() {
		setTitle("Factura");
		setBounds(100, 100, 617, 576);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(27, 11, 78, 27);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblCliente);
		
		JButton btnSeleccionarCliente = new JButton("Seleccionar cliente");
		btnSeleccionarCliente.setBounds(296, 13, 145, 28);
		btnSeleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedirDatos();
			}
		});
		getContentPane().add(btnSeleccionarCliente);
		
		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(37, 53, 242, 27);
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblCedula);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(306, 53, 242, 27);
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblNombres);
		
		lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setBounds(306, 93, 242, 27);
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblTelfono);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(37, 93, 242, 27);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblApellidos);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(37, 127, 242, 27);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblEmail);
		
		lblDireccin = new JLabel("Dirección:");
		lblDireccin.setBounds(306, 127, 242, 27);
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblDireccin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(439, 435, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 191, 511, 141);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombre", "Precio", "Unidades", "Total"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		
		lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(175, 343, 169, 14);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblSubtotal);
		
		lblIVA = new JLabel("IVA: ");
		lblIVA.setBounds(175, 368, 169, 14);
		lblIVA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblIVA);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(175, 393, 169, 14);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblTotal);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.setBounds(397, 165, 151, 23);
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
		});
		getContentPane().add(btnEliminarProducto);
		
		JButton btnAgregarProductos = new JButton("Agregar Producto");
		btnAgregarProductos.setBounds(242, 165, 145, 23);
		btnAgregarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		getContentPane().add(btnAgregarProductos);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(72, 435, 89, 23);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDeNuevo();
			}
		});
		getContentPane().add(btnNuevo);

	}
	private void pedirDatos() {
		ListaClientes listaCliente = new ListaClientes(new JDialog(), true, lblCedula, lblNombres, lblApellidos, lblTelfono, lblEmail, lblDireccin);
		listaCliente.setVisible(true);
	}
	
	private void agregarProducto() {
		ListaProductos listaProductos = new ListaProductos(new JDialog(), true, this.model, this.productos, this.lblSubtotal, this.lblIVA, this.lblTotal);
		listaProductos.setVisible(true);
	}
	
	private void eliminarProducto() {
		EliminarProducto eliminarProducto = new EliminarProducto(new JDialog(), true, this.model, this.productos, this.lblSubtotal, this.lblIVA, this.lblTotal);
		eliminarProducto.setVisible(true);
	}
	private void abrirDeNuevo() {
		Container menuPrincipal = this.getParent();
		this.dispose();
		FrmFactura frmFactura = new FrmFactura();
		frmFactura.setVisible(true);
		menuPrincipal.add(frmFactura);
	}
}