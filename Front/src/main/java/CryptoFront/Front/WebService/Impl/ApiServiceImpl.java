package CryptoFront.Front.WebService.Impl;

import CryptoFront.Front.WebService.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ApiServiceImpl implements ApiService {

    private final String baseUrl;
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiServiceImpl(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public <T> Optional<T> post(String endpoint, Object body, Class<T> responseType)
            throws IOException, InterruptedException {

        String jsonBody = gson.toJson(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            T data = gson.fromJson(response.body(), responseType);
            return Optional.ofNullable(data);
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> get(String endpoint, Class<T> responseType)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            T data = gson.fromJson(response.body(), responseType);
            return Optional.ofNullable(data);
        }
        return Optional.empty();
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}