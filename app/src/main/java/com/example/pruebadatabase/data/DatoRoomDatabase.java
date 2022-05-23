package com.example.pruebadatabase.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// CLASE DONDE SE GENERA LA BASE DE DATOS

@Database(entities = {DatoEntity.class} , version = 1) // 1. LoqueseaEntity.class, otrasTablas...
public abstract class DatoRoomDatabase extends RoomDatabase {

    // 2. Crear constante con el nombre de la base de datos
    private static String DATABASE_NAME = "basededatos";

    // 3. Definir metodo abstracto dataDao o InterfazDao
    public abstract DataDao dataDao();

    // 4. Crear una instancia de tipo DatoRoomDatabase
    private static volatile DatoRoomDatabase INSTANCE;

    // 5. Definir metodo getInstance, se copia y se pega, lo unico que cambia es el nombre de esta clase
    public synchronized static DatoRoomDatabase getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DatoRoomDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
