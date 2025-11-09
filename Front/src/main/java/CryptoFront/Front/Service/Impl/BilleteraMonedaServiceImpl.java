package CryptoFront.Front.Service.Impl;

import CryptoFront.Front.Dtos.BilleteraMonedaDto;
import CryptoFront.Front.Service.ApiService;
import CryptoFront.Front.Service.BilleteraMonedaService;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BilleteraMonedaServiceImpl implements BilleteraMonedaService {

    private final ApiService apiService;
    private final Gson gson = new Gson();

    public BilleteraMonedaServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public BilleteraMonedaDto crearBilleteraMoneda(BilleteraMonedaDto dto) {
        try {
            String endpoint = "/billetera-moneda/crearBilleteraMoneda";
            return apiService.post(endpoint, dto, BilleteraMonedaDto.class).orElse(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BilleteraMonedaDto> listar() {
        try {
            String endpoint = apiService.getBaseUrl() + "/billetera-moneda/listar";
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BilleteraMonedaDto[] monedas = gson.fromJson(
                        new java.io.InputStreamReader(conn.getInputStream()),
                        BilleteraMonedaDto[].class
                );
                return Arrays.asList(monedas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public BilleteraMonedaDto depositar(Long billeteraId, Long monedaId, double monto) {
        try {
            String endpoint = String.format("/billetera-moneda/depositar?billeteraId=%d&monedaId=%d&monto=%.2f",
                    billeteraId, monedaId, monto);
            return apiService.post(endpoint, null, BilleteraMonedaDto.class).orElse(null);
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
            return apiService.post(endpoint, null, BilleteraMonedaDto.class).orElse(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}