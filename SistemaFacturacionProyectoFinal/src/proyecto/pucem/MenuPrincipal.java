package proyecto.pucem;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private Map<String, JInternalFrame> formulariosAbiertos;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 756);

        formulariosAbiertos = new HashMap<>();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu("Archivo");
        mnArchivo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/proyecto/pucemimagenes/WhatsApp Image 2023-12-12 at 22.33.27.jpeg")));
        menuBar.add(mnArchivo);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnArchivo.add(mntmSalir);

        JMenu mnClientes = new JMenu("Clientes");
        mnClientes.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/proyecto/pucemimagenes/WhatsApp Image 2023-12-12 at 22.35.37.jpeg")));
        menuBar.add(mnClientes);

        JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
        mntmNuevoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario(FrmCliente.class);
            }
        });
        mnClientes.add(mntmNuevoCliente);

        JMenu mnProductos = new JMenu("Productos");
        mnProductos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/proyecto/pucemimagenes/WhatsApp Image 2023-12-12 at 22.36.34.jpeg")));
        menuBar.add(mnProductos);

        JMenuItem mntmNuevoProducto = new JMenuItem("Nuevo Producto");
        mntmNuevoProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario(FrmProducto.class);
            }
        });
        mnProductos.add(mntmNuevoProducto);

        JMenu mnFacturas = new JMenu("Facturas");
        mnFacturas.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/proyecto/pucemimagenes/WhatsApp Image 2023-12-12 at 22.39.01.jpeg")));
        menuBar.add(mnFacturas);

        JMenuItem mntmFacturar = new JMenuItem("Facturar");
        mntmFacturar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario(FrmFactura.class);
            }
        });
        mnFacturas.add(mntmFacturar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));

        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, "name_13528538996200");
        desktopPane.setLayout(null);
    }
    private void abrirFormulario(Class<?> claseFormulario) {
        String nombreFormulario = claseFormulario.getName();
        if (!formulariosAbiertos.containsKey(nombreFormulario) || formulariosAbiertos.get(nombreFormulario).isClosed()) {
            try {
                JInternalFrame formulario = (JInternalFrame) claseFormulario.getDeclaredConstructor().newInstance();
                formulariosAbiertos.put(nombreFormulario, formulario);
                desktopPane.add(formulario);
                formulario.setVisible(true);
                formulario.toFront();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            formulariosAbiertos.get(nombreFormulario).toFront();
        }
    }
}