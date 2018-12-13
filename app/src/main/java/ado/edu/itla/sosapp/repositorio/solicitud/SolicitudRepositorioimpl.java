package ado.edu.itla.sosapp.repositorio.solicitud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.Dbconexion;

public class SolicitudRepositorioimpl implements SolicitudRepositorio {
    //private Context context;
    private Dbconexion dbConexion;

    public SolicitudRepositorioimpl(Context context)
    {
        dbConexion = new Dbconexion(context);
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
        return null;
    }

    @Override
    public List<Solicitud> buscarSolicitudesAfinesA(Usuario usuario) {
        return null;
    }
}
