package ado.edu.itla.sosapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.repositorio.funciones.Sesion;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorioimpl;

public class VerSolicitud extends AppCompatActivity {
    Sesion sesion = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_solicitud);
        String idSolicitud="";
        if (savedInstanceState == null) {
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            idSolicitud = "";
        } else {
            idSolicitud= extras.getString("idSolicitud");
        }
    } else {
        idSolicitud= (String) savedInstanceState.getSerializable("idSolicitud");
    }
        Toast.makeText(VerSolicitud.this,
                "Solicitud "+idSolicitud+
                        ", Lista para ser visualizada ",Toast.LENGTH_SHORT).show();
        sesion = new Sesion(getApplicationContext());
        String correo = sesion.get("email");
        int id =  Integer.parseInt(idSolicitud);
        Solicitud solicitud = new SolicitudRepositorioimpl(getApplicationContext()).buscarPor(id);

        TextView titulo = findViewById(R.id.solicitud_lstitulo);
        titulo.setText(solicitud.getTitulo());
        TextView descripcion = findViewById(R.id.solicitud_lsdescripcion);
        descripcion.setText(solicitud.getDescripcion());
        TextView area = findViewById(R.id.solicitud_lsareaafin);
        area.setText(solicitud.getAreaAfin().getNombre());
        TextView estado = findViewById(R.id.solicitud_lsestado);
        estado.setText(solicitud.getEstado().toString());

        TextView ttc = findViewById(R.id.labelcreatra);
        TextView qtra = findViewById(R.id.solicitud_lsusuario);

        if(solicitud.getUsuarioSolicitante().getEmail().equals(correo))
        {
            ttc.setText("Trabajando por:");
            
        }else
            {
                ttc.setText("Creado por:");
            }
            try {

                if (solicitud.getUsuarioAsignado().getId() != 0) {
                    qtra.setText(solicitud.getUsuarioAsignado().getNombre());
                } else {
                    qtra.setText("Nadie interesado");
                }
            }catch (Exception e)
            {
                qtra.setText("Nadie interesado ");
                Log.i("Error",e.getMessage());
            }


    }

}
