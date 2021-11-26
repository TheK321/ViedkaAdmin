package com.example.viedkaadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;

import java.util.ArrayList;

public class FragmentPantallaProductos extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezadoProductos = {"Articulo","Cantidad","Edad","Precio Unitario"};
    private ArrayList<String[]> filas = new ArrayList<>();




    public FragmentPantallaProductos() {

    }

    // TODO: Rename and change types and number of parameters
    public static FragmentPantallaProductos newInstance(String param1, String param2) {
        FragmentPantallaProductos fragment = new FragmentPantallaProductos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_productos, null, false);
        tableLayout = view.findViewById(R.id.tProductos);
        ClassTablaProductos tablaProductos = new ClassTablaProductos(tableLayout, getContext());
        tablaProductos.agregarEncabezado(encabezadoProductos);

        ImageButton imageButton = view.findViewById(R.id.editarBoton);


        tablaProductos.agregarDatos(obtenerDatos());



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container,
                        new FragmentEditarProductos()).commit();
            }
        });

        return view;
    }
    //Metodos de Integracion Tabla Ventas DANIEL IFR
    private ArrayList<String[]>obtenerDatos(){

        for(int con=0;con<10;con++){
            String temp = (String) ""+(con+1);
            filas.add(new String[]{temp,"CAMISA","100","4","400"});
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","","",""};
    }

}