package com.example.viedkaadmin;

import static android.view.Gravity.CENTER_HORIZONTAL;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentEditarProductos extends Fragment {
    private TextView nombreProducto, nombreProductoB, categoria, cantidad, precio;
    private Button buscar, confirmar, eliminar;
    private EditText editText1, editText2, editText3, editText4, editText5;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TableLayout tl;
    private TableRow tr;
    private TextView tv1;
    private ScrollView scrollView;
    private boolean color = false;
    private String [] [] rawConsulta;
    private String[] nombres;

    public FragmentEditarProductos() {

    }

    public static FragmentEditarProductos newInstance(String param1, String param2) {
        FragmentEditarProductos fragment = new FragmentEditarProductos();
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

        View view = inflater.inflate(R.layout.fragment_editar_productos, null, false);

        tl = (TableLayout) view.findViewById(R.id.tableEditarP);
        nombreProducto = view.findViewById(R.id.BuscarNombreP);
        nombreProductoB = view.findViewById(R.id.EditarNombreP);
        categoria = view.findViewById(R.id.EditarCategoriaP);
        cantidad = view.findViewById(R.id.editarCantidadP);
        precio = view.findViewById(R.id.EditarPrecioP);
        buscar = view.findViewById(R.id.btn_BuscarP);
        confirmar = view.findViewById(R.id.btn_ConfirmarP);
        eliminar = view.findViewById(R.id.btn_EliminarP);
        scrollView = view.findViewById(R.id.ScrollViewProductos);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nombreProductoB.equals("") && !categoria.equals("") && !cantidad.equals("") && !precio.equals("") ) {
                    agregarFila();
                    scrollView.fullScroll(View.FOCUS_DOWN);
                } else {
                    Toast.makeText(getActivity(), "Algún campo está vacío", (short) 1000).show();
                }
            }
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        //String colorHeader="#84477F";
        String colorHeader = "#db9600";
        agregaEncabezado(getActivity(), params, "Producto", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Categoria", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Cantidad", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio", tr, colorHeader);
        tl.addView(tr);

        return view;

    }

    private String getColorFondo(boolean color) {
        String acolor = color ? "#ffe5ad" : "#fff2d6";
        return acolor;
    }

    public void agregarFila() {

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);

        tr = new TableRow(getActivity());
        agregaCelda(getActivity(), params, nombreProductoB.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, categoria.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, cantidad.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, precio.getText().toString(), tr, getColorFondo(color));

        tl.addView(tr);

        color = !color;

    }

    private void agregaCelda(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, String color) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregaEncabezado(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, String color) {
        tv1 = new TextView(getActivity());
        tv1.setText(string);
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

}