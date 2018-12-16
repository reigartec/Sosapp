package ado.edu.itla.sosapp.repositorio.usuario;

import ado.edu.itla.sosapp.entidad.Usuario;

public interface UsuarioRepositorio {
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    String logging(String email, String password);//no se esta usando la funcion
    Usuario buscarPor(int id);
}
