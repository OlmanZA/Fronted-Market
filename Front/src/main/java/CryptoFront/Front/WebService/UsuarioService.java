package CryptoFront.Front.WebService;

import CryptoFront.Front.Dtos.LoginResponse;
import CryptoFront.Front.entities.Usuario;

import java.io.IOException;
import java.util.Optional;

public interface UsuarioService {
    Optional<LoginResponse> login(Usuario credenciales) throws IOException, InterruptedException;
    Optional<Usuario> register(Usuario usuario) throws IOException, InterruptedException;
}