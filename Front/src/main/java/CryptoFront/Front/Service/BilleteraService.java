package CryptoFront.Front.Service;

import CryptoFront.Front.Dtos.BilleteraResponse;
import CryptoFront.Front.entities.Billetera;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BilleteraService {
    Optional<BilleteraResponse> crearBilletera(Long cedulaUsuario, Billetera billetera)
            throws IOException, InterruptedException;

    List<Billetera> listarBilleterasPorUsuario(Long cedulaUsuario)
            throws IOException, InterruptedException;
}