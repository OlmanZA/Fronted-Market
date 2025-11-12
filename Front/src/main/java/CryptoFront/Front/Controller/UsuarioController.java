package CryptoFront.Front.Controller;

import CryptoFront.Front.Dtos.LoginResponse;
import CryptoFront.Front.WebService.UsuarioService;
import CryptoFront.Front.entities.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Optional<LoginResponse> iniciarSesion(String correo, String contraseña) {
        if (correo == null || correo.isBlank() || contraseña == null || contraseña.isBlank()) {
            JOptionPane.showMessageDialog(null, "Debes ingresar correo y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        Usuario cred = new Usuario();
        cred.setCorreo(correo);
        cred.setContraseña(contraseña);

        try {
            return usuarioService.login(cred);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }

    public Optional<Usuario> registrarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getCorreo() == null || usuario.getContraseña() == null) {
            JOptionPane.showMessageDialog(null, "Completa todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        try {
            return usuarioService.register(usuario);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }
}