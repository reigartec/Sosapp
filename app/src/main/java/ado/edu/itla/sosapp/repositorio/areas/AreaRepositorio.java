package ado.edu.itla.sosapp.repositorio.areas;

import java.util.ArrayList;

import ado.edu.itla.sosapp.entidad.AreaAfin;
import ado.edu.itla.sosapp.entidad.Usuario;

public interface AreaRepositorio {
    ArrayList <AreaAfin> buscarTodos();
    AreaAfin buscarPor(int id);
}
