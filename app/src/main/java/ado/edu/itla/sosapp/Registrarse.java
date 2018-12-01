package ado.edu.itla.sosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import java.util.regex.Pattern;

import ado.edu.itla.sosapp.entidad.Usuario;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioimpl;

public class Registrarse extends AppCompatActivity {

    private static String TAG = "REGISTRO_USUARIO";
    UsuarioRepositorio usuarioRepositorio;
    // desclaracion de variables================================
    String nom;
    int cont=0;
    public static String donde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        usuarioRepositorio = new UsuarioRepositorioimpl(this);

        final EditText emailText = findViewById(R.id.registrar_email);
        final EditText nombreText = findViewById(R.id.registrar_nombre);
        final EditText passwordText = findViewById(R.id.registrar_password);
        final EditText password2Text = findViewById(R.id.registrar_password2);

        Button btnRegistrarse = findViewById(R.id.registrarse_guardar);

        btnRegistrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

               //VALIDACIÓN
                //VALIDACION EMAIL
                if(emailText.getText().toString() == "")
                {
                    toasted("El campo email está vacío ó su longitud es menor a 4");
                    return;
                }
                if (!validarEmail(emailText.getText().toString())){
                    emailText.setError("Email no válido");
                    toasted("Email no válido");
                    return;
                }
                //Verificar en la base de datos si existe el correo.
                String mail = usuarioRepositorio.buscar(emailText.getText().toString().toLowerCase());
                if( mail.equals(emailText.getText().toString().toLowerCase()))
                {
                    emailText.setError("Este email ya fue registrado!");
                    toasted("Este email ya fue registrado!");
                    return;
                }
                //VALIDACION EMAIL
                if(nombreText.getText().toString() == "" || nombreText.getText().length() <= 3)
                {
                    toasted("El campo nombre está vacío ó su longitud es menor a 4");
                    nombreText.setError("Campo vacío ó longitud menor a 4");
                    return;
                }

                if(passwordText.getText().toString() == "")
                {
                    toasted("El campo password está vacío");
                    passwordText.setError("Campo vacío");
                    return;
                }

                if (!passwordText.getText().toString().equals(password2Text.getText().toString()))
                {
                    toasted("No coincide con la confirmación de passwords");
                    passwordText.setError("Password no coincide");
                    password2Text.setError("Password no coincide");
                    return;
                }

                //VALIDACIÓN
                Usuario usuario = new Usuario();
                usuario.setNombre(nombreText.getText().toString());
                usuario.setEmail(emailText.getText().toString());
                usuario.setPassword(passwordText.getText().toString());
//                usuario.setId();


                Log.i(TAG, usuario.toString());
                usuarioRepositorio.guardar(usuario);
                Log.i(TAG, usuario.toString());

                nombreText.setText("");
                emailText.setText("");
                passwordText.setText("");
                password2Text.setText("");
                Toast.makeText(Registrarse.this,"Usuario registrado correctamente!", Toast.LENGTH_SHORT).show();
                Cancelar_click(v);//Salimos del formulario, ya nuestro usuario está registrado...
                //podemos proceder a intentar login.
            }
        });

       //btnCancelar.setOnClickListener(new View.OnClickListener(){});

/*        public void loginabr{
            Intent loginser = new Intent(Registrarse.this, MainActivity.class);
            startActivity(loginser);
        }
        Button btnCancelar = findViewById(R.id.registrar_cancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View b){
                Intent loginser = new Intent(Registrarse.this, MainActivity.class);
                startActivity(loginser);
            }

        });*/



    }

    public void Cancelar_click(View view){
        // iniciamos la segunda actividad donde se encuentran los reportes.
        nom="Saliendo del registro...";//Asigno a la variable nom el nombre de esa pantalla a presentar en el toasted
        //Cuadro negro.
        donde="Registro";//Asigno a la variable donde, el lugar a donde es que va para ser identificado.
        Intent i = new Intent(Registrarse.this,MainActivity.class);//Asigno el intent la clase que debe abrir
        toasted(nom);// aqui lanzamos el menssage cuando pulsamos un boton
        startActivity(i);//Inicio la actividad
    }

    public void toasted(String nom){
        //Es utilizada para presentar el mensaje en forma rectangulo negro antes de abrir cada ventana
        //Aquí lanzamos el menssage cuando pulsamos un tab
        Context context = getApplicationContext();
        CharSequence text = "Mensaje:... \n  "+nom;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
