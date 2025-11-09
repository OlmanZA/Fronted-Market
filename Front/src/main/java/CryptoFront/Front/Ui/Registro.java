package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Registro extends JFrame {

    private final UsuarioController controller;

    public Registro(UsuarioController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Registrar Usuario - Crypto");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField(20);

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(20);

        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField(20);

        JLabel lblFecha = new JLabel("Fecha Nac (YYYY-MM-DD):");
        JTextField txtFecha = new JTextField(20);

        JLabel lblPais = new JLabel("País de nacimiento:");
        JTextField txtPais = new JTextField(20);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");

        btnRegistrar.addActionListener(e -> {
            try {
                Usuario u = new Usuario();
                u.setCedula(Long.parseLong(txtCedula.getText().trim()));
                u.setNombre(txtNombre.getText().trim());
                u.setCorreo(txtCorreo.getText().trim());
                u.setContraseña(new String(txtContraseña.getPassword()));
                u.setFecha_nac(txtFecha.getText().trim());
                u.setPais_nacimiento(txtPais.getText().trim());

                Optional<Usuario> registrado = controller.registrarUsuario(u);
                if (registrado.isPresent()) {
                    JOptionPane.showMessageDialog(this, "Usuario registrado con éxito ✅");
                    dispose();
                    new MenuInicio(controller);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cédula debe ser numérica",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new MenuInicio(controller);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblCedula, gbc); gbc.gridx = 1; add(txtCedula, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNombre, gbc); gbc.gridx = 1; add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblCorreo, gbc); gbc.gridx = 1; add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(lblContraseña, gbc); gbc.gridx = 1; add(txtContraseña, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(lblFecha, gbc); gbc.gridx = 1; add(txtFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(lblPais, gbc); gbc.gridx = 1; add(txtPais, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        gbc.gridy = 7;
        add(btnVolver, gbc);

        setVisible(true);
    }
}
