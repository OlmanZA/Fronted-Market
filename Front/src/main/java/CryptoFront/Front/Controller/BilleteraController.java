package CryptoFront.Front.Controller;

import CryptoFront.Front.Dtos.BilleteraResponse;
import CryptoFront.Front.WebService.BilleteraService;
import CryptoFront.Front.entities.Billetera;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BilleteraController {

    private final BilleteraService billeteraService;

    public BilleteraController(BilleteraService billeteraService) {
        this.billeteraService = billeteraService;
    }

    // 🔹 Crear billetera
    public Optional<BilleteraResponse> crearBilletera(Long cedulaUsuario, Billetera billetera) {
        if (billetera == null || cedulaUsuario == null) {
            JOptionPane.showMessageDialog(null, "Datos inválidos para crear billetera.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        try {
            Optional<BilleteraResponse> response = billeteraService.crearBilletera(cedulaUsuario, billetera);

            if (response.isPresent() && response.get().isSuccess()) {
                JOptionPane.showMessageDialog(null,
                        "✅ " + response.get().getMessage(),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "⚠️ " + (response.isPresent() ? response.get().getMessage() : "Error desconocido"),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            return response;

        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al conectar con el servidor: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }

    // 🔹 Listar billeteras del usuario
    public List<Billetera> listarBilleterasPorUsuario(Long cedulaUsuario) {
        try {
            return billeteraService.listarBilleterasPorUsuario(cedulaUsuario);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al obtener las billeteras: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    // 🔹 Obtener saldos (billeteras + monedas)
    public List<BilleteraResponse> obtenerSaldosPorUsuario(Long cedulaUsuario) {
        try {
            List<BilleteraResponse> saldos = billeteraService.obtenerSaldosPorUsuario(cedulaUsuario);

            if (saldos.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "El usuario no tiene monedas registradas.",
                        "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            }

            return saldos;

        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al obtener los saldos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}
