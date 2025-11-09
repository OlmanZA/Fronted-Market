package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class MenuInicio extends JFrame {

    private final UsuarioController controller;

    public MenuInicio(UsuarioController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Crypto - Menú de Inicio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Bienvenido a Crypto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistro = new JButton("Registrar Usuario");

        btnLogin.addActionListener(e -> {
            dispose();
            new Login(controller);
        });

        btnRegistro.addActionListener(e -> {
            dispose();
            new Registro(controller);
        });

        add(lblTitulo);
        add(btnLogin);
        add(btnRegistro);

        setVisible(true);
    }
}