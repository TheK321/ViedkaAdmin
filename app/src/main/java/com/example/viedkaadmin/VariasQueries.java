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
                        System.out.println("Excepción en "+i);
                    }
                }

                datos.setText(reportesrandoms());
                SQLiteDatabase BaseDeDatos2 = AdminSQLiteOpenHelper.DatabaseHelper.getInstance(getActivity().getApplicationContext()).getWritableDatabase();
                cadenascomandos = datos.getText().toString().split(";");
                for(int i=0;i<cadenascomandos.length;i++){
                    try {
                        BaseDeDatos2.execSQL(cadenascomandos[i]);
                    } catch (Exception e){
                        System.out.println("Excepción en "+i+"\n"+e.toString());
                    }
                }
            }
        });

        return view;
    }

    private String generaDatosRandoms() {
        String[] prendas = {"Camisa","Blusa","Sueter","Bufanda","Gorro","Chamarra","Hoddie","Sudaderas","Pantalón Mezclilla","Pantalón Vestir","Pants","Mallas","Falda","Vestido","Pantolón Legging","Short","Top","Camiseta","Licra","Calcetas","Calcetines","Medias"};
         String[] tipo = {"Adulto","Niño"};
         String[] avalible = {"50","35","20","30","35","20","15","20","25","25","30","20","15","10","20","15","20","50","35","25","30","20"};
         String[] sold = {"15","16","10","15","5","8","10","12","10","10","6","7","3","5","15","6","2","11","3","11","11","9"};
         String[] unidad = {"50","34","40","25","30","60","75","25","45","35","40","25","30","130","80","45","35","30","60","30","25","25"};
        String[] nombresapellidos = {"Pepita Busquets Infante","América Morera","Belén Eulalia Fuertes Navarrete","Haroldo Mateo Arranz","Maximino Múñiz Batalla","Paola Sevilla Agustí","Moisés Cantero-Peña","Azahar Gisbert Jódar","Olegario Valverde Valenciano","Liliana Rivero Ballesteros","Daniel Suarez","Pacífica del Dávila","Febe Mate Molina","María Belén Robles Leal","Américo de Mendoza","Paula de Taboada","Eligio Carrillo Julián","Ricarda Girón Medina","Reyna de Murcia","Esteban Agullo Barrera","Anunciación Tenorio Molins","Soledad Segarra Agudo","Nando Bermudez-Cuéllar","Vasco Fernandez Pinedo","Camila Piñol Baró","Feliciana de Lopez","Ana Belén Bautista Pazos","Purificación Castells Pou","Soraya Marisol Perez España","Jesús Lumbreras Fuster","Celia Solé Novoa","Saturnina Clementina Naranjo Quevedo","Emilio Lucio Canals Benítez","Eva María Flor Garzón","Berto Olivera","Bernardo Infante-Prieto","Emiliano del Páez","Leonardo Estevez Ballesteros","Demetrio Losa Moreno","Abilio Vega Bayona","Elvira Figueroa Ribes","Bernardita Verónica Marin Echeverría","Ernesto Sierra Gargallo","Marcelo Boix-Criado","Hector Cabanillas Crespo","Chita Nicolás","Tomás del Rovira","Vicenta Cortés Agustí","Marcelino Alemán Calvet","Aitana Salmerón Sanmiguel","Leoncio Velázquez García","Aurelio Escrivá Aguado","Mauricio Manzano Abella","Teresita Olivé Alberto","Federico Roldán","Eladio Saavedra Pozo","Isabel Estrada Luna","Benito Coca Roman","Heriberto de Pont","Guiomar Recio Romero","Aránzazu del Quintanilla","Efraín Sáez Gual","Haroldo Arregui Cadenas","Onofre Marín-Atienza","Coral Pintor-Cerro","Anacleto Gimeno Mateu","Ildefonso del Gracia","Reynaldo Malo Arévalo","Cecilia Sevilla Moles","Ainara Baena-Mendoza","Marcio Vallejo Bautista","María Del Carmen Salmerón Ramirez","Sebastián Trujillo Poza","Juan Francisco Tomás Mendizábal","Vicente Naranjo Ramis","Fito Coca","Bernabé Parra Vilaplana","Teófila Jódar","Diana Arcelia Ribes Amorós","Valeria Pizarro Baena","Odalis Ledesma Diaz","Eduardo Amat-Escribano","Horacio Soler Luján","Duilio Gallo-Terrón","América Regina Garriga Téllez","Porfirio Espada Vallés","Eloy Borrás Navas","Amanda Tenorio Avilés","Manuela Galiano Cortés","Venceslás Abascal","Rubén Pombo","María Jesús Borrás Antúnez","César Rosado Cabeza","Abril Trujillo Torrecilla","Brígida Atienza Vicente","Primitiva Nydia Quirós Daza","Victor Morillo Costa","Inés Granados Vera","Joan Almansa Bárcena","Edelmira Olivares Salom","Guadalupe Fanny Santamaría Gutiérrez","Clemente Alvarado Perera","Olga Belmonte Sosa","Pío Teodoro Galvez Nogueira","Jose Francisco Camps Pont","Ascensión Corbacho Pazos","Elvira Rebollo Salmerón","Emma Cózar Murillo","Nayara Torre Luján","Trini Pedrosa Lerma","Marino del Mascaró","Reyes Blanch Jódar","Eloísa de Lago","Rosario Tovar Acosta","Ascensión del Beltran","Fabiana Casal","Samuel Fabregat Maza","María Dolores Hernando Carrillo","Chuy Ricart Guillén","Lalo Guerrero Duarte","Joaquina Polo Orozco","Lope Isidoro Llamas Leon","Otilia Alonso Iborra","Aurelio Adadia Vergara","Georgina Catalina Alfonso Colomer","Clementina Silva Murcia","Luciano Fuentes Arnau","Pacífica Bartolomé","Luciano Tapia Tormo","Isaura Ayllón Casanovas","Fabiola Prat Castell","Porfirio Valbuena Olmo","Jose Carlos Sosa Corral","Gerónimo Estevez-Polo","Juan Francisco Aguilera Perez","Angelina Peláez-Herrera","Lourdes Jordán","Dan Juárez Águila","Tiburcio Lucio Romero Real","Maximiano Guillén Belda","Balduino Andres","Paula Asensio Gómez","Eduardo Segovia-Trillo","Melania Mendizábal Goicoechea","Leandro Gilabert Infante","Atilio Bárcena Flor","Genoveva Salcedo Cózar","Jenny Ureña Díaz","Leandra Chus Posada Losa","Nadia Alegria Adán","Lino Cerdá-Franco","Febe Campos Alberdi","Aristides del Salamanca","Luis Miguel Gonzalo Leiva","Íngrid Nicolau Vara","Paloma Morata Collado","Lucio Morcillo Criado","Albert Mancebo-Sabater","Ruperto del Bru","Josep Ferrera Portillo","Renato de Lozano","Mar Saura Bertrán","Aitor Canales-Casanovas","Dorita Acevedo","Begoña Palomar Borrell","Benjamín Río Abril","Bonifacio Cabrero Álamo","María José Costa Galán","Bernardo Gual Carreras","Jose Angel Malo","Teófilo Gomez Sanmiguel","Claudio del Colom","Carina Edelmira Perelló Perez","Baldomero Celestino Taboada Guillen","Gustavo Salvador Sans","Silvestre Corral","Rogelio Severo Malo Tomé","Cándido Losa Conde","Raquel Díaz Mariscal","Ruperto Urrutia Gomez","Araceli Rodriguez","Regina Velasco Porta","Herberto Batlle Vergara","Olimpia Benita Noriega Valls","Máxima Sola Sobrino","Godofredo Campo Vallejo","Ángel Ferrer","Jeremías Prudencio Escolano Ballester","Carina Balaguer Urrutia","Angelina Codina Borrell","Jaime Víctor Alvarado Mur","Lino Salazar-Sanmiguel","Gregorio Feijoo Suárez","Paulino de Montserrat","Adrián Monreal Cervantes","Domitila Alba Domínguez","Vicenta Granados Izquierdo","Felicia Barco Cuevas","Anunciación Cifuentes Castelló","Tiburcio Cabo Carballo"};

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
                        +",'Ingreso',0,"+(cantidadrandom*Integer.parseInt(rawConsulta[5][indicerandom])+",'"+(new Random().nextInt(11)+10)+"-Nov-2021',"+new Random().nextInt(15)+1+","+(rawConsulta[0][indicerandom])+");");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return datosrandoms;
    }
}