package CryptoFront.Front;

import CryptoFront.Front.Controller.UsuarioController;
import CryptoFront.Front.WebService.Impl.ApiServiceImpl;
import CryptoFront.Front.WebService.Impl.UsuarioServiceImpl;
import CryptoFront.Front.Ui.MenuInicio;

public class FrontApplication {
    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080";

        ApiServiceImpl apiService = new ApiServiceImpl(baseUrl);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(apiService);
        UsuarioController usuarioController = new UsuarioController(usuarioService);

        javax.swing.SwingUtilities.invokeLater(() -> new MenuInicio(usuarioController));
    }
}
