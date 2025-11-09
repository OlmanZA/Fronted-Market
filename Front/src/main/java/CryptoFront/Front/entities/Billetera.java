package CryptoFront.Front.entities;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Billetera {

    private long  numeroBilletera;

    private String nombre;

    private Usuario usuario;

    private List<BilleteraMoneda> billeteraMonedas;

}

