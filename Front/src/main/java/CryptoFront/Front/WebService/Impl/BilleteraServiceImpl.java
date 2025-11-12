package CryptoFront.Front.WebService.Impl;

import CryptoFront.Front.Dtos.BilleteraResponse;
import CryptoFront.Front.WebService.ApiService;
import CryptoFront.Front.WebService.BilleteraService;
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

    @Override
    public List<BilleteraResponse> obtenerSaldosPorUsuario(Long cedulaUsuario)
            throws IOException, InterruptedException {

        String endpoint = apiService.getBaseUrl() + "/Crypto/saldos/" + cedulaUsuario;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                Type listType = new TypeToken<List<BilleteraResponse>>() {}.getType();
                return gson.fromJson(new java.io.InputStreamReader(conn.getInputStream()), listType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}