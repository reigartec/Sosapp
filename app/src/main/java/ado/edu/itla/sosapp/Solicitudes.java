package ado.edu.itla.sosapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Solicitudes extends AppCompatActivity {
    ListView list;
    String titles[] = {"Titulo uno", "Titulo dos"};
    String description[] = {"Descripcion uno","Descripcion dos"};
    String autor[] = {"Raymond D.","Benjamin P."};
    String estado[] = {"Pendiente","En proceso"};
    String fecha[] = {"14/12/18", "15/12/18"};
    int imgs[] = {R.drawable.ic_menu_gallery, R.drawable.ic_menu_share};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_solicitudes);

        /**************TRABAJANDO CON LISTAS********************/
        list = findViewById(R.id.missolicitudes_list);
        MyAdapter adapter = new MyAdapter(this,titles, imgs, description,autor, fecha, estado);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(Solicitudes.this,"Primera posicion 1",Toast.LENGTH_SHORT).show();
                }
                if(position==1){
                    Toast.makeText(Solicitudes.this,"Segunda posicion 2",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**************TRABAJANDO CON LISTAS********************/
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String myTitles[];
        String myDescriptions[];
        String myautor[];
        String myfecha[];
        String myestado[];
        int[] imgs;

        public MyAdapter(Context c, String[] titles, int[] imgs, String[] description, String[] autor, String fecha[], String estado[]){
            super(c,R.layout.solicitud_list_item,R.id.solicitud_item_descripcion,description);

            this.context=c;
            this.imgs=imgs;
            this.myTitles=titles;
            this.myDescriptions=description;
            this.myautor=autor;
            this.myfecha=fecha;
            this.myestado=estado;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = LayoutInflater.from(context).inflate(R.layout.solicitud_list_item,null);//layoutInflater.inflate(R.layout.solicitud_list_item, parent, false);
            //LayoutInflater.from(context).inflate(R.layout.solicitud_list_item,null
            ImageView images = row.findViewById(R.id.solicitud_item_logo);
            TextView myTitle = row.findViewById(R.id.solicitud_item_titulo);
            TextView myDescription = row.findViewById(R.id.solicitud_item_descripcion);
            TextView myautor = row.findViewById(R.id.solicitud_item_usuario);
            TextView myfecha = row.findViewById(R.id.solicitud_item_fecha);
            TextView myestado = row.findViewById(R.id.solicitud_item_estado);
            images.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            myDescription.setText(description[position]);
            myautor.setText(autor[position]);
            myfecha.setText(fecha[position]);
            myestado.setText(estado[position]);
            return super.getView(position, convertView, parent);
        }
    }
}
