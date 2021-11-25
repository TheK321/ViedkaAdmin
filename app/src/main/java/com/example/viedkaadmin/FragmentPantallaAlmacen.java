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
 * Use the {@link FragmentPantallaAlmacen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaAlmacen extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezado = {"ID","PRENDA","TIPO","DISPONIBLES","PRECIO X UNIDAD"};
    private ArrayList<String[]> filas = new ArrayList<>();


    public FragmentPantallaAlmacen() {

    }

    public static FragmentPantallaAlmacen newInstance(String param1, String param2) {
        FragmentPantallaAlmacen fragment = new FragmentPantallaAlmacen();

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
        View view = inflater.inflate(R.layout.fragment_pantalla_almacen, null, false);
        tableLayout = view.findViewById(R.id.tableLayoutAlmacen);
        ClassTablaAlmacen tablaAlmacen = new ClassTablaAlmacen(tableLayout, getContext());
        tablaAlmacen.agregarEncabezado(encabezado);

        tablaAlmacen.agregarDatos(obtenerDatos());
        //tablaAlmacen.fondoEncabezadoColor(Color.WHITE);
        //tablaAlmacen.fondoCeldasColor(Color.GREEN, Color.YELLOW);

        return view;
    }

    private ArrayList<String[]>obtenerDatos(){

        for(int con=0;con<100;con++){
            String temp = (String) ""+(con+1);
            filas.add(new String[]{temp,"PANTALÓN","NIÑO","30","100"});
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","","",""};
    }
    //
}

