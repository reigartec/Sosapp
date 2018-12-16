package ado.edu.itla.sosapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

//    private FrameLayout frameLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.toolbar_her);
        toolbar.setTitle("SosApp");

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.menuview);
        navigationView.setNavigationItemSelectedListener(this);

        seleccionarItem(R.id.action_inicio);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        seleccionarItem(menuItem.getItemId());
        return false;
    }

    public boolean seleccionarItem(int itemid)
    {
        Activity activity = null;
        Intent it = null;
        String titulo = "SosApp";

        if(itemid == R.id.action_inicio)
        {
            titulo = titulo + " - Inicio";
        }else if(itemid == R.id.action_missolicitudes)
        {
            titulo = titulo + " - Mis solicitudes";
        }else if(itemid == R.id.action_solicitudes)
        {
            titulo = titulo + " - Solicitudes";
            it = new Intent(InicioActivity.this, Solicitudes.class);
        }else if(itemid == R.id.action_nsolicitud)
        {
            titulo = titulo + " - Nueva Solicitud";
            it = new Intent(InicioActivity.this, NuevaSolicitud.class);
        }
        else if(itemid == R.id.action_settings)
        {
            titulo = titulo + " - Configuración";
        }else if(itemid == R.id.action_Cerrars)
        {
            //Cerrar sesión
        }

        if(it != null)
        {startActivity(it);}

        Toast.makeText(this, ""+titulo, Toast.LENGTH_SHORT).show();
        DrawerLayout drawerLayout =  findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;



    }
}
