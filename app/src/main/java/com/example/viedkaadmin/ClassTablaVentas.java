package com.example.viedkaadmin;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.navigationrail.NavigationRailView;

import java.util.ArrayList;

public class ClassTablaVentas {
    //INICIALIZACION DE LA INTERFAZ DE TABLA
    private NavigationRailView menuViedka;
    private TableLayout tableLayout;
    private Context context;
    private TableRow tableRow;
    private TextView textView;
    private boolean alter = false;
    int primerColor,segundoColor;

    private String[] encabezado;
    private ArrayList<String[]> datos;

    private int fila;
    private int columna;

    public ClassTablaVentas(TableLayout tl, Context c) {
        this.tableLayout = tl;
        this.context = c;
        nuevaFila();
    }
    public void agregarEncabezado(String[] encabezado){
        this.encabezado = encabezado;
        crearEncabezado();
    }
    public void agregarDatos(ArrayList<String[]> d){
        this.datos = d;
        crearDatosTabla();
    }
    private void nuevaFila(){
        tableRow = new TableRow(context);
    }

    private void nuevoDato(){
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
    }
    private void crearEncabezado(){
        columna=0;
        nuevaFila();
        while(columna<encabezado.length){
            nuevoDato();
            textView.setText(encabezado[columna++]);
            textView.setBackgroundColor(Color.parseColor("#FBA823"));
            textView.setTextColor(Color.WHITE);
            tableRow.addView(textView, nuevoTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void crearDatosTabla(){
        String info;
        for (fila=1;fila<=/*Cantidad de Filas*/100;fila++){
            nuevaFila();
            alter=!alter;
            for (columna=0;columna<encabezado.length;columna++){
                nuevoDato();
                String[] filas = datos.get(fila-1);
                info = (columna<filas.length)?filas[columna]:"";

                textView.setText(info);
                textView.setBackgroundResource(R.drawable.style_ventas_h);
                textView.setBackgroundColor(Color.parseColor((alter) ?"#FCC873":"#FDD087"));
                tableRow.addView(textView, nuevoTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    private TableRow.LayoutParams nuevoTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }

}