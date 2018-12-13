package ado.edu.itla.sosapp.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbconexion extends SQLiteOpenHelper {

    private static String NOMBRE_BASEDATOS = "sosapp.db";
    private static int VERSION_BASEDATOS = 1;

    private static  String TABLA_USUARIO = "CREATE TABLE `usuario` (" +
            "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`nombre` TEXT NOT NULL, " +
            "`email` TEXT NOT NULL," +
            "`password` TEXT NOT NULL )";

    private static String TABLA_SOLICITUD = "CREATE TABLE `solicitud` (" +
            "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`fecha` INTEGER, " +
            "`descripcion` TEXT, " +
            "`titulo` TEXT, " +
            "`estado` TEXT, " +
            "`areaafin` INTEGER," +
            "`usuario_solicitante_id` INTEGER," +
            "`usuario_asignado_id` INTEGER)";

    private static String TABLA_AREA = "CREATE TABLE `catalogo_area` (" +
            "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`area` TEXT," +
            "`descripcion` TEXT)";

    public Dbconexion(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLA_USUARIO);
            db.execSQL(TABLA_SOLICITUD);
            db.execSQL(TABLA_AREA);
            db.execSQL("INSERT INTO catalogo_area(area,descripcion)"+
                    "values('Comunicación',''),('Tecnología',''),('Electrónica','')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
