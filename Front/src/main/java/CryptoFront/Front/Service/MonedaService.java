package CryptoFront.Front.Service;

import CryptoFront.Front.entities.Moneda;
import java.io.IOException;
import java.util.Optional;

public interface MonedaService {
    Optional<Moneda> crearMoneda(Moneda moneda) throws IOException, InterruptedException;
}

