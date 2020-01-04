import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.awt.Dimension;
import java.awt.Point;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClienteFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new ChatClienteFrame().setVisible(true);
    }

    public ChatClienteFrame() {
        inicializar();
    }

    public static Point derecha(JComponent c, int x) {
        return new Point(c.getX() + c.getWidth() + x, c.getY());
    }

    public static Point debajo(JComponent c, int y) {
        return new Point(c.getX(), c.getY() + c.getHeight() + y);
    }

    public JLabel lblServer;
    public JTextField txtServer;
    public JLabel lblNombre;
    public JTextField txtNombre;
    public JButton btnConectar;
    public JLabel lblMensaje;
    public JTextField txtMensaje;
    public JButton btnEnviar;
    public JLabel lblDestinatario;
    public JTextField txtDestinatario;
    public JTextArea txtMensajes;
    ChatClienteChido cliente;

    private void inicializar() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chat!!!");
        setResizable(false);
        Dimension dimVentana = new Dimension(800, 600);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2,
            (dimPantalla.height - dimVentana.height) / 2
        );

        lblServer = new JLabel("Server:");
        lblServer.setLocation(new Point(10, 10));
        lblServer.setSize(lblServer.getPreferredSize());

        txtServer = new JTextField("172.20.10.10:1234");
        txtServer.setLocation(derecha(lblServer, 20));
        txtServer.setSize(120, 20);

        lblNombre = new JLabel("Nickame:");
        lblNombre.setLocation(derecha(txtServer, 100));
        lblNombre.setSize(lblNombre.getPreferredSize());

        txtNombre = new JTextField();
        txtNombre.setLocation(derecha(lblNombre, 20));
        txtNombre.setSize(120, 20);

        btnConectar = new JButton("Conectar");
        btnConectar.setLocation(derecha(txtNombre, 50));
        btnConectar.setSize(btnConectar.getPreferredSize());

        lblMensaje = new JLabel("Mensaje:");
        lblMensaje.setLocation(debajo(lblServer, 50));
        lblMensaje.setSize(lblMensaje.getPreferredSize());

        txtMensaje = new JTextField();
        txtMensaje.setLocation(derecha(lblMensaje, 20));
        txtMensaje.setSize(200, 20);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setLocation(derecha(txtMensaje, 20));
        btnEnviar.setSize(btnEnviar.getPreferredSize());

        lblDestinatario = new JLabel("Para:");
        lblDestinatario.setLocation(derecha(btnEnviar, 75));
        lblDestinatario.setSize(lblDestinatario.getPreferredSize());
        
        txtDestinatario = new JTextField();
        txtDestinatario.setLocation(derecha(lblDestinatario, 20));
        txtDestinatario.setSize(120, 20);

        txtMensajes = new JTextArea();
        txtMensajes.setLocation(debajo(lblMensaje, 20));
        txtMensajes.setSize(770, 450);

        cliente = null;
        btnConectar.addActionListener(e -> {
            if(cliente == null) try {

                cliente = new ChatClienteChido();
                ChatServer server = (ChatServer)Naming.lookup(
                    "//" + txtServer.getText() + "/chat"
                );
                cliente.nombre = txtNombre.getText();
                cliente.server = server;
                cliente.consola = m -> txtMensajes.append(m + '\n');
                server.registrarCliente(cliente.nombre, cliente);
                btnConectar.setEnabled(false);
                btnEnviar.setEnabled(true);
                
            } catch(Exception ex) {
                
                cliente = null;
                txtMensajes.setText(ex.getMessage() + '\n');
            }
        });

        btnEnviar.addActionListener(e -> {
            try {
                cliente.server.enviarMensaje(
                    cliente.nombre,
                    txtMensaje.getText(),
                    txtDestinatario.getText()
                );
            } catch(RemoteException ex) { }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(cliente != null) try {
                    cliente.server.olvidarCliente(cliente.nombre);
                    UnicastRemoteObject.unexportObject(cliente, true);
                } catch(RemoteException ex) { }
            }
        });

        setLayout(null);
        for(Field propiedad : getClass().getFields()) try {
            Object obj = propiedad.get(this);
            if(obj instanceof JComponent)
                add((JComponent)obj);
        } catch(IllegalAccessException ex) { }
        
        /*
        add(lblServer);
        add(txtServer);
        add(lblNombre);
        add(txtNombre);
        add(btnConectar);
        add(lblMensaje);
        add(txtMensaje);
        add(btnEnviar);
        add(txtMensajes);
        */
    }
}
