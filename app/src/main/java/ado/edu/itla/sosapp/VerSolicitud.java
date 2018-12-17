package ado.edu.itla.sosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ado.edu.itla.sosapp.entidad.Solicitud;
import ado.edu.itla.sosapp.repositorio.Dbconexion;
import ado.edu.itla.sosapp.repositorio.funciones.Sesion;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorioimpl;

public class VerSolicitud extends AppCompatActivity {
    Sesion sesion = null;
    Solicitud solicitud=null;
    String correo="";
    private Dbconexion dbConexion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_solicitud);
        String idSolicitud="";
        dbConexion = new Dbconexion(getApplicationContext());
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
        correo = sesion.get("email");
        int id =  Integer.parseInt(idSolicitud);
        solicitud = new SolicitudRepositorioimpl(getApplicationContext()).buscarPor(id);
        final Button bcancelar = findViewById(R.id.solicitud_boton);
        Button boton2 = findViewById(R.id.solicitud_boton2);

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

        if(solicitud.getUsuarioSolicitante().getEmail().toLowerCase().equals(correo.toLowerCase()))
        {
            ttc.setText("Trabajando por:");
            boton2.setVisibility(View.INVISIBLE);
            //bcancelar.setText("");

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

        }else
            {
                bcancelar.setText("Seleccionar");
                boton2.setVisibility(View.INVISIBLE);
                ttc.setText("Creado por:");
                qtra.setText(solicitud.getUsuarioSolicitante().getNombre());
            }


            bcancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),
                      //      "Afuera - "+solicitud.getUsuarioSolicitante().getEmail()+
                        //    " - correo - "+correo,Toast.LENGTH_SHORT).show();
                    if(solicitud.getUsuarioSolicitante().getEmail().toLowerCase().equals(correo.toLowerCase()))
                    {
                        AlertDialog cajaopcion = pregunta(solicitud.getId());
                        cajaopcion.show();//Opcion para borrar la solicitud o no.
                        //Toast.makeText(getApplicationContext(),
                          //      "Solicitud prueba click cancelar ",Toast.LENGTH_SHORT).show();

                    }else
                    {
                            if(bcancelar.getText().toString().equals("Seleccionar"))
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Probando, si funciona",Toast.LENGTH_SHORT).show();
                            }
                    }
                }
            });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    private AlertDialog pregunta(final int id)
    {
        AlertDialog mDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Borrar Solicitud")
                .setMessage("Seguro que quieres borrar tu solicitud?")
                .setIcon(R.drawable.ic_menu_slideshow)

                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Codigo para borrar
                        SQLiteDatabase db = dbConexion.getWritableDatabase();

                        db.delete("solicitud","id=?",new String[]{String.valueOf(id)});

                        Log.e("TAG_BORRAR","Borrando la solicitud");

                        db.close(); // Closing database connection
                        //solicitud.
                        dialog.dismiss();
                        //onBackPressed();
                        Intent i = new Intent(VerSolicitud.this, InicioActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),
                              "Solicitud borrada",Toast.LENGTH_SHORT).show();
                    }

                })



                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return mDialogBox;

    }

}
