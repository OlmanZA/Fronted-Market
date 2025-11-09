package CryptoFront.Front.Service.Impl;

import CryptoFront.Front.Dtos.LoginResponse;
import CryptoFront.Front.Service.ApiService;
import CryptoFront.Front.Service.UsuarioService;
import CryptoFront.Front.entities.Usuario;

import java.io.IOException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private final ApiService apiService;

    public UsuarioServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Optional<LoginResponse> login(Usuario credenciales) throws IOException, InterruptedException {
        String endpoint = "/Crypto/IniciarSesion";
        return apiService.post(endpoint, credenciales, LoginResponse.class);
    }

    @Override
    public Optional<Usuario> register(Usuario usuario) throws IOException, InterruptedException {
        String endpoint = "/Crypto/CrearUsuario";
        return apiService.post(endpoint, usuario, Usuario.class);
    }
}