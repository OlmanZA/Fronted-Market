package CryptoFront.Front.Controller;

import CryptoFront.Front.Service.MonedaService;
import CryptoFront.Front.entities.Moneda;

import javax.swing.*;
import java.util.Optional;

public class MonedaController {

    private final MonedaService monedaService;

    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    public Optional<Moneda> crearMoneda(Moneda moneda) {
        try {
            Optional<Moneda> response = monedaService.crearMoneda(moneda);

            if (response.isPresent()) {
                Moneda monedaCreada = response.get();
                JOptionPane.showMessageDialog(null,
                        "Moneda creada exitosamente:\n" +
                                "Nombre: " + monedaCreada.getNombre() + "\n" +
                                "Símbolo: " + monedaCreada.getSimbolo(),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la moneda.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            return response;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al conectar con el servidor: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }
}
