package ado.edu.itla.sosapp.repositorio.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.Dbconexion;

public class UsuarioRepositorioimpl implements UsuarioRepositorio{
    //private Context context;
    private Dbconexion dbConexion;

    public UsuarioRepositorioimpl(Context context) {
        //this.context = context;
        this.dbConexion = new Dbconexion(context);
    }


    @Override
    public void guardar(Usuario usuario) {
        ContentValues cv = new ContentValues();
        cv.put("email", usuario.getEmail());
        cv.put("password", usuario.getPassword());
        cv.put("nombre", usuario.getNombre());
        //TODO: guardar usuario
    SQLiteDatabase db = dbConexion.getWritableDatabase();
           Long id = db.insert("usuario", null, cv);
           usuario.setId(id.intValue());

    }

    @Override
    public Usuario buscar(String email) {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor c = null;
        String nombre ="";
        Usuario usuario = null;;
        try
        {
            //String[] selectionArgs = {email};
            //c = db.rawQuery("SELECT email FROM usuario WHERE email = '"+email+"'", null);
            c = db.query("usuario",null,"lower(email)=?",
                    new String[]{email},null,null,null);
            if(c.moveToNext()){
                //nombre = c.getString(c.getColumnIndex("email"));
                //usuario = new Usuario();
                usuario = new Usuario();
                usuario.setId(c.getInt(c.getColumnIndex("id")));
                usuario.setNombre(c.getString(c.getColumnIndex("nombre")));
                usuario.setPassword(c.getString(c.getColumnIndex("password")));
                usuario.setEmail(c.getString(c.getColumnIndex("email")));

                c.close();
            }
            //c.moveToFirst();
            //nombre = c.getString(c.getColumnIndex("email"));
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return usuario;//nombre.toLowerCase();
    }
//Actualmente esta función 'logging' no se esta usando...
    public String logging(String email, String password)
    {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        email = email.toLowerCase();
        Cursor c = null;
        String mensaje = "";

        try
        {
            c = db.query("usuario",null,"lower(email)=? AND password=?",
                    new String[]{email,password},null,null,null);
            if(c.moveToNext())
            {
            mensaje=" Gracias por iniciar sesión "+c.getString(c.getColumnIndex("email"));
            c.close();
            }else{//mensaje="Registrate/Revisa tu usuario o contraseña!";
                 }
            c.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return mensaje;
    }

}
