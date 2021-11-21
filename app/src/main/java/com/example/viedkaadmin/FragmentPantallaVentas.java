package com.example.viedkaadmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

public class FragmentPantallaVentas extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezado = {"ID","PRECIO","CANTIDAD","TOTAL"};
    private ArrayList<String[]> filas = new ArrayList<>();


    public FragmentPantallaVentas() {

    }

    // TODO: Rename and change types and number of parameters
    public static FragmentPantallaVentas newInstance(String param1, String param2) {
        FragmentPantallaVentas fragment = new FragmentPantallaVentas();

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
        View view = inflater.inflate(R.layout.fragment_pantalla_ventas, null, false);
        tableLayout = view.findViewById(R.id.tl);
        ClassTablaVentas tablaVentas = new ClassTablaVentas(tableLayout, getContext());
        tablaVentas.agregarEncabezado(encabezado);

        tablaVentas.agregarDatos(obtenerDatos());
        tablaVentas.fondoEncabezadoColor(Color.BLUE);
        tablaVentas.fondoCeldasColor(Color.GREEN, Color.YELLOW);


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
    //
}