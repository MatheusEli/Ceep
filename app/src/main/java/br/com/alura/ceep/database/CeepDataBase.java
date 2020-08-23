package br.com.alura.ceep.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;

@Database(entities = {Nota.class}, version = 3, exportSchema = false)
public abstract class CeepDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "ceep.db";

    public abstract RoomNotaDao getNotaDao();

    public static CeepDataBase getInstance(Context context){

        return Room.databaseBuilder(context,CeepDataBase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
