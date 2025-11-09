package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.BilleteraController;
import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.entities.Billetera;

import javax.swing.*;
import java.awt.*;

public class BilleteraFrame extends JFrame {

    private final BilleteraController billeteraController;
    private final UsuarioController usuarioController;
    private final Long cedulaUsuario;

    public BilleteraFrame(BilleteraController controller, Long cedulaUsuario, UsuarioController usuarioController) {
        this.billeteraController = controller;
        this.cedulaUsuario = cedulaUsuario;
        this.usuarioController = usuarioController;
        initUI();
    }

    private void initUI() {
        setTitle("Crear Nueva Billetera");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JLabel lblTitulo = new JLabel("Crear Billetera");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblNombre = new JLabel("Nombre de la Billetera:");
        JTextField txtNombre = new JTextField(20);

        JButton btnCrear = new JButton("Crear");
        JButton btnVolver = new JButton("Volver");

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.");
                return;
            }

            Billetera nueva = new Billetera();
            nueva.setNombre(nombre);
            billeteraController.crearBilletera(cedulaUsuario, nueva);

            txtNombre.setText("");
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal(usuarioController, cedulaUsuario);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        add(lblNombre, gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(btnCrear, gbc);
        gbc.gridx = 1;
        add(btnVolver, gbc);

        setVisible(true);
    }
}
