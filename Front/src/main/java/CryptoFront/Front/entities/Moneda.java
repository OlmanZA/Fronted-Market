package CryptoFront.Front.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Moneda {


    private Integer idMoneda;

    private String nombre;

    private String simbolo;
}
