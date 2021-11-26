package com.example.viedkaadmin;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassTablaProductos {
    //INICIALIZACION DE LA INTERFAZ DE TABLA
    private TableLayout tableLayout;
    private Context context;
    private TableRow tableRow;
    private TextView textView;
    private boolean alter = false;
    int primerColor,segundoColor;

    private String[] encabezadoProductos;
    private ArrayList<String[]> datos;

    private int filaProductos;
    private int columna;



    public ClassTablaProductos(TableLayout tProductos, Context c) {
        this.tableLayout = tProductos;
        this.context = c;
        nuevaFila();
    }
    public void agregarEncabezado(String[] encabezadoProductos){
        this.encabezadoProductos = encabezadoProductos;
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
        while(columna<encabezadoProductos.length){
            nuevoDato();
            textView.setText(encabezadoProductos[columna++]);
            tableRow.addView(textView, nuevoTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void crearDatosTabla(){
        String info;
        for (filaProductos=1;filaProductos<=/*Cantidad de Filas*/10;filaProductos++){
            nuevaFila();
            for (columna=0;columna<encabezadoProductos.length;columna++){
                nuevoDato();
                String[] filas = datos.get(filaProductos-1);
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
        while (columna<encabezadoProductos.length){
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
        while(columna<encabezadoProductos.length){
            nuevoDato();
            textView=getDato(0,columna++);
            textView.setBackgroundColor(color);
            textView.setBackgroundResource(R.drawable.style_ventas_h);
            textView.setTextColor(Color.WHITE);
        }
    }
    public void fondoCeldasColor(int primerColor, int segundoColor){
        for (filaProductos=1;filaProductos<=/*Cantidad de Filas*/10;filaProductos++){
            alter=!alter;
            for (columna=0;columna<encabezadoProductos.length;columna++){
                textView=getDato(filaProductos,columna);
                String[] filas = datos.get(filaProductos-1);
                //textView.setBackgroundResource((alter)?R.drawable.style_ventas_u:R.drawable.style_ventas_d);
            }
        }
        this.primerColor=primerColor;
        this.segundoColor=segundoColor;
    }
    public void filasNuevasColor(){
        columna=0;
        alter=!alter;
        while(columna<encabezadoProductos.length){
            nuevoDato();
            textView=getDato(datos.size()-1,columna++);
            textView.setBackgroundColor((alter)?primerColor:segundoColor);
        }
        tableLayout.addView(tableRow);
    }

}
