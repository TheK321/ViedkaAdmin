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
            tableRow.addView(textView, nuevoTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void crearDatosTabla(){
        String info;
        for (fila=1;fila<=/*Cantidad de Filas*/100;fila++){
            nuevaFila();
            for (columna=0;columna<encabezado.length;columna++){
                nuevoDato();
                String[] filas = datos.get(fila-1);
                info = (columna<filas.length)?filas[columna]:"";
                textView.setText(info);
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

    //ANEXO DE DATOS
    public void agregarItem(String[]item){
        String info;
        datos.add(item);
        columna = 0;
        nuevaFila();
        while (columna<encabezado.length){
            nuevoDato();
            info = (columna<item.length)?item[columna]:"";
            textView.setText(info);
            tableRow.addView(textView,nuevoTableRowParams());
        }
        tableLayout.addView(tableRow,datos.size()-1);

    }

    //DISEÑO DE TABLA
    private TableRow getFila(int id){
        return (TableRow) tableLayout.getChildAt(id);
    }
    private TextView getDato(int idFila, int idCol){
        tableRow=getFila(idFila);
        return (TextView) tableRow.getChildAt(idCol);
    }
    public void fondoEncabezadoColor(int color){
        columna=0;
        while(columna<encabezado.length){
            nuevoDato();
            textView=getDato(0,columna++);
            textView.setBackgroundColor(color);
            textView.setBackgroundResource(R.drawable.style_ventas_h);
            textView.setTextColor(Color.WHITE);
        }
    }
    public void fondoCeldasColor(int primerColor, int segundoColor){
        for (fila=1;fila<=/*Cantidad de Filas*/100;fila++){
            alter=!alter;
            for (columna=0;columna<encabezado.length;columna++){
                textView=getDato(fila,columna);
                String[] filas = datos.get(fila-1);
                textView.setBackgroundResource((alter)?R.drawable.style_ventas_u:R.drawable.style_ventas_d);
            }
        }
        this.primerColor=primerColor;
        this.segundoColor=segundoColor;
    }
    public void filasNuevasColor(){
        columna=0;
        alter=!alter;
        while(columna<encabezado.length){
            nuevoDato();
            textView=getDato(datos.size()-1,columna++);
            textView.setBackgroundColor((alter)?primerColor:segundoColor);
        }
        tableLayout.addView(tableRow);
    }

}