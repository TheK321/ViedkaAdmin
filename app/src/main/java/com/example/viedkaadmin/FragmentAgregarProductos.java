package com.example.viedkaadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAgregarProductos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAgregarProductos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TextView textView1, textView2, textView3, textView4, textView5;
    private EditText editText1, editText2, editText3, editText4;
    private Button button1, button2;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAgregarProductos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAgregarProductos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAgregarProductos newInstance(String param1, String param2) {
        FragmentAgregarProductos fragment = new FragmentAgregarProductos();
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
        View view= inflater.inflate(R.layout.fragment_agregar_productos, container, false);

        textView1 = view.findViewById(R.id.textViewProductos);
        textView2 = view.findViewById(R.id.textViewProductosA);
        textView3 = view.findViewById(R.id.textViewProductosB);
        textView4 = view.findViewById(R.id.textViewProductosC);
        textView5 = view.findViewById(R.id.textViewProductosD);

        editText1 = view.findViewById(R.id.editText_TipoArticulo);
        editText2 = view.findViewById(R.id.editText_EdadArticulo);
        editText3 = view.findViewById(R.id.editText_CantidadArticulo);
        editText4 = view.findViewById(R.id.editText_PrecioArticulo);

        button1 =  view.findViewById(R.id.btn_agregarP);
        button2 = view.findViewById(R.id.btn_cancelarP);

        return view;
    }
}