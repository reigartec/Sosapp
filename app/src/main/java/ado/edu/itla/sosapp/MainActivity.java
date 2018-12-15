package ado.edu.itla.sosapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.funciones.Sesion;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioimpl;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SOSAPP.MAINACTIVITY";//"Prueba";
    UsuarioRepositorio usuarioRepositorio;
    Sesion sesion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);//activity_main);

       // Log.d(TAG, "Entrando al Main Activity");
       // Log.e(TAG, "Esto es un error");
        sesion = new Sesion(getApplicationContext());
        String correo = sesion.get("email");
        if(!correo.equals(""))
        {
            Intent ventana = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(ventana);
        }
        TextView tView = (TextView) findViewById(R.id.login_Registrarse);

        tView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registraser = new Intent(MainActivity.this, Registrarse.class);
                startActivity(registraser);
            }
        });



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

/*        Usuario usuario,usuario1,usuario2, usuario3;
        usuario = new Usuario();
        usuario1 = new Usuario();
        usuario2 = new Usuario();
        usuario3 = new Usuario();*/
//        try {
//            usuario.setEdad(-12);
//        }catch (Exception e){
//            Toast.makeText(this, "Edad no permitida", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
/*        usuario.setEmail("juandelospalotes@gmail.com");
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
        }*/



    }

    public void login_click(View view)
    {

        final EditText emailText = findViewById(R.id.eUsuario);
        final EditText passwordText = findViewById(R.id.ePassword);
        String email = emailText.getText().toString().toLowerCase().trim();
        String password = passwordText.getText().toString();
        usuarioRepositorio = new UsuarioRepositorioimpl(this);

        /***************NUEVO METODO****************/
        Usuario u = null;
        String mensaje = "";
        String mail = "";
        String pass = "";
        u = usuarioRepositorio.buscar(email);

        mail = u==null?"":u.getEmail().toLowerCase();
        pass = u==null?"":u.getPassword().toLowerCase();
        //mensaje = email==u.getEmail().toLowerCase()?"Gracias por iniciar sesión "+email:"";
        if(mail.equals(email) && password.equals(pass))
        {
            mensaje = "Gracias por iniciar sesión "+email;
            sesion = new Sesion(getApplicationContext());
            sesion.set("email",email);
            Intent ventana = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(ventana);
        }else
            {
                mensaje = "Revisar datos de login!";
                emailText.setError("Revisar datos de login!");
            }
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        /***************NUEVO METODO****************/
/*****************METODO VIEJO DEVOLVIENDO UN STRING***************/
        /*String mensaje = usuarioRepositorio.logging(email,password);
        if(mensaje=="")
        {
            Toast.makeText(this,"Revisar datos de login!", Toast.LENGTH_SHORT).show();
            emailText.setError("Revisar datos de login!");
        }else
        {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }*/
        /*****************METODO VIEJO DEVOLVIENDO UN STRING***************/
    }
}
