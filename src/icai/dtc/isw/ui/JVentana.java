package icai.dtc.isw.ui;

import icai.dtc.isw.client.Client;
import icai.dtc.isw.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class JVentana extends JFrame {
    public static void main(String[] args) {
        new JVentana();
    }
    private int id;
    public JVentana() {
        super("INGENIERÍA DEL SOFTWARE");
        this.setLayout(new BorderLayout());
        //Pongo un panel arriba con el título
        JPanel pnlNorte = new JPanel();
        JLabel lblTitulo = new JLabel("Prueba COMUNICACIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 20));
        pnlNorte.add(lblTitulo);
        this.add(pnlNorte, BorderLayout.NORTH);

        //Pongo el panel central el botón
        JPanel pnlCentro = new JPanel();

        JLabel lblId = new JLabel("Introduzca el id", SwingConstants.CENTER);
        JButton btnInformacion = new JButton("Recibir información");
        JTextField txtId = new JTextField();
        txtId.setBounds(new Rectangle(250,150,250,150));
        txtId.setHorizontalAlignment(JTextField.LEFT);
        pnlCentro.add(lblId);
        pnlCentro.add(txtId);
        pnlCentro.add(btnInformacion);
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.	X_AXIS));
        this.add(pnlCentro, BorderLayout.CENTER);

        //El Sur lo hago para recoger el resultado
        JPanel pnlSur = new JPanel();
        JLabel lblResultado = new JLabel("El resultado obtenido es: ", SwingConstants.CENTER);
        JTextField txtResultado = new JTextField();
        txtResultado.setBounds(new Rectangle(250,150,250,150));
        txtResultado.setEditable(false);
        txtResultado.setHorizontalAlignment(JTextField.LEFT);
        pnlSur.add(lblResultado);
        pnlSur.add(txtResultado);
        //Añado el listener al botón
        btnInformacion.addActionListener(actionEvent -> {
            id=Integer.parseInt(txtId.getText());
            txtResultado.setText(recuperarInformacion());
        });
        pnlSur.setLayout(new BoxLayout(pnlSur, BoxLayout.X_AXIS));
        this.add(pnlSur,BorderLayout.SOUTH);

        this.setSize(550,120);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public String recuperarInformacion() {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getCustomer";
        session.put("id",id);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getName();
    }
}
