package com.example.viedkaadmin;

import static android.view.Gravity.CENTER_HORIZONTAL;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class FragmentEditarProductos extends Fragment {
    private TextInputEditText nombre, descripcion, categoria, cantidad, precio;
    private Button actualizar, insertar, btn;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TableLayout tl;
    private TableRow tr;
    private TextView tv1,idtxt;
    private ScrollView scrollView;
    private boolean color = false;
    private String [] [] rawConsulta, rawConsulta2;
    private String[] nombres;
    private Producto [] listaProductos;

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

        View view = inflater.inflate(R.layout.fragment_editar_productos, container, false);

        int conteo = ((MainActivity) getActivity()).Contar("Prendas");

        System.out.println("CONTEO =" +conteo);

        tl = (TableLayout) view.findViewById(R.id.tableEditarP);
        nombre = view.findViewById(R.id.editTextProductos);
        descripcion = view.findViewById(R.id.editTextDescripcion);
        categoria = view.findViewById(R.id.editTextCategoria);
        cantidad = view.findViewById(R.id.editTextCantidad);
        precio = view.findViewById(R.id.editTextPecio);
        insertar = view.findViewById(R.id.btn_ConfirmarP);
        actualizar = view.findViewById(R.id.btn_ActualizarP);
        scrollView = view.findViewById(R.id.ScrollViewProductos);
        idtxt = view.findViewById(R.id.idText);

        idtxt.setVisibility(View.GONE);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(nombre) & !isEmpty(descripcion) & !isEmpty(categoria)
                        & !isEmpty(cantidad) & !isEmpty(precio)){
                    scrollView.fullScroll(View.FOCUS_DOWN);
                    agregarProducto();
                }else {
                    Toast.makeText(getActivity(), "Algún campo está vacío", (short) 1000).show();
                }
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(nombre) & !isEmpty(descripcion) & !isEmpty(categoria)
                        & !isEmpty(cantidad) & !isEmpty(precio)){
                    rawConsulta2 = ((MainActivity) getActivity()).Consultar("Prendas", 6, false, "");
                    String[] columnas = new String[]{"Nombre","Descripcion","Categoria","Cantidad","Precio"};
                    String[] valores = new String[]{nombre.getText().toString(),descripcion.getText().toString()
                    , categoria.getText().toString(),cantidad.getText().toString(),precio.getText().toString()};
                    int actualizado=((MainActivity)getActivity()).Actualizar(columnas,valores,"Prendas","idPrenda",idtxt.getText().toString());
                    if(actualizado == 1){
                        refresh();
                    } else {
                        Toast.makeText(getActivity(), "No se actualizó", (short)1000).show();
                    }
                    scrollView.fullScroll(View.FOCUS_DOWN);
                } else {
                    Toast.makeText(getActivity(), "Algún campo está vacío", (short)1000).show();
                }
            }
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        //String colorHeader="#84477F";
        String colorHeader = "#db9600";
        agregaEncabezado(getActivity(), params, "ID", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Nombre", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Descripción", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Categoria", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Cantidad", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio", tr, colorHeader);
        tl.addView(tr);
        //
        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Prendas", 6, false, "");
            listaProductos = new Producto[rawConsulta[0].length];
            for (int i = 0; i < rawConsulta[0].length; i++) {
                for (int t = 0; t < conteo; t++) {
                    System.out.println("Coordenada "+t+","+i+":"+rawConsulta[i][t]+",");

                    int num = Integer.parseInt(rawConsulta[i][t]);

                    System.out.println("RESULTADO DE PARSEO:"+rawConsulta[i][t]+", "+num);
                    listaProductos[i] = new Producto
                            (num, rawConsulta[1][t], rawConsulta[2][t],
                                    rawConsulta[3][t], rawConsulta[4][t], rawConsulta[5][t]);

                    String[] arregloProductos = new String[6];
                    arregloProductos[0] = listaProductos[i].getId()+"";
                    System.out.println("ID:     "+listaProductos[i].getId());
                    arregloProductos[1] = listaProductos[i].getNombre();
                    System.out.println("Nombre:     "+listaProductos[i].getNombre());
                    arregloProductos[2] = listaProductos[i].getDescripcion();
                    System.out.println("Descripcion:     "+listaProductos[i].getDescripcion());
                    arregloProductos[3] = listaProductos[i].getCategoria();
                    System.out.println("Categoria:     "+listaProductos[i].getCategoria());
                    arregloProductos[4] = listaProductos[i].getCantidad();
                    System.out.println("Cantidad:     "+listaProductos[i].getCantidad());
                    arregloProductos[5] = listaProductos[i].getPrecio();
                    System.out.println("Precio:     "+listaProductos[i].getPrecio());
                    System.out.println("CICLO"+i+":"+arregloProductos);
                    agregarFila(arregloProductos, listaProductos[i].getId());

                }





            }
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }

        return view;

    }


    private String getColorFondo(boolean color) {
        String acolor = color ? "#ffe5ad" : "#fff2d6";
        return acolor;
    }


    public void agregarFila(String [] values, int id) {

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, 50);
        String idd = String.valueOf(id);
        tr = new TableRow(getActivity());
        tr.setTag(String.valueOf(id));
        agregaCelda(getActivity(), params, idd, tr, getColorFondo(color));
        agregaCelda(getActivity(), params, values[1], tr, getColorFondo(color));
        agregaCelda(getActivity(), params, values[2], tr, getColorFondo(color));
        agregaCelda(getActivity(), params, values[3], tr, getColorFondo(color));
        agregaCelda(getActivity(), params, values[4], tr, getColorFondo(color));
        agregaCelda(getActivity(), params, values[5], tr, getColorFondo(color));
        agregarBotonEditar(getActivity(), params, "Editar", tr, id,values,getColorFondo(color));
        agregarBotonEliminar(getActivity(), params, "Eliminar", tr, id,values[1], getColorFondo(color));

        tl.addView(tr);
        color = !color;

    }


    private void agregarBotonEditar(FragmentActivity fragmentActivity,TableRow.LayoutParams layoutParams, String string, TableRow tr,int id,String[] data,String color){
        btn = new Button(fragmentActivity);
        btn.setText(string);
        btn.setBackgroundColor(Color.parseColor(color));
        btn.setTextColor(Color.BLACK);
        btn.setPadding(5, 5, 5, 5);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                idtxt.setText(data[0].toString());
                nombre.setText(data[1].toString());
                descripcion.setText(data[2].toString());
                categoria.setText(data[3].toString());
                cantidad.setText(data[4].toString());
                precio.setText(data[5].toString());
            }
        });
        tr.addView(btn);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregarBotonEliminar(FragmentActivity fragmentActivity,TableRow.LayoutParams layoutParams, String string, TableRow tr, int id,String nombre,String color){
        btn = new Button(fragmentActivity);
        btn.setText(string);
        btn.setBackgroundColor(Color.parseColor(color));
        btn.setTextColor(Color.BLACK);
        btn.setPadding(5, 5, 5, 5);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(fragmentActivity)
                        .setTitle("Confirmar")
                        .setMessage("¿Está segura de eliminar a "+nombre+"?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long eliminado=((MainActivity)getActivity()).Eliminar("Prendas","idPrenda",String.valueOf(id));
                                if(eliminado == -1){
                                    Toast.makeText(fragmentActivity,"Error al Eliminar",(short)1000);
                                } else {
                                    refresh();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(fragmentActivity,"No eliminado "+nombre, (short)1000);
                            }
                        })
                        .show();
            }
        });
        tr.addView(btn);
        tv1.setLayoutParams(layoutParams);
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

    public void agregarProducto() {
        String nom = nombre.getText().toString();
        String des = descripcion.getText().toString();
        String cat = categoria.getText().toString();
        String can = cantidad.getText().toString();
        String pre = precio.getText().toString();

        String[] encabezado = new String[5];
        encabezado[0] ="Nombre";
        encabezado[1] ="Descripcion";
        encabezado[2] ="Categoria";
        encabezado[3] ="Cantidad";
        encabezado[4] ="Precio";

        String [] valores = {nom,des,cat,can,pre};
        for (int c=0; c<encabezado.length;c++){
            System.out.println(encabezado[c]+" VALORES "+valores[c]);
        }
        long productos = ((MainActivity) getActivity()).Insertar(encabezado, valores, "Prendas");
        System.out.println("LONG:"+productos);
        if(productos == -1){
            Toast.makeText(this.getContext(),"Error al insertar",(short)1000);
        } else {
            Toast.makeText(getActivity(), "Producto Ingresado", (short) 1000).show();
            System.out.println("Producto Ingresado");
            refresh();
        }
    }



    public void refresh() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitNow();
            getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitNow();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}