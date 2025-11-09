package CryptoFront.Front.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppRestTemplate {

        public static final String BASE_URL = "http://localhost:8080/Crypto";

        @Bean
        public static RestTemplate restTemplate() {
            return new RestTemplate();

        }

}