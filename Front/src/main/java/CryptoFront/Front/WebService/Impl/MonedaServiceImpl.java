package CryptoFront.Front.WebService.Impl;

import CryptoFront.Front.WebService.MonedaService;
import CryptoFront.Front.WebService.ApiService;
import CryptoFront.Front.entities.Moneda;

import java.io.IOException;
import java.util.Optional;

public class MonedaServiceImpl implements MonedaService {

    private final ApiService apiService;

    public MonedaServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Optional<Moneda> crearMoneda(Moneda moneda) throws IOException, InterruptedException {
        // 🔥 solo el endpoint, sin repetir localhost
        String endpoint = "/Crypto/CrearMoneda";
        return apiService.post(endpoint, moneda, Moneda.class);
    }
}
