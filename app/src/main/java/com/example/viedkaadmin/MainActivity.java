package com.example.viedkaadmin;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Transition;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Variables de creacion de Tabla Ventas DANIEL IFR
    //private TableLayout tableLayout;
    //private String[] encabezado = {"ID","PRECIO","CANTIDAD","TOTAL"};
    //private ArrayList<String[]> filas = new ArrayList<>();
    //
    //

    NavigationRailView menuViedka;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuViedka=findViewById(R.id.id_menuviedka);
        if(fragment==null){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, new FragmentPantallaResumen());
            fragmentTransaction.commit();
        }
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "ViedkaBD"/*Nombre final de la BD*/, null,8);
        //Abrir la BD en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        BaseDeDatos.close();

        menuViedka.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_resumen:
                        fragment = new FragmentPantallaResumen();
                        //cambiarTituloBarra("Resumen");
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"resumen").addToBackStack("resumen");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_ventas:
                        fragment = new FragmentPantallaVentas();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"ventas").addToBackStack("ventas");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_trabajadores:
                        fragment = new FragmentTrabajadores();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"trabajadores").addToBackStack("trabajadores");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_agregarreporte:
                        fragment = new FragmentPantallaAgregarReporte();
                        //cambiarTituloBarra("Agregar Reporte");
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"agregarreporte").addToBackStack("agregarreporte");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_librocontable:
                        fragment = new FragmentPantallaLibroContable();
                        //cambiarTituloBarra("Libro Contable");
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"librocontable").addToBackStack("librocontable");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_productos:
                        fragment = new FragmentPantallaProductos();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"productos").addToBackStack("productos");
                        fragmentTransaction.commit();
                        break;
                    case R.id.menu_almacen:
                        fragment = new FragmentPantallaAlmacen();
                        //cambiarTituloBarra("Almacén");
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout_container, fragment,"almacen").addToBackStack("almacen");
                        fragmentTransaction.commit();
                        break;
                }

                return true;
            }
        });
    }

    public long Insertar(String [] columnas, String[] valores, String tabla)  {
        //Abrir la BD en modo lectura-escritura

            SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();

            //Agregar un registro a la Base de Datos
            ContentValues insercion = new ContentValues();
            //Referenciar los valores locales de las columnas con los valores reales de la tabla de la BD
        for(int i = 0; i<columnas.length;i++){
            insercion.put(columnas[i], valores[i]);
        }
            //Guardar valores dentro de la Base de Datos
            long insert = BaseDeDatos.insertOrThrow(tabla, null, insercion);
        //Cerrar base de datos despues de la transaccion
            BaseDeDatos.close();

            //Mensaje de registro Exitoso
            return insert;
    }

    public String [][] Consultar(String tabla, int numCampos,boolean tienewhere, String where){
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getReadableDatabase();
        String sql=tienewhere? "select * from "+tabla+" where "+where:"select * from "+tabla;
            //Aplicar un select a la Base de Datos
            Cursor fila = BaseDeDatos.rawQuery
                    (sql, null);
            //Metodo para verificar si existe o no el elemento en la tabla
        String[][] datos = new String[numCampos][fila.getCount()];
            if(fila.moveToFirst()){
                int tamanio=0;
                do{
                    for(int i = 0; i<numCampos;i++) {
                        datos[i][tamanio] = fila.getString(i);
                    }
                    tamanio++;
                } while (fila.moveToNext());
                fila.close();
                //Cerrar Base de Datos
                BaseDeDatos.close();
            }
        return datos;
    }

    public String [] ConsultarUltimo(String tabla, int numCampos,boolean tienewhere, String where, String llave){
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getReadableDatabase();
        String sql=tienewhere? "select * from "+tabla+" where "+where+" ORDER BY "+llave+" DESC LIMIT 1 ":"select * from "+tabla+" ORDER BY "+llave+" DESC LIMIT 1 ";
        //Aplicar un select a la Base de Datos
        Cursor fila = BaseDeDatos.rawQuery
                (sql, null);
        //Metodo para verificar si existe o no el elemento en la tabla
        String[] datos = new String[numCampos];
        if(fila.moveToFirst()){
                for(int i = 0; i<numCampos;i++) {
                    datos[i] = fila.getString(i);
                }
            fila.close();
            //Cerrar Base de Datos
            BaseDeDatos.close();
        } else {
            for(int i = 0; i<numCampos;i++) {
                datos[i] = "0";
            }
        }
        return datos;
    }

    public String [] ConsultarSuma(String tabla, String campoasumar, boolean tienewhere, String where, String collave){
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getReadableDatabase();
        String sql=tienewhere? "select SUM(\""+campoasumar+"\") from "+tabla+" where "+where+" GROUP BY "+collave:"select SUM(\""+campoasumar+"\") from "+tabla+" GROUP BY "+collave ;
        System.out.println("consulta a ejecutar es "+sql);
        //Aplicar un select a la Base de Datos
        Cursor fila = BaseDeDatos.rawQuery
                (sql, null);
        //Metodo para verificar si existe o no el elemento en la tabla
        String[] datos = new String[1];
        if(fila.moveToFirst()){
            datos[0] = fila.getString(0);
            fila.close();
            //Cerrar Base de Datos
            BaseDeDatos.close();
        } else {
        datos[0] = "0";
        }
        return datos;
    }

    public long Eliminar(String tabla, String id ,String valor){
        //Abrir la BD en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();

        long delete = BaseDeDatos.delete(tabla, id+"="+valor, null);
        return  delete;
    }

    public int Actualizar(String [] columnas, String[] valores, String tabla, String collave, String valorllave){
        //Abrir la BD en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();

        ContentValues actualizacion = new ContentValues();
        //Referenciar los valores locales de las columnas con los valores reales de la tabla de la BD
        for(int i = 0; i<columnas.length;i++){
            actualizacion.put(columnas[i], valores[i]);
        }

        int actualizado = BaseDeDatos.update(tabla, actualizacion, collave+"="+valorllave, null);
        return actualizado;
    }

    public int Contar(String tabla){
        int conteo = 0;
        SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select count(*) from "+tabla,null);
        if(fila.moveToFirst()){
            for(int c=0 ; c < 1 ; c++){
                conteo = Integer.parseInt(fila.getString(0));
            }
        }
        return conteo;
    }


    public void cambiarTituloBarra(String nuevoTitulo){
        getSupportActionBar().setTitle(nuevoTitulo);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Confirmar")
                    .setMessage("¿Está segura de querer salir de la aplicación??")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }


}