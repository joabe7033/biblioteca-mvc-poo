package controller;

import model.Usuario;
import repository.IUsuarioRepository;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController implements IUsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
