package br.com.alura.ceep.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.ceep.database.converter.ConversorDrawable;
import br.com.alura.ceep.database.dao.RoomCorDao;
import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Cor;
import br.com.alura.ceep.model.Nota;

@Database(entities = {Nota.class, Cor.class}, version = 1, exportSchema = false)
@TypeConverters({ConversorDrawable.class})
public abstract class CeepDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "ceep.db";

    public abstract RoomNotaDao getNotaDao();

    public abstract RoomCorDao getCorDao();

    public static CeepDataBase getInstance(Context context){

        return Room.databaseBuilder(context,CeepDataBase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries().build();
    }
}
