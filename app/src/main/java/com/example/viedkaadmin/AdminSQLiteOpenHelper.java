package com.example.viedkaadmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//SQLiteOpenHelper genera los metodos onCreate onUpgrade y AdminSQLiteOpenHelper
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ViedkaBD";
        private static final int DATABASE_VERSION = 8;

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

        BaseDeDatos.execSQL("CREATE TABLE Prenda (" +
                "idPrenda INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                "Nombre TEXT NOT NULL UNIQUE," +
                "Categoria TEXT  NOT NULL," +
                "Existencias INTEGER NOT NULL CHECK (Existencias >= 0)," +
                "PrecioCompra INTEGER NOT NULL," +
                "PrecioVenta NOT NULL);");

        BaseDeDatos.execSQL("CREATE TABLE Movimientos (" +
                "idMovimiento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "Concepto TEXT NOT NULL," +
                "Categoria TEXT NOT NULL," +
                "PrecioUni INTEGER NOT NULL," +
                "Cantidad INTEGER NOT NULL CHECK (Cantidad >= 0)," +
                "Total INTEGER NOT NULL," +
                "Tipo TEXT NOT NULL," +
                "SaldoAnterior INTEGER NOT NULL DEFAULT(0),"+
                "SaldoActual NOT NULL,"+
                "Fecha INTEGER NOT NULL," +
                "idEmpleado INTEGER NOT NULL,"+
                "idPrenda INTEGER);");

        BaseDeDatos.execSQL("create table Trabajadores"/*Nombre de la table*/ +
                "(idTrab integer primary key autoincrement not null unique," +
                "NombreTrab text not null)"); /*Columnas de la tabla*/

        BaseDeDatos.execSQL("CREATE TRIGGER actualizarStock AFTER INSERT ON Movimientos FOR EACH ROW " +
                "BEGIN" +
                " UPDATE Prenda SET Existencias = Existencias - new.Cantidad WHERE idPrenda = new.idPrenda;" +
                " END;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

