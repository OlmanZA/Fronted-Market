package CryptoFront.Front.Service.Impl;

import CryptoFront.Front.Dtos.BilleteraResponse;
import CryptoFront.Front.Service.ApiService;
import CryptoFront.Front.Service.BilleteraService;
import CryptoFront.Front.entities.Billetera;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BilleteraServiceImpl implements BilleteraService {

    private final ApiService apiService;
    private final Gson gson = new Gson();

    public BilleteraServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Optional<BilleteraResponse> crearBilletera(Long cedulaUsuario, Billetera billetera)
            throws IOException, InterruptedException {
        String endpoint = "/Crypto/CrearBilletera/" + cedulaUsuario;
        return apiService.post(endpoint, billetera, BilleteraResponse.class);
    }

    @Override
    public List<Billetera> listarBilleterasPorUsuario(Long cedulaUsuario)
            throws IOException, InterruptedException {

        String endpoint = apiService.getBaseUrl() + "/Crypto/ListarBilleteras/" + cedulaUsuario;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                Billetera[] billeteras = gson.fromJson(
                        new java.io.InputStreamReader(conn.getInputStream()),
                        Billetera[].class
                );
                return Arrays.asList(billeteras);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}