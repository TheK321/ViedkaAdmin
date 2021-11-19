package com.example.viedkaadmin;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaAgregarReporte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaAgregarReporte extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TableLayout tableLayout;
    private Context context;
    private String[] header = {"Id", "Nombre","Apellido"};
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multiColor = false;
    int firtColor, secondColor, textColor, colorLinea;
    TableLayout tabla;
    EditText nombre, apellido;

    ArrayList<String[]> filas=new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPantallaAgregarReporte() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPantallaAgregarReporte.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPantallaAgregarReporte newInstance(String param1, String param2) {
        FragmentPantallaAgregarReporte fragment = new FragmentPantallaAgregarReporte();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantalla_agregar_reporte, container, false);
        TableLayout ll = (TableLayout) view.findViewById(R.id.tablaOperacionesReporte);
        TableRow tr;
        TextView tv1;
        for (int i=0;i<=20;i++){
            tr= new TableRow(getActivity());
            tv1 = new TextView(getActivity());
            tv1.setText("TEST NUMBER");
            tv1.setTextColor(Color.BLACK);
            tv1.setTextSize(20);
            tv1.setPadding(5, 5, 5, 5);
            tr.addView(tv1);
            ll.addView(tr);
        }


        return view;
    }

    public void insertarRegistro(View view) {
        String[] nuevo_registro = {"5", nombre.getText().toString(), apellido.getText().toString()};
        addItems(nuevo_registro);
    }

    private ArrayList<String[]> getClientes(){
        filas.add(new String[]{"1", "Pedro", "Lopez"});
        filas.add(new String[]{"2", "Juan", "Villa"});
        filas.add(new String[]{"3", "Aldo", "Torres"});
        filas.add(new String[]{"4", "Fiorella", "Canto"});
        return filas;
    }

    public void addHeader(String[] header) {
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]> data) {
        this.data = data;
        createDataTable();
    }

    private void newRow() {
        tableRow = new TableRow(context);
    }

    private void newCell() {
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }

    private void createHeader() {
        indexC = 0;
        newRow();
        while (indexC < header.length) {
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable() {
        String info;
        for (indexR = 1; indexR <= data.size(); indexR++) {
            newRow();
            for (indexC = 0; indexC < header.length; indexC++) {
                newCell();
                String[] row = data.get(indexR - 1);
                info = (indexC < row.length) ? row[indexC] : "";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    public void addItems(String[] item) {
        String info;
        data.add(item);
        indexC = 0;
        newRow();
        while (indexC < header.length) {
            newCell();
            info = (indexC < item.length) ? item[indexC++] : "";
            txtCell.setText(info);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow, data.size());//Se quito el -1 despues de size para corregir
        reColoring();
        reColoringLinea();
    }

    public void backgroundHeader(int color) {
        indexC = 0;
        newRow();
        while (indexC < header.length) {
            txtCell = getCell(0, indexC++);
            txtCell.setBackgroundColor(color);
        }
    }

    public void backgroundData(int firtColor, int secondColor) {
        for (indexR = 1; indexR <= data.size(); indexR++) {
            multiColor = !multiColor;
            for (indexC = 0; indexC < header.length; indexC++) {
                txtCell = getCell(indexR, indexC);
                txtCell.setBackgroundColor((multiColor) ? firtColor : secondColor);
            }
        }
        this.firtColor = firtColor;
        this.secondColor = secondColor;
    }

    public void lineColor(int color) {
        indexR = 0;
        while (indexR <= data.size()) {
            getRow(indexR++).setBackgroundColor(color);
        }
        this.colorLinea = color;
    }

    public void textColorData(int color) {
        for (indexR = 1; indexR <= data.size(); indexR++) {
            for (indexC = 0; indexC < header.length; indexC++) {
                getCell(indexR, indexC).setTextColor(color);
            }
        }
        this.textColor = color;
    }

    public void textColorHeader(int color) {
        indexC = 0;
        while (indexC < header.length) {
            getCell(0, indexC++).setTextColor(color);
        }
    }

    public void reColoring() {
        indexC = 0;
        multiColor=!multiColor;
        while (indexC < header.length) {
            txtCell = getCell(data.size(), indexC++);
            txtCell.setBackgroundColor((multiColor) ? firtColor : secondColor);
            txtCell.setTextColor(textColor);
        }
    }

    public void reColoringLinea(){
        indexR = 0;
        while (indexR <= data.size()) {
            getRow(indexR++).setBackgroundColor(colorLinea);
        }
    }

    private TableRow getRow(int index) {
        return (TableRow) tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex, int columIndex) {
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columIndex);
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(3, 3, 3, 3);
        params.weight = 1;
        return params;
    }
}