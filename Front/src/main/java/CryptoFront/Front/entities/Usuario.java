    package CryptoFront.Front.entities;

    import lombok.*;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Usuario {

        private Long cedula;

        private String nombre;
        private String correo;
        private String contraseña;
        private String fecha_nac;
        private String pais_nacimiento;

    }
