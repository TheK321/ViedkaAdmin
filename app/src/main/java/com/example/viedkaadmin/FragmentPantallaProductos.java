package com.example.viedkaadmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaProductos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaProductos extends Fragment {
    private TableLayout tableLayout;
    private String[] encabezadoProductos = {"Articulo","Cantidad","Edad","Precio Unitario"};
    private ArrayList<String[]> filas = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters


    public FragmentPantallaProductos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPantallaProductos.
     */
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

        tablaProductos.agregarDatos(obtenerDatos());
        tablaProductos.fondoEncabezadoColor(Color.BLUE);
        tablaProductos.fondoCeldasColor(Color.GREEN, Color.YELLOW);


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