package CryptoFront.Front.Service;

import CryptoFront.Front.Dtos.BilleteraMonedaDto;
import java.util.List;

public interface BilleteraMonedaService {
    BilleteraMonedaDto crearBilleteraMoneda(BilleteraMonedaDto dto);
    List<BilleteraMonedaDto> listar();
    BilleteraMonedaDto depositar(Long billeteraId, Long monedaId, double monto);
    BilleteraMonedaDto retirar(Long billeteraId, Long monedaId, double monto);
}