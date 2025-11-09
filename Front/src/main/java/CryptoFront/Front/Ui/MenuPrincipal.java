package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.BilleteraController;
import CryptoFront.Front.Controller.BilleteraMonedaController;
import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.Service.Impl.ApiServiceImpl;
import CryptoFront.Front.Service.Impl.BilleteraMonedaServiceImpl;
import CryptoFront.Front.Service.Impl.BilleteraServiceImpl;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private final Long cedulaUsuario;
    private final UsuarioController usuarioController;

    public MenuPrincipal(UsuarioController usuarioController, Long cedulaUsuario) {
        this.usuarioController = usuarioController;
        this.cedulaUsuario = cedulaUsuario;
        initUI();
    }

    private void initUI() {
        setTitle("🏦 Menú Principal - CryptoFront");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblBienvenida = new JLabel("Usuario: " + cedulaUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCrearBilletera = new JButton("🪙 Crear Billetera");
        JButton btnAdministrarBilleteras = new JButton("💰 Administrar Billeteras");
        JButton btnSalir = new JButton("Salir");

        add(lblBienvenida);
        add(btnCrearBilletera);
        add(btnAdministrarBilleteras);
        add(btnSalir);

        ApiServiceImpl api = new ApiServiceImpl("http://localhost:8080");
        BilleteraServiceImpl billeteraService = new BilleteraServiceImpl(api);
        BilleteraController billeteraController = new BilleteraController(billeteraService);

        BilleteraMonedaServiceImpl billeteraMonedaService = new BilleteraMonedaServiceImpl(api);
        BilleteraMonedaController billeteraMonedaController = new BilleteraMonedaController(billeteraMonedaService);

        btnCrearBilletera.addActionListener(e -> {
            dispose();
            new BilleteraFrame(billeteraController, cedulaUsuario, usuarioController);
        });

        btnAdministrarBilleteras.addActionListener(e -> {
            dispose();
            new BilleteraMonedaFrame(billeteraMonedaController, billeteraController, cedulaUsuario, usuarioController);
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new MenuInicio(usuarioController);
        });

        setVisible(true);
    }
}
