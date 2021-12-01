package com.example.viedkaadmin;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class FragmentEditarProductos extends Fragment {
    private TextInputEditText nombre, categoria, cantidad, preciocompra, precioventa;
    private Button actualizar, insertar, btn;
    private ImageButton botones;

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
    private Drawable iconoeditar, iconoeliminar;
    private AutoCompleteTextView categoriaS;

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

        int conteo = ((MainActivity) getActivity()).Contar("Prenda");

        System.out.println("CONTEO =" +conteo);

        tl = (TableLayout) view.findViewById(R.id.tableEditarP);
        nombre = view.findViewById(R.id.editTextProductos);
        cantidad = view.findViewById(R.id.editTextCantidad);
        preciocompra = view.findViewById(R.id.editTextPrecioCompra);
        precioventa = view.findViewById(R.id.editTextPrecioVenta);
        insertar = view.findViewById(R.id.btn_ConfirmarP);
        actualizar = view.findViewById(R.id.btn_ActualizarP);
        scrollView = view.findViewById(R.id.ScrollViewProductos);
        idtxt = view.findViewById(R.id.idText);
        iconoeditar = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_editar, null);
        iconoeliminar = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_eliminar, null);
        idtxt.setVisibility(View.GONE);

        categoriaS = view.findViewById(R.id.autoCompleteTextView_nombre);
        String[] val = new String[]{"Adulto","Niño"};

        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item,val );
            categoriaS.setAdapter(adapter);
        }catch(Exception exception){
            System.out.println(exception);
        }

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(nombre) & !isEmpty(preciocompra)
                        & !isEmpty(cantidad) & !isEmpty(precioventa)){
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
                if(!isEmpty(nombre) & !isEmpty(precioventa)
                        & !isEmpty(cantidad) & !isEmpty(preciocompra)){
                    rawConsulta2 = ((MainActivity) getActivity()).Consultar("Prenda", 6, false, "");
                    String[] columnas = new String[]{"Nombre","Categoria","Existencias","PrecioCompra","PrecioVenta"};
                    String[] valores = new String[]{nombre.getText().toString(), categoriaS.getText().toString(),cantidad.getText().toString(),
                            preciocompra.getText().toString(), precioventa.getText().toString()};
                    int actualizado=((MainActivity)getActivity()).Actualizar(columnas,valores,"Prenda","idPrenda",idtxt.getText().toString());
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
        //String colorHeader = "#db9600";
        int colorHeader=R.drawable.style_librocontable_h;
        agregaEncabezado(getActivity(), params, "ID", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Nombre", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Categoria", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Cantidad", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio compra", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio venta", tr, colorHeader);
        tl.addView(tr);
        //
        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Prenda", 6, false, "");
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
                    arregloProductos[1] = listaProductos[i].getNombre();
                    arregloProductos[2] = listaProductos[i].getCategoria();
                    arregloProductos[3] = listaProductos[i].getExistencias();
                    arregloProductos[4] = listaProductos[i].getPrecioC();
                    arregloProductos[5] = listaProductos[i].getPrecioV();
                    agregarFila(
                            listaProductos[i].getId(),
                            listaProductos[i].getNombre(),
                            listaProductos[i].getCategoria(),
                            listaProductos[i].getExistencias(),
                            listaProductos[i].getPrecioC(),
                            listaProductos[i].getPrecioV(),
                            arregloProductos
                    );

                }





            }
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }

        return view;

    }


    private int getColorFondo(boolean color) {
        //String acolor = color ? "#ffe5ad" : "#fff2d6";
        return color ? R.drawable.style_librocontable_u : R.drawable.style_librocontable_d;
    }


    public void agregarFila(int id, String name, String category, String count, String buy, String sell,String[] values) {

        String idd = String.valueOf(id);
        tr = new TableRow(getActivity());
        tr.setTag(String.valueOf(id));


        TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, idd, tr, getColorFondo(color), CENTER_HORIZONTAL);
        params = new TableRow.LayoutParams(250, TableRow.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, name, tr, getColorFondo(color),CENTER);
        params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, category, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, count, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, buy, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, sell, tr, getColorFondo(color),CENTER);
        agregarBotonEditar(getActivity(), params, "Editar", tr, id,values);
        agregarBotonEliminar(getActivity(), params, "Eliminar", tr, id,values[1]);

        tl.addView(tr);
        color = !color;

    }


    private void agregarBotonEditar(FragmentActivity fragmentActivity,TableRow.LayoutParams layoutParams, String string, TableRow tr,int id,String[] data){
        botones = new ImageButton(fragmentActivity);
        botones.setPadding(5, 5, 5, 5);
        botones.setBackgroundColor(0x00000000);
        botones.setImageDrawable(iconoeditar);
        DrawableCompat.setTint(
                DrawableCompat.wrap(botones.getDrawable()),
                ContextCompat.getColor(fragmentActivity, R.color.verde_editar)
        );
        botones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                idtxt.setText(data[0].toString());
                nombre.setText(data[1].toString());
                cantidad.setText(data[3].toString());
                preciocompra.setText(data[4].toString());
                precioventa.setText(data[5].toString());
            }
        });
        tr.addView(botones);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregarBotonEliminar(FragmentActivity fragmentActivity,TableRow.LayoutParams layoutParams, String string, TableRow tr, int id,String nombre){
        botones = new ImageButton(fragmentActivity);
        botones.setPadding(5, 5, 5, 5);
        botones.setBackgroundColor(0x00000000);
        botones.setImageDrawable(iconoeliminar);
        DrawableCompat.setTint(
                DrawableCompat.wrap(botones.getDrawable()),
                ContextCompat.getColor(fragmentActivity, R.color.md_theme_light_error)
        );
        botones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(fragmentActivity)
                        .setTitle("Confirmar")
                        .setMessage("¿Está segura de eliminar a "+nombre+"?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long eliminado=((MainActivity)getActivity()).Eliminar("Prenda","idPrenda",String.valueOf(id));
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
        tr.addView(botones);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregaCelda(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color, int gravedad) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        //tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregaEncabezado(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color) {
        tv1 = new TextView(getActivity());
        tv1.setText(string);
        tv1.setTextColor(Color.WHITE);
        //tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    public void agregarProducto() {
        String nom = nombre.getText().toString();
        String cat = categoriaS.getText().toString();
        String can = cantidad.getText().toString();
        String prec = preciocompra.getText().toString();
        String prev = precioventa.getText().toString();

        String[] encabezado = new String[5];
        encabezado[0] ="Nombre";
        encabezado[1] ="Categoria";
        encabezado[2] ="Existencias";
        encabezado[3] ="PrecioCompra";
        encabezado[4] ="PrecioVenta";

        String [] valores = {nom,cat,can,prec,prev};
        for (int c=0; c<encabezado.length;c++){
            System.out.println(encabezado[c]+" VALORES "+valores[c]);
        }
        long productos = ((MainActivity) getActivity()).Insertar(encabezado, valores, "Prenda");
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