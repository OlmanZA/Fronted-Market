package CryptoFront.Front.WebService.Impl;

import CryptoFront.Front.Dtos.BilleteraMonedaDto;
import CryptoFront.Front.WebService.ApiService;
import CryptoFront.Front.WebService.BilleteraMonedaService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BilleteraMonedaServiceImpl implements BilleteraMonedaService {

    private final ApiService apiService;

    public BilleteraMonedaServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public BilleteraMonedaDto crearBilleteraMoneda(BilleteraMonedaDto dto) {
        try {
            String endpoint = "/billetera-moneda/crear";
            Optional<BilleteraMonedaDto> response = apiService.post(endpoint, dto, BilleteraMonedaDto.class);
            return response.orElse(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BilleteraMonedaDto> listar() {
        try {
            String endpoint = "/billetera-moneda/listar";
            Optional<BilleteraMonedaDto[]> response = apiService.get(endpoint, BilleteraMonedaDto[].class);
            return response.map(Arrays::asList).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public BilleteraMonedaDto depositar(Long billeteraId, Long monedaId, double monto) {
        try {
            String endpoint = String.format("/billetera-moneda/depositar?billeteraId=%d&monedaId=%d&monto=%.2f",
                    billeteraId, monedaId, monto);
            Optional<BilleteraMonedaDto> response = apiService.post(endpoint, null, BilleteraMonedaDto.class);
            return response.orElse(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BilleteraMonedaDto retirar(Long billeteraId, Long monedaId, double monto) {
        try {
            String endpoint = String.format("/billetera-moneda/retirar?billeteraId=%d&monedaId=%d&monto=%.2f",
                    billeteraId, monedaId, monto);
            Optional<BilleteraMonedaDto> response = apiService.post(endpoint, null, BilleteraMonedaDto.class);
            return response.orElse(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
