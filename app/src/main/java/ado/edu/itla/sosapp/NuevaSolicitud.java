package ado.edu.itla.sosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import ado.edu.itla.sosapp.entidad.AreaAfin;
import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.areas.AreaRepositorioimpl;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioimpl;

public class NuevaSolicitud extends AppCompatActivity  {
    final EditText titulo = findViewById(R.id.nstitulo);
    final EditText descripcion = findViewById(R.id.nsdescripcion);
    final Spinner area = findViewById(R.id.nssarea);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_solicitud);

        AreaRepositorioimpl aimpl = new AreaRepositorioimpl(this);
        ArrayList<AreaAfin> areas = aimpl.buscarTodos();

        area.setAdapter(new ArrayAdapter<AreaAfin>(this,R.layout.support_simple_spinner_dropdown_item,areas));

    }

    public void cancelarClick(View view)
    {
        Intent i = new Intent(this,InicioActivity.class);
        startActivity(i);//Salimos del formulario
    }

    public void guardarClick(View view)
    {
            //VALIDACION
        if(titulo.getText().length() < 3)
        {
            Toast.makeText(getApplicationContext(),"Titulo no admitido!",Toast.LENGTH_SHORT);
            titulo.setError("El campo email está vacío ó su longitud es menor a 4");
            return;
        }
        if(descripcion.getText().length() < 7)
        {
            Toast.makeText(getApplicationContext(),"Descripción no admitida!",Toast.LENGTH_SHORT);
            titulo.setError("El campo descripción está vacío ó su longitud es menor a 4");
            return;
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(descripcion.getText().toString());
        solicitud.setEstado(Solicitud.Estado.Pendiente);
        AreaAfin afin = (AreaAfin) area.getSelectedItem();
        solicitud.setAreaAfin(afin);
        solicitud.setFecha(new Date());
        Usuario usuario =  new UsuarioRepositorioimpl(getApplicationContext()).buscar("reinaldo788@gmail.com");
        solicitud.setUsuarioSolicitante(usuario);
        solicitud.setTitulo(titulo.getText().toString());

        Toast.makeText(getApplicationContext(),"Solicitud registrada correctamente!", Toast.LENGTH_SHORT).show();
        cancelarClick(view);

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
