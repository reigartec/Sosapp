package ado.edu.itla.sosapp.entidad;

public class AreaAfin {

    private int id;

    @Override
    public String toString() {
        return nombre;
    }

    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
