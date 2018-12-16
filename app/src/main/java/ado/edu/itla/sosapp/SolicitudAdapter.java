package ado.edu.itla.sosapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ado.edu.itla.sosapp.entidad.Solicitud;

public class SolicitudAdapter extends BaseAdapter {

    private SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/YYYY");
    private Context context;
    private List<Solicitud> solicitudes;

    public SolicitudAdapter(Context context, List<Solicitud> solicitudes){
        this.context = context;
        this.solicitudes = solicitudes;
    }


    @Override
    public int getCount() {
        return solicitudes.size();
    }

    @Override
    public Object getItem(int position) {
        return solicitudes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View solicitudItem, ViewGroup parent) {

        if(solicitudItem == null) {
            solicitudItem = LayoutInflater.from(context).inflate(R.layout.solicitud_list_item,null);
        }

        ImageView images = solicitudItem.findViewById(R.id.solicitud_item_logo);
        TextView myTitle = solicitudItem.findViewById(R.id.solicitud_item_titulo);
        TextView myDescription = solicitudItem.findViewById(R.id.solicitud_item_descripcion);
        TextView myautor = solicitudItem.findViewById(R.id.solicitud_item_usuario);
        TextView myfecha = solicitudItem.findViewById(R.id.solicitud_item_fecha);
        TextView myestado = solicitudItem.findViewById(R.id.solicitud_item_estado);
        //images.setImageResource(imgs[position]);

        /*
        TextView txtTitulo = null;//solicitudItem.findViewById(R.id.solicitud_item_titulo);
        TextView txtUsuario = null;//solicitudItem.findViewById(R.id.solicitud_usuario_solicitante);
        TextView txtFecha = null;//solicitudItem.findViewById(R.id.solicitud_item_fecha);
*/
        Solicitud solicitud = solicitudes.get(position);

        /*
        txtTitulo.setText(solicitud.getTitulo());//solicitud.getTitulo);
        txtFecha.setText(SDF.format(solicitud.getFecha()));//SDF.format(solicitud.getFecha())
        txtUsuario.setText(solicitud.getUsuarioSolicitante().getNombre());//solicitud.getUsuarioSolicitante().getNombre()
*/
        myTitle.setText(solicitud.getTitulo());
        myDescription.setText(solicitud.getDescripcion());
        myautor.setText(solicitud.getUsuarioSolicitante().getNombre());
        myfecha.setText(SDF.format(solicitud.getFecha()));
        myestado.setText(solicitud.getEstado().toString());
        return solicitudItem;
    }
}
