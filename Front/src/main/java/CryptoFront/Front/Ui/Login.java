package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.Dtos.LoginResponse;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Login extends JFrame {

    private final UsuarioController controller;

    public Login(UsuarioController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Iniciar Sesión - Crypto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(20);

        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField(20);

        JButton btnLogin = new JButton("Ingresar");
        JButton btnVolver = new JButton("Volver");

        btnLogin.addActionListener(e -> {
            String correo = txtCorreo.getText().trim();
            String pass = new String(txtContraseña.getPassword());

            Optional<LoginResponse> respuesta = controller.iniciarSesion(correo, pass);
            if (respuesta.isPresent() && respuesta.get().isSuccess()) {
                Long cedulaUsuario = respuesta.get().getUsuario().getCedula();
                String nombreUsuario = respuesta.get().getUsuario().getNombre();

                JOptionPane.showMessageDialog(this, "Bienvenido " + nombreUsuario + "!",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new MenuPrincipal(controller, cedulaUsuario);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new MenuInicio(controller);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblCorreo, gbc);
        gbc.gridx = 1;
        add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblContraseña, gbc);
        gbc.gridx = 1;
        add(txtContraseña, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        gbc.gridy = 3;
        add(btnVolver, gbc);

        setVisible(true);
    }
}