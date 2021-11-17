package com.example.viedkaadmin;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Transition;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Variables de creacion de Tabla Ventas DANIEL IFR
    private TableLayout tableLayout;
    private String[] encabezado = {"ID","ARTICULO","CANTIDAD VENDIDA","PRECIO","TOTAL"};
    private ArrayList<String[]> filas = new ArrayList<>();
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
            fragmentTransaction.replace(R.id.frame_layout_container, new FragmentPantallaLibroContable());
            fragmentTransaction.commit();
        }

        menuViedka.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_resumen:
                        //fragment = new fragmentPantallaResumen;
                        cambiarTituloBarra("Resumen");
                        break;
                    case R.id.menu_ventas:
                        //Definicion de Tabla Ventas
                        tableLayout = (TableLayout)findViewById(R.id.TableLayoutVentas);
                        //
                        //Parte de integracion de tabla Ventas
                        ClassTablaVentas tablaVentas = new ClassTablaVentas(tableLayout,getApplicationContext());
                        tablaVentas.definirEncabezado(encabezado);
                        tablaVentas.definirDatos(obtenerDatos());
                        //
                        fragment = new FragmentPantallaVentas();
                        cambiarTituloBarra("Ventas");
                        break;
                    case R.id.menu_agregarreporte:
                        fragment = new FragmentPantallaAgregarReporte();
                        cambiarTituloBarra("Agregar Reporte");
                        break;
                    case R.id.menu_librocontable:
                        fragment = new FragmentPantallaLibroContable();
                        cambiarTituloBarra("Libro Contable");
                        break;
                }
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout_container, fragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    public void cambiarTituloBarra(String nuevoTitulo){
        getSupportActionBar().setTitle(nuevoTitulo);
    }

    //Metodos de Integracion Tabla Ventas DANIEL IFR
    private ArrayList<String[]>obtenerDatos(){

        for(int con=0;con<10;con++){
            String temp = (String) ""+(con+1);
            filas.add(new String[]{temp,"CAMISA","2","100","200"});
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","","",""};
    }
    //
}