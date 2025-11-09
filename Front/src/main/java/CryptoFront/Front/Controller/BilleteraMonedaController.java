package CryptoFront.Front.Controller;

import CryptoFront.Front.Dtos.BilleteraMonedaDto;
import CryptoFront.Front.Service.BilleteraMonedaService;

import javax.swing.*;
import java.util.List;

public class BilleteraMonedaController {

    private final BilleteraMonedaService service;

    public BilleteraMonedaController(BilleteraMonedaService service) {
        this.service = service;
    }

    public List<BilleteraMonedaDto> listar() {
        try {
            return service.listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar monedas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    public BilleteraMonedaDto crearBilleteraMoneda(BilleteraMonedaDto dto) {
        try {
            return service.crearBilleteraMoneda(dto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear billetera-moneda: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public BilleteraMonedaDto depositar(Long billeteraId, Long monedaId, double monto) {
        try {
            BilleteraMonedaDto result = service.depositar(billeteraId, monedaId, monto);
            if (result != null) {
                JOptionPane.showMessageDialog(null, "✅ Depósito exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al depositar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public BilleteraMonedaDto retirar(Long billeteraId, Long monedaId, double monto) {
        try {
            BilleteraMonedaDto result = service.retirar(billeteraId, monedaId, monto);
            if (result != null) {
                JOptionPane.showMessageDialog(null, "✅ Retiro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al retirar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}