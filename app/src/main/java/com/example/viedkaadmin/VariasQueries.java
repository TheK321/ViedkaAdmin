package com.example.viedkaadmin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VariasQueries#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VariasQueries extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputEditText datos;
    private Button btnejecutar;

    public VariasQueries() {
        // Required empty public constructor
    }

    public static VariasQueries newInstance(String param1, String param2) {
        VariasQueries fragment = new VariasQueries();
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
        View view = inflater.inflate(R.layout.fragment_varias_queries, container, false);
        datos = view.findViewById(R.id.textInputEditText_textoplano);
        btnejecutar = view.findViewById(R.id.Button_ejecutar);
        btnejecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.setText(generaDatosRandoms());
                String[] cadenascomandos = datos.getText().toString().split(";");
                SQLiteDatabase BaseDeDatos = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getActivity().getApplicationContext()).getWritableDatabase();

                for(int i=0;i<cadenascomandos.length;i++){
                    try {
                        BaseDeDatos.execSQL(cadenascomandos[i]);
                    } catch (Exception e){
                        System.out.println("Excepci??n en "+i);
                    }
                }

                datos.setText(reportesrandoms());
                SQLiteDatabase BaseDeDatos2 = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getActivity().getApplicationContext()).getWritableDatabase();
                cadenascomandos = datos.getText().toString().split(";");
                for(int i=0;i<cadenascomandos.length;i++){
                    try {
                        BaseDeDatos2.execSQL(cadenascomandos[i]);
                    } catch (Exception e){
                        System.out.println("Excepci??n en "+i+"\n"+e.toString());
                    }
                }
            }
        });

        return view;
    }

    private String generaDatosRandoms() {
        String[] prendas = {"Camisa","Blusa","Sueter","Bufanda","Gorro","Chamarra","Hoddie","Sudaderas","Pantal??n Mezclilla","Pantal??n Vestir","Pants","Mallas","Falda","Vestido","Pantol??n Legging","Short","Top","Camiseta","Licra","Calcetas","Calcetines","Medias"};
         String[] tipo = {"Adulto","Ni??o"};
         String[] avalible = {"50","35","20","30","35","20","15","20","25","25","30","20","15","10","20","15","20","50","35","25","30","20"};
         String[] sold = {"15","16","10","15","5","8","10","12","10","10","6","7","3","5","15","6","2","11","3","11","11","9"};
         String[] unidad = {"50","34","40","25","30","60","75","25","45","35","40","25","30","130","80","45","35","30","60","30","25","25"};
        String[] nombresapellidos = {"Pepita Busquets Infante","Am??rica Morera","Bel??n Eulalia Fuertes Navarrete","Haroldo Mateo Arranz","Maximino M????iz Batalla","Paola Sevilla Agust??","Mois??s Cantero-Pe??a","Azahar Gisbert J??dar","Olegario Valverde Valenciano","Liliana Rivero Ballesteros","Daniel Suarez","Pac??fica del D??vila","Febe Mate Molina","Mar??a Bel??n Robles Leal","Am??rico de Mendoza","Paula de Taboada","Eligio Carrillo Juli??n","Ricarda Gir??n Medina","Reyna de Murcia","Esteban Agullo Barrera","Anunciaci??n Tenorio Molins","Soledad Segarra Agudo","Nando Bermudez-Cu??llar","Vasco Fernandez Pinedo","Camila Pi??ol Bar??","Feliciana de Lopez","Ana Bel??n Bautista Pazos","Purificaci??n Castells Pou","Soraya Marisol Perez Espa??a","Jes??s Lumbreras Fuster","Celia Sol?? Novoa","Saturnina Clementina Naranjo Quevedo","Emilio Lucio Canals Ben??tez","Eva Mar??a Flor Garz??n","Berto Olivera","Bernardo Infante-Prieto","Emiliano del P??ez","Leonardo Estevez Ballesteros","Demetrio Losa Moreno","Abilio Vega Bayona","Elvira Figueroa Ribes","Bernardita Ver??nica Marin Echeverr??a","Ernesto Sierra Gargallo","Marcelo Boix-Criado","Hector Cabanillas Crespo","Chita Nicol??s","Tom??s del Rovira","Vicenta Cort??s Agust??","Marcelino Alem??n Calvet","Aitana Salmer??n Sanmiguel","Leoncio Vel??zquez Garc??a","Aurelio Escriv?? Aguado","Mauricio Manzano Abella","Teresita Oliv?? Alberto","Federico Rold??n","Eladio Saavedra Pozo","Isabel Estrada Luna","Benito Coca Roman","Heriberto de Pont","Guiomar Recio Romero","Ar??nzazu del Quintanilla","Efra??n S??ez Gual","Haroldo Arregui Cadenas","Onofre Mar??n-Atienza","Coral Pintor-Cerro","Anacleto Gimeno Mateu","Ildefonso del Gracia","Reynaldo Malo Ar??valo","Cecilia Sevilla Moles","Ainara Baena-Mendoza","Marcio Vallejo Bautista","Mar??a Del Carmen Salmer??n Ramirez","Sebasti??n Trujillo Poza","Juan Francisco Tom??s Mendiz??bal","Vicente Naranjo Ramis","Fito Coca","Bernab?? Parra Vilaplana","Te??fila J??dar","Diana Arcelia Ribes Amor??s","Valeria Pizarro Baena","Odalis Ledesma Diaz","Eduardo Amat-Escribano","Horacio Soler Luj??n","Duilio Gallo-Terr??n","Am??rica Regina Garriga T??llez","Porfirio Espada Vall??s","Eloy Borr??s Navas","Amanda Tenorio Avil??s","Manuela Galiano Cort??s","Vencesl??s Abascal","Rub??n Pombo","Mar??a Jes??s Borr??s Ant??nez","C??sar Rosado Cabeza","Abril Trujillo Torrecilla","Br??gida Atienza Vicente","Primitiva Nydia Quir??s Daza","Victor Morillo Costa","In??s Granados Vera","Joan Almansa B??rcena","Edelmira Olivares Salom","Guadalupe Fanny Santamar??a Guti??rrez","Clemente Alvarado Perera","Olga Belmonte Sosa","P??o Teodoro Galvez Nogueira","Jose Francisco Camps Pont","Ascensi??n Corbacho Pazos","Elvira Rebollo Salmer??n","Emma C??zar Murillo","Nayara Torre Luj??n","Trini Pedrosa Lerma","Marino del Mascar??","Reyes Blanch J??dar","Elo??sa de Lago","Rosario Tovar Acosta","Ascensi??n del Beltran","Fabiana Casal","Samuel Fabregat Maza","Mar??a Dolores Hernando Carrillo","Chuy Ricart Guill??n","Lalo Guerrero Duarte","Joaquina Polo Orozco","Lope Isidoro Llamas Leon","Otilia Alonso Iborra","Aurelio Adadia Vergara","Georgina Catalina Alfonso Colomer","Clementina Silva Murcia","Luciano Fuentes Arnau","Pac??fica Bartolom??","Luciano Tapia Tormo","Isaura Ayll??n Casanovas","Fabiola Prat Castell","Porfirio Valbuena Olmo","Jose Carlos Sosa Corral","Ger??nimo Estevez-Polo","Juan Francisco Aguilera Perez","Angelina Pel??ez-Herrera","Lourdes Jord??n","Dan Ju??rez ??guila","Tiburcio Lucio Romero Real","Maximiano Guill??n Belda","Balduino Andres","Paula Asensio G??mez","Eduardo Segovia-Trillo","Melania Mendiz??bal Goicoechea","Leandro Gilabert Infante","Atilio B??rcena Flor","Genoveva Salcedo C??zar","Jenny Ure??a D??az","Leandra Chus Posada Losa","Nadia Alegria Ad??n","Lino Cerd??-Franco","Febe Campos Alberdi","Aristides del Salamanca","Luis Miguel Gonzalo Leiva","??ngrid Nicolau Vara","Paloma Morata Collado","Lucio Morcillo Criado","Albert Mancebo-Sabater","Ruperto del Bru","Josep Ferrera Portillo","Renato de Lozano","Mar Saura Bertr??n","Aitor Canales-Casanovas","Dorita Acevedo","Bego??a Palomar Borrell","Benjam??n R??o Abril","Bonifacio Cabrero ??lamo","Mar??a Jos?? Costa Gal??n","Bernardo Gual Carreras","Jose Angel Malo","Te??filo Gomez Sanmiguel","Claudio del Colom","Carina Edelmira Perell?? Perez","Baldomero Celestino Taboada Guillen","Gustavo Salvador Sans","Silvestre Corral","Rogelio Severo Malo Tom??","C??ndido Losa Conde","Raquel D??az Mariscal","Ruperto Urrutia Gomez","Araceli Rodriguez","Regina Velasco Porta","Herberto Batlle Vergara","Olimpia Benita Noriega Valls","M??xima Sola Sobrino","Godofredo Campo Vallejo","??ngel Ferrer","Jerem??as Prudencio Escolano Ballester","Carina Balaguer Urrutia","Angelina Codina Borrell","Jaime V??ctor Alvarado Mur","Lino Salazar-Sanmiguel","Gregorio Feijoo Su??rez","Paulino de Montserrat","Adri??n Monreal Cervantes","Domitila Alba Dom??nguez","Vicenta Granados Izquierdo","Felicia Barco Cuevas","Anunciaci??n Cifuentes Castell??","Tiburcio Cabo Carballo"};

         String datosrandoms=new String();
        for(int i=0;i<new Random().nextInt(10000) + 1;i++){
                datosrandoms+="insert into Prenda(Nombre,Categoria,Existencias,PrecioCompra,PrecioVenta) values('"+prendas[new Random().nextInt(prendas.length)]+new Random().nextInt(84048)+"','"+tipo[new Random().nextInt(tipo.length)]+"',999999999,"+(new Random().nextInt(50)+25)+","+(new Random().nextInt(40)+15)+");";
        }
        for(int i=0;i<nombresapellidos.length;i++){
            datosrandoms+="insert into Trabajadores(NombreTrab) values('"+nombresapellidos[i]+"');";
        }
        return datosrandoms;
    }

    private String reportesrandoms(){
        String datosrandoms = new String();

        try {
            String [][] rawConsulta = ((MainActivity) getActivity()).Consultar("Prenda", 6, false, "");
            for(int i=0;i<new Random().nextInt(10000) + 1;i++){
                int indicerandom = new Random().nextInt(rawConsulta.length) ;
                int cantidadrandom = new Random().nextInt(5)+1;
                datosrandoms+="insert into Movimientos (Concepto,Categoria,PrecioUni,Cantidad,Total,Tipo,SaldoAnterior,SaldoActual,Fecha,idEmpleado,idPrenda) values" +
                        "('"+rawConsulta[1][indicerandom]
                        +"','"+rawConsulta[2][indicerandom]
                        +"',"+rawConsulta[5][indicerandom]
                        +","+cantidadrandom
                        +","+(cantidadrandom*Integer.parseInt(rawConsulta[5][indicerandom]))
                        +",'Ingreso',0,"+(cantidadrandom*Integer.parseInt(rawConsulta[5][indicerandom])+",202111"+(new Random().nextInt(11)+10)+","+new Random().nextInt(15)+1+","+(rawConsulta[0][indicerandom])+");");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return datosrandoms;
    }
}