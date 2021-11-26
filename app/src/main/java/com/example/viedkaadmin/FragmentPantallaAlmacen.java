package com.example.viedkaadmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaAlmacen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaAlmacen extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezado = {"ID\n","Prenda\n","Tipo\n","Disponibles\n","Vendidos\n","Precio\nUnidad","Precio\nTotal"};
    private ArrayList<String[]> filas = new ArrayList<>();
    private String[] prendas = {"Camisa","Blusa","Sueter","Bufanda","Gorro","Chamarra","Hoddie","Sudaderas","Pantalón Mezclilla","Pantalón Vestir","Pants","Mallas","Falda","Vestido","Pantolón Legging","Short","Top","Camiseta","Licra","Calcetas","Calcetines","Medias"};
    private String[] tipo = {"Adulto","Adulto","Niño","Adulto","Niño","Niño","Adulto","Niño","Adulto","Adulto","Niño","Niño","Adulto","Adulto","Adulto","Niño","Adulto","Adulto","Adulto","Niño","Niño","Adulto"};
    private String[] avalible = {"50","35","20","30","35","20","15","20","25","25","30","20","15","10","20","15","20","50","35","25","30","20"};
    private String[] sold = {"15","16","10","15","5","8","10","12","10","10","6","7","3","5","15","6","2","11","3","11","11","9"};
    private String[] unidad = {"50","34","40","25","30","60","75","25","45","35","40","25","30","130","80","45","35","30","60","30","25","25"};
    private String[] total = {"$500","$175","$600","$525","$450"};
    private int totaldis=0;
    private TextView txtvav;


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
        txtvav = view.findViewById(R.id.textView_totalesav);
        txtvav.setText(String.valueOf(totaldis));
        //tablaAlmacen.fondoEncabezadoColor(Color.WHITE);
        //tablaAlmacen.fondoCeldasColor(Color.GREEN, Color.YELLOW);

        return view;
    }

    private ArrayList<String[]>obtenerDatos(){

        for(int con=0;con<22;con++){
            String temp = (String) ""+(con+1);
            int total=Integer.parseInt(sold[con])*Integer.parseInt(unidad[con]);
            filas.add(new String[]{temp,prendas[con],tipo[con],avalible[con],sold[con],"$"+unidad[con],"$"+String.valueOf(total)});
            totaldis+=Integer.parseInt(avalible[con]);
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","","","","",""};
    }
    //
}

