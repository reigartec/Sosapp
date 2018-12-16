package ado.edu.itla.sosapp.repositorio.areas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ado.edu.itla.sosapp.entidad.AreaAfin;
import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.Dbconexion;

public class AreaRepositorioimpl implements AreaRepositorio {

    private Dbconexion dbConexion;

    public AreaRepositorioimpl(Context context) {
        //this.context = context;
        this.dbConexion = new Dbconexion(context);
    }
    @Override
    public ArrayList<AreaAfin> buscarTodos() {

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor c = null;
        String nombre ="";
        ArrayList<AreaAfin> area = new ArrayList<>();
        AreaAfin areafin = null;
        try
        {
            //String[] selectionArgs = {email};
            //c = db.rawQuery("SELECT email FROM usuario WHERE email = '"+email+"'", null);
            c = db.query("catalogo_area",null,null,
                    null,null,null,null);

            while(c.moveToNext()){
                //nombre = c.getString(c.getColumnIndex("email"));
                //usuario = new Usuario();
                areafin = new AreaAfin();

                areafin.setId(c.getInt(c.getColumnIndex("id")));
                areafin.setNombre(c.getString(c.getColumnIndex("area")));

                area.add(areafin);
            }
            c.close();
            //c.moveToFirst();
            //nombre = c.getString(c.getColumnIndex("email"));
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //return usuario;//nombre.toLowerCase();
        return area;
    }

    @Override
    public AreaAfin buscarPor(int id) {

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor c = null;
        String nombre ="";

        AreaAfin areafin = null;
        try
        {
            //String[] selectionArgs = {email};
            //c = db.rawQuery("SELECT email FROM usuario WHERE email = '"+email+"'", null);
            c = db.query("catalogo_area",null,"id=?",
                    new String[]{String.valueOf(id)},null,null,null);

            while(c.moveToNext()){
                //nombre = c.getString(c.getColumnIndex("email"));
                //usuario = new Usuario();
                areafin = new AreaAfin();

                areafin.setId(c.getInt(c.getColumnIndex("id")));
                areafin.setNombre(c.getString(c.getColumnIndex("area")));

                //area.add(areafin);
            }
            c.close();
            //c.moveToFirst();
            //nombre = c.getString(c.getColumnIndex("email"));
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //return usuario;//nombre.toLowerCase();
        return areafin;
    }
}
