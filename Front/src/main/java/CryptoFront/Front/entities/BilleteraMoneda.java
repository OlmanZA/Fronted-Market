package CryptoFront.Front.entities;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BilleteraMoneda {

    private Long id;

    private Billetera billetera;

    private Moneda moneda;

    private BigDecimal cantidad = BigDecimal.ZERO;
}
