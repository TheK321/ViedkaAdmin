package com.example.viedkaadmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//SQLiteOpenHelper genera los metodos onCreate onUpgrade y AdminSQLiteOpenHelper
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ViedkaBD";
        private static final int DATABASE_VERSION = 4;

        private static DatabaseHelper mInstance;

        private DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public static synchronized DatabaseHelper getInstance(Context context) {

            if (mInstance == null) {
                mInstance = new DatabaseHelper(context.getApplicationContext());
            }
            return mInstance;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {



            onCreate(db);
        }
    }

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos/*Nombre de la Base de Datos*/) {
        //Creacion de la tabla prendas en la BaseDeDatos
        BaseDeDatos.execSQL("create table Prendas"/*Nombre de la table*/ +
                "(idPrenda integer primary key autoincrement not null unique, " +
                "Nombre text not null,Descripcion text," +
                "Categoria text, Cantidad int not null, " +
                "Precio real not null)");/*Columnas de la tabla*/

        BaseDeDatos.execSQL("create table Compra"/*Nombre de la table*/ +
                "(idCompra integer primary key autoincrement not null unique, " +
                "NombreProd text not null,Descripcion text," +
                "Cantidad integer not null, " +
                "PrecioUnitario real not null," +
                "MontoTotal real not null)");/*Columnas de la tabla*/

        BaseDeDatos.execSQL("create table Compra_Prendas"/*Nombre de la table*/ +
                "(Compra_idCompra integer references Compra (idCompra) ON DELETE RESTRICT ON UPDATE RESTRICT NOT NULL," +
                "Prendas_idPrenda integer references Prendas (idPrenda) ON DELETE RESTRICT ON UPDATE RESTRICT NOT NULL," +
                "PRIMARY KEY (Compra_idCompra, Prendas_idPrenda))"); /*Columnas de la tabla*/

        BaseDeDatos.execSQL("create table Trabajadores"/*Nombre de la table*/ +
                "(idTrab integer primary key autoincrement not null unique," +
                "NombreTrab text not null)"); /*Columnas de la tabla*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

