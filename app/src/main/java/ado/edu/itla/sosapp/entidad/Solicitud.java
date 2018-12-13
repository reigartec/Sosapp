package ado.edu.itla.sosapp.entidad;

import java.util.Date;

public class Solicitud {

    public enum Estado{Pendiente,Proceso,Terminado};
    //public enum AreaAfin{Tecnología,Ingeniería,Arquitectura,Bancario,Comunicación,Hogar,};

    private int id;
    private String descripcion;
    private AreaAfin areaAfin;
    private Estado estado;
    private String titulo;
    private Date fecha;
    private Usuario usuarioAsignado;
    private Usuario usuarioSolicitante;
    private boolean actualizando=false;

    public boolean isActualizando() {
        return actualizando;
    }

    public void setActualizando(boolean actualizando) {
        this.actualizando = actualizando;
    }



    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AreaAfin getAreaAfin() {
        return areaAfin;
    }

    public void setAreaAfin(AreaAfin areaAfin) {
        this.areaAfin = areaAfin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

       public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}
