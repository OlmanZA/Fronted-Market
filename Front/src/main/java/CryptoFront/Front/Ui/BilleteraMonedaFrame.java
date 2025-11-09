package CryptoFront.Front.Ui;

import CryptoFront.Front.Controller.BilleteraController;
import CryptoFront.Front.Controller.BilleteraMonedaController;
import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.Dtos.BilleteraMonedaDto;
import CryptoFront.Front.entities.Billetera;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class BilleteraMonedaFrame extends JFrame {

    private final BilleteraMonedaController billeteraMonedaController;
    private final BilleteraController billeteraController;
    private final UsuarioController usuarioController;
    private final Long cedulaUsuario;

    private final DefaultListModel<Billetera> billeteraListModel = new DefaultListModel<>();
    private final JList<Billetera> listaBilleteras = new JList<>(billeteraListModel);

    private final DefaultListModel<BilleteraMonedaDto> monedasListModel = new DefaultListModel<>();
    private final JList<BilleteraMonedaDto> listaMonedas = new JList<>(monedasListModel);

    public BilleteraMonedaFrame(BilleteraMonedaController billeteraMonedaController,
                                BilleteraController billeteraController,
                                Long cedulaUsuario,
                                UsuarioController usuarioController) {

        this.billeteraMonedaController = billeteraMonedaController;
        this.billeteraController = billeteraController;
        this.cedulaUsuario = cedulaUsuario;
        this.usuarioController = usuarioController;

        initUI();
    }

    private void initUI() {
        setTitle("💰 Administrar Billeteras y Monedas");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout());
        JLabel lblTitulo = new JLabel("Gestión de Billeteras y Monedas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Tus Billeteras"));
        listaBilleteras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelIzquierdo.add(new JScrollPane(listaBilleteras), BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Monedas de la Billetera"));
        listaMonedas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelDerecho.add(new JScrollPane(listaMonedas), BorderLayout.CENTER);

        panelCentral.add(panelIzquierdo);
        panelCentral.add(panelDerecho);
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnListar = new JButton("📋 Listar Monedas");
        JButton btnDepositar = new JButton("➕ Depositar");
        JButton btnRetirar = new JButton("➖ Retirar");
        JButton btnVolver = new JButton("Volver");

        panelBotones.add(btnListar);
        panelBotones.add(btnDepositar);
        panelBotones.add(btnRetirar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        cargarBilleteras();

        btnListar.addActionListener(e -> listarMonedas());
        btnDepositar.addActionListener(e -> realizarOperacion(true));
        btnRetirar.addActionListener(e -> realizarOperacion(false));
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal(usuarioController, cedulaUsuario);
        });

        setVisible(true);
    }

    private void cargarBilleteras() {
        billeteraListModel.clear();
        List<Billetera> billeteras = billeteraController.listarBilleterasPorUsuario(cedulaUsuario);

        if (billeteras == null || billeteras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes billeteras registradas.");
        } else {
            billeteras.forEach(billeteraListModel::addElement);
        }
    }

    private void listarMonedas() {
        Billetera billetera = listaBilleteras.getSelectedValue();
        if (billetera == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una billetera primero.");
            return;
        }

        monedasListModel.clear();
        List<BilleteraMonedaDto> todasLasMonedas = billeteraMonedaController.listar();

        List<BilleteraMonedaDto> monedasDeLaBilletera = todasLasMonedas.stream()
                .filter(m -> m.getBilleteraId().equals(billetera.getNumeroBilletera()))
                .collect(Collectors.toList());

        if (monedasDeLaBilletera.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Esta billetera no tiene monedas registradas.");
        } else {
            monedasDeLaBilletera.forEach(monedasListModel::addElement);
        }
    }

    private void realizarOperacion(boolean esDeposito) {
        Billetera billetera = listaBilleteras.getSelectedValue();
        if (billetera == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una billetera primero.");
            return;
        }

        String monedaIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la moneda:");
        if (monedaIdStr == null || monedaIdStr.isBlank()) return;

        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto:");
        if (montoStr == null || montoStr.isBlank()) return;

        try {
            Long monedaId = Long.parseLong(monedaIdStr);
            double monto = Double.parseDouble(montoStr);

            if (esDeposito) {
                billeteraMonedaController.depositar(billetera.getNumeroBilletera(), monedaId, monto);
            } else {
                billeteraMonedaController.retirar(billetera.getNumeroBilletera(), monedaId, monto);
            }

            listarMonedas();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifique que sean números válidos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
