package CryptoFront.Front.WebService;

import java.io.IOException;
import java.util.Optional;

public interface ApiService {
    <T> Optional<T> post(String endpoint, Object body, Class<T> responseType)
            throws IOException, InterruptedException;

    <T> Optional<T> get(String endpoint, Class<T> responseType)
            throws IOException, InterruptedException;

    String getBaseUrl();
}