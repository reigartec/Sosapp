package ado.edu.itla.sosapp.repositorio.solicitud;

import java.util.List;

import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.entidad.Usuario;

public interface SolicitudRepositorio  {
    void guardar(Solicitud solicitud);
    public List<Solicitud> buscarSolicitudesPor(Usuario usuario);
    public List<Solicitud> buscarSolicitudesAfinesA (Usuario usuario);
    public List<Solicitud> buscarTodos();
    public Solicitud buscarPor(int id);
}
