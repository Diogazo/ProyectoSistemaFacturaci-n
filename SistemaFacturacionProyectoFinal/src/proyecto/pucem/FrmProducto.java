package proyecto.pucem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmProducto extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textcodProducto;
    private JTextField textnombreProducto;
    private JTextField textprecio;
    private JTextField textunidades;
    private JTable table;
    private static ArrayList<Producto> productos = new ArrayList<Producto>();
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmProducto frame = new FrmProducto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public FrmProducto() {
        setTitle("Producto");
        setBounds(100, 100, 450, 382);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Código de Producto");
        lblNewLabel.setBounds(10, 31, 156, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nombre del Producto");
        lblNewLabel_1.setBounds(10, 56, 139, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Precio");
        lblNewLabel_2.setBounds(10, 81, 100, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Unidades en el itinerario");
        lblNewLabel_3.setBounds(10, 106, 139, 14);
        getContentPane().add(lblNewLabel_3);

        textcodProducto = new JTextField();
        textcodProducto.setBounds(174, 28, 86, 20);
        getContentPane().add(textcodProducto);
        textcodProducto.setColumns(10);

        textnombreProducto = new JTextField();
        textnombreProducto.setBounds(174, 53, 86, 20);
        getContentPane().add(textnombreProducto);
        textnombreProducto.setColumns(10);

        textprecio = new JTextField();
        textprecio.setBounds(174, 78, 86, 20);
        getContentPane().add(textprecio);
        textprecio.setColumns(10);

        textunidades = new JTextField();
        textunidades.setBounds(174, 103, 86, 20);
        getContentPane().add(textunidades);
        textunidades.setColumns(10);

        JButton btnNewButton = new JButton("Nuevo");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        btnNewButton.setBounds(21, 156, 89, 23);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Guardar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (camposValidos()) {
                    crearProducto();
                }
            }
        });
        btnNewButton_1.setBounds(174, 156, 89, 23);
        getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Cancelar");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_2.setBounds(303, 156, 89, 23);
        getContentPane().add(btnNewButton_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 190, 362, 163);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Código del Producto", "Nombre", "Precio", "Unidades" }));
        scrollPane.setViewportView(table);
        model = (DefaultTableModel) table.getModel();
    }
    private void crearProducto() {
		Producto producto = new Producto(
				this.textcodProducto.getText(),
				this.textnombreProducto.getText(),
				Double.parseDouble(this.textprecio.getText()) ,
				Integer.parseInt(this.textunidades.getText()) 
				);
		productos.add(producto);
		agregarFila();
	}
    private void agregarFila() {
        Object[] fila = new Object[4];
        fila[0]= productos.get(productos.size()-1).getCodProducto();
        fila[1]= productos.get(productos.size()-1).getNombreProducto();
        fila[2]= productos.get(productos.size()-1).getPrecio();
        fila[3]= productos.get(productos.size()-1).getUnidades();
        this.model.addRow(fila);
    }
    private void limpiarCampos() {
        textcodProducto.setText("");
        textnombreProducto.setText("");
        textprecio.setText("");
        textunidades.setText("");
    }
    private boolean camposValidos() {
        return !textcodProducto.getText().isEmpty() && !textnombreProducto.getText().isEmpty()
                && !textprecio.getText().isEmpty() && !textunidades.getText().isEmpty();
    }
    public static ArrayList<Producto> getProductos(){
		return productos;
	}
}