package ado.edu.itla.sosapp.entidad;

public class Usuario {
    public int id;
    private String nombre;
   // private String username;
    private String password;
    private String email;
    //private int edad;

   /* public void setEdad(int edad) throws Exception {

        if (edad<0 || edad>130){
            throw new RuntimeException("Error: Edad no permitida.");
        }

        this.edad = edad;

    }*/

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    /*public String getUsername() {
        return username;
    }*/

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    /*public int getEdad(){
        return edad;
    }*/


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public void setUsername(String username) {
        this.username = username;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                //", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                //", edad=" + edad +
                '}';
    }
}
