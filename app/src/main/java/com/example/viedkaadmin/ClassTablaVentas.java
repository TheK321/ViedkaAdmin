package com.example.viedkaadmin;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ClassTablaVentas {
    private TableLayout tableLayout;
    private Context context;
    private TableRow tableRow;
    private TextView textView;
    private Boolean alter = false;
    int colorUno, colorDos;

    private String[] encabezado;
    private ArrayList<String[]> matrizDatos;

    private int fila, columna;

    public ClassTablaVentas(TableLayout tableLayout, Context context){
        this.tableLayout = tableLayout;
        this.context = context;
        nuevoRegistro();
    }

    public void definirEncabezado(String[] encabezado){
        this.encabezado = encabezado;
        crearEncabezado();
    }

    public void definirDatos(ArrayList<String[]> matrizDatos){
        this.matrizDatos = matrizDatos;
        crearDatosTabla();
    }

    public void nuevoRegistro(){
        tableRow = new TableRow(context);
    }

    private void nuevoDato(){
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
    }

    private void crearEncabezado(){
        columna = 0;
        nuevoRegistro();
        while (columna<encabezado.length){
            nuevoDato();
            textView.setText(encabezado[columna++]);
            tableRow.addView(textView, parametrosTablaVentas());
        }
        tableLayout.addView(tableRow);;
    }

    private void crearDatosTabla(){
        String registro;
        for (fila=1;fila<=10;fila++){
            nuevoRegistro();
            for (columna=0;columna<encabezado.length;columna++){
                nuevoDato();
                String[] contenidoNuevoRegistro = matrizDatos.get(fila-1);
                registro = (columna<contenidoNuevoRegistro.length)?contenidoNuevoRegistro[columna]:"";
                textView.setText(registro);
                tableRow.addView(textView, parametrosTablaVentas());
            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams parametrosTablaVentas(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }

    public void agregarInsercion(String[] insercion){
        String registro;
        matrizDatos.add(insercion);
        columna = 0;
        nuevoRegistro();
        while (columna<encabezado.length){
            nuevoDato();
            registro = (columna<insercion.length)?insercion[columna]:"NULL";
            textView.setText(registro);
            tableRow.addView(textView, parametrosTablaVentas());
        }
        tableLayout.addView(tableRow, matrizDatos.size()-1);
        //colorearNuevos();
    }

    //---------------------------- DISEÑO ---------------------------//

    //Obtener filas y columnas
    private TableRow obtenerFila(int numero){
        return (TableRow) tableLayout.getChildAt(numero);
    }

    private TextView obtenerCoordenada(int numeroFila, int numeroColumna){
        tableRow = obtenerFila(numeroFila);
        return (TextView) tableRow.getChildAt(numeroColumna);
    }

    //Diseño puro y duro

    public void encabezadoColor(int color){
        columna=0;
        while(columna<encabezado.length){
            nuevoDato();
            textView=obtenerCoordenada(0,columna++);
            textView.setBackgroundColor(color);
            textView.setBackgroundResource(R.drawable.style_ventas_h);
            textView.setTextColor(Color.WHITE);
        }
    }
    public void celdasColor(int colorUno, int colorDos){
        for (fila=1;fila<=10;fila++){
            alter=!alter;
            for (columna=0;columna<encabezado.length;columna++){
                textView=obtenerCoordenada(fila,columna);
                String[] filas = matrizDatos.get(fila-1);
                textView.setBackgroundResource((alter)?R.drawable.style_ventas_u:R.drawable.style_ventas_d);
            }
        }
        this.colorUno=colorUno;
        this.colorDos=colorDos;
    }
    public void colorearNuevos(){
        columna=0;
        alter=!alter;
        while(columna<encabezado.length){
            nuevoDato();
            textView=obtenerCoordenada(matrizDatos.size()-1,columna++);
            textView.setBackgroundColor((alter)?colorUno:colorDos);
        }
        tableLayout.addView(tableRow);
    }
}

