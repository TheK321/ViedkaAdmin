package com.example.viedkaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
            fragmentTransaction.replace(R.id.frame_layout_container, new FragmentPantallaVentas());
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
                        fragment = new FragmentPantallaVentas();
                        cambiarTituloBarra("Ventas");
                        setContentView(R.layout.activity_main);
                        break;
                    case R.id.menu_agregarreporte:
                        fragment = new FragmentPantallaAgregarReporte();
                        cambiarTituloBarra("Agregar Reporte");
                        break;
                    case R.id.menu_librocontable:
                        fragment = new FragmentPantallaLibroContable();
                        cambiarTituloBarra("Libro Contable");
                        break;
                    case R.id.menu_productos:
                        fragment = new FragmentPantallaProductos();
                        cambiarTituloBarra("Productos");
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
}