package ado.edu.itla.sosapp.repositorio.usuario;

import ado.edu.itla.sosapp.entidad.Usuario;

public interface UsuarioRepositorio {
    void guardar(Usuario usuario);
    String buscar(String email);
    String logging(String email, String password);
}
