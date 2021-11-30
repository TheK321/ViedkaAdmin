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
    private String[] encabezado = {"ID","ARTICULO","CANTIDAD VENDIDA","PRECIO","TOTAL"};
    private ArrayList<String[]> filas = new ArrayList<>();
    private String [] [] rawConsulta;
    private Movimiento [] listaVentas;


    public FragmentPantallaVentas() {

    }

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
        tableLayout = view.findViewById(R.id.tableLayoutVentas);
        ClassTablaVentas tablaVentas = new ClassTablaVentas(tableLayout, getContext());
        tablaVentas.agregarEncabezado(encabezado);

        tablaVentas.agregarDatos(obtenerDatos(),rawConsulta[1].length);
        return view;
    }

    //Metodos de Integracion Tabla Ventas DANIEL IFR
    private ArrayList<String[]>obtenerDatos(){
/*
        for(int con=0;con<100;con++){
            String temp = (String) ""+(con+1);
            filas.add(new String[]{temp,"CAMISA","4","150","600"});
        }*/
        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Movimientos",12,true,"Tipo=\"Ingreso\"");
            Movimiento[] listaMovimientos = new Movimiento[rawConsulta[1].length];
            for (int i = 0; i < rawConsulta[1].length; i++) {
                System.out.println(rawConsulta[0][i]+rawConsulta[1][i]);
                listaMovimientos[i] = new Movimiento(
                        rawConsulta[0][i],
                        rawConsulta[1][i],
                        rawConsulta[2][i],
                        rawConsulta[3][i],
                        rawConsulta[4][i],
                        rawConsulta[5][i],
                        rawConsulta[6][i],
                        rawConsulta[7][i],
                        rawConsulta[8][i],
                        rawConsulta[9][i],
                        rawConsulta[10][i],
                        rawConsulta[11][i]);
                filas.add(new String[]{listaMovimientos[i].getIdMovimiento(),listaMovimientos[i].getConcepto(),listaMovimientos[i].getCantidad(),listaMovimientos[i].getPrecioUni(),listaMovimientos[i].getTotal()});
                ;
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","",""};
    }
    //
}