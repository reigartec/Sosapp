package ado.edu.itla.sosapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.sosapp.entidad.Usuario;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Prueba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Entrando al Main Activity");
        Log.e(TAG, "Esto es un error");

        /*Button btnBotton = (Button) findViewById(R.id.button);

        btnBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                try {
                    usuario.setEdad(-12);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Edad no permitida", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });*/

        Usuario usuario,usuario1,usuario2, usuario3;
        usuario = new Usuario();
        usuario1 = new Usuario();
        usuario2 = new Usuario();
        usuario3 = new Usuario();
//        try {
//            usuario.setEdad(-12);
//        }catch (Exception e){
//            Toast.makeText(this, "Edad no permitida", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
        usuario.setEmail("juandelospalotes@gmail.com");
        usuario.setNombre("Juan de los palotes");
        usuario.setUsername("juandelospalotes");

        usuario1.setEmail("usuario1@gmail.com");
        usuario1.setNombre("usuario1 de los palotes");
        usuario1.setUsername("usuario1");

        usuario2.setEmail("usuario2@gmail.com");
        usuario2.setNombre("usuario2 de los palotes");
        usuario2.setUsername("usuario2");

        usuario3.setEmail("usuario3@gmail.com");
        usuario3.setNombre("usuario3 de los palotes");
        usuario3.setUsername("usuario3");

        List<Usuario> usuarioList = new ArrayList();
        usuarioList.add(usuario);
        usuarioList.add(usuario1);
        usuarioList.add(usuario2);
        usuarioList.add(usuario3);

        Log.i(TAG,"Tamaño de la lista "+usuarioList.size());
        for ( Usuario u: usuarioList ){
            Log.i(TAG, "Nombre: " + u.getNombre());
        }



    }
}
