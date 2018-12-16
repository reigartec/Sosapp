package ado.edu.itla.sosapp.repositorio.solicitud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.widget.Toast;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ado.edu.itla.sosapp.InicioActivity;
import ado.edu.itla.sosapp.entidad.AreaAfin;
import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.Dbconexion;
import ado.edu.itla.sosapp.repositorio.areas.AreaRepositorioimpl;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioimpl;

public class SolicitudRepositorioimpl implements SolicitudRepositorio {
    //private Context context;
    private Dbconexion dbConexion;
    private Context conext;
    public SolicitudRepositorioimpl(Context context)
    {

        dbConexion = new Dbconexion(context);
        conext = context;
    }
    @Override
    public void guardar(Solicitud solicitud) {
        ContentValues cv = new ContentValues();
        cv.put("fecha", solicitud.getFecha().getTime());
        cv.put("descripcion", solicitud.getDescripcion());
        cv.put("titulo", solicitud.getTitulo());
        cv.put("estado", solicitud.getEstado().toString());
        cv.put("areaafin", solicitud.getAreaAfin().getId());
        cv.put("usuario_solicitante_id", solicitud.getUsuarioSolicitante().getId());
        if (solicitud.isActualizando()) {
            cv.put("usuario_asignado_id", solicitud.getUsuarioAsignado().getId());
        } else {

            cv.put("usuario_asignado_id", 0);
        }

        //TODO: guardar solicitud
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        if (solicitud.isActualizando()) {
           int count = db.update("solicitud", cv, "id=?", new String[]{String.valueOf(solicitud.getId())});
           //conteo de los datos.
        } else {
            Long id = db.insert("solicitud", null, cv);
            solicitud.setId(id.intValue());
        }
/*************RECORDAR USAR CLASE SOLICITUD CUANDO SE VAYA ACTUALIZAR, EN EL MOMENTO
 * EN QUE EL USUARIO ASIGNAR ESCOJA ESA SOLICITUD***************/

    }

    @Override
    public List<Solicitud> buscarSolicitudesPor(Usuario usuario) {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor c = null;
        String nombre ="";
        List<Solicitud> solicitudes = new ArrayList<>();
        Solicitud solicitud = null;
        try
        {
            //String[] selectionArgs = {email};
            //c = db.rawQuery("SELECT email FROM usuario WHERE email = '"+email+"'", null);
            c = db.query("solicitud",null,"usuario_solicitante_id=?",
                    new String[]{String.valueOf(usuario.getId())},null,null,null);

            while(c.moveToNext()){
                //nombre = c.getString(c.getColumnIndex("email"));
                //usuario = new Usuario();
                solicitud = new Solicitud();

                solicitud.setId(c.getInt(c.getColumnIndex("id")));
                solicitud.setTitulo(c.getString(c.getColumnIndex("titulo")));
                solicitud.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                int id = c.getInt(c.getColumnIndex("areaafin"));
                AreaAfin afin = new AreaRepositorioimpl(conext).buscarPor(id);
                solicitud.setAreaAfin(afin);
                Usuario usu =  new UsuarioRepositorioimpl(conext).buscarPor(c.getInt(c.getColumnIndex("usuario_solicitante_id")));
                solicitud.setUsuarioSolicitante(usu);
                String estado = c.getString(c.getColumnIndex("estado"));
                if(estado.equals(Solicitud.Estado.Pendiente.toString()))
                {solicitud.setEstado(Solicitud.Estado.Pendiente);}else if(estado.equals(Solicitud.Estado.Proceso.toString()))
                {solicitud.setEstado(Solicitud.Estado.Proceso);}else{solicitud.setEstado(Solicitud.Estado.Terminado);}
                long nfecha = c.getInt(c.getColumnIndex("fecha"));
                Date fechad = new Date(nfecha);
                solicitud.setFecha(fechad);
                Usuario usua =  new UsuarioRepositorioimpl(conext).buscarPor(c.getInt(c.getColumnIndex("usuario_asignado_id")));
                solicitud.setUsuarioAsignado(usua);


                solicitudes.add(solicitud);

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
        return solicitudes;
    }

    @Override
    public List<Solicitud> buscarSolicitudesAfinesA(Usuario usuario) {
        return null;
    }

    @Override
    public List<Solicitud> buscarTodos() {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor c = null;
        String nombre ="";
        List<Solicitud> solicitudes = new ArrayList<>();
        Solicitud solicitud = null;
        try
        {
            //String[] selectionArgs = {email};
            //c = db.rawQuery("SELECT email FROM usuario WHERE email = '"+email+"'", null);
            c = db.query("solicitud",null,null,
                    null,null,null,null);

            while(c.moveToNext()){
                //nombre = c.getString(c.getColumnIndex("email"));
                //usuario = new Usuario();
                solicitud = new Solicitud();

                solicitud.setId(c.getInt(c.getColumnIndex("id")));
                solicitud.setTitulo(c.getString(c.getColumnIndex("titulo")));
                solicitud.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                int id = c.getInt(c.getColumnIndex("areaafin"));
                AreaAfin afin = new AreaRepositorioimpl(conext).buscarPor(id);
                solicitud.setAreaAfin(afin);
                Usuario usu =  new UsuarioRepositorioimpl(conext).buscarPor(c.getInt(c.getColumnIndex("usuario_solicitante_id")));
                solicitud.setUsuarioSolicitante(usu);
                String estado = c.getString(c.getColumnIndex("estado"));
                if(estado.equals(Solicitud.Estado.Pendiente.toString()))
                {solicitud.setEstado(Solicitud.Estado.Pendiente);}else if(estado.equals(Solicitud.Estado.Proceso.toString()))
                {solicitud.setEstado(Solicitud.Estado.Proceso);}else{solicitud.setEstado(Solicitud.Estado.Terminado);}
                long nfecha = c.getInt(c.getColumnIndex("fecha"));
                Date fechad = new Date(nfecha);
                solicitud.setFecha(fechad);
                Usuario usua =  new UsuarioRepositorioimpl(conext).buscarPor(c.getInt(c.getColumnIndex("usuario_asignado_id")));
                solicitud.setUsuarioAsignado(usua);


                solicitudes.add(solicitud);

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
        return solicitudes;
    }


}
