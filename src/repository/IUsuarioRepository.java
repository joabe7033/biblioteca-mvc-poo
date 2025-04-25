package repository;

import model.Usuario;
import java.util.List;

public interface IUsuarioRepository {
    void cadastrarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
}
