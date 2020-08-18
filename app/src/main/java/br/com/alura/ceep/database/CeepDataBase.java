package br.com.alura.ceep.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Cor;
import br.com.alura.ceep.model.Nota;

@Database(entities = {Nota.class, Cor.class}, version = 1, exportSchema = false)
public abstract class CeepDataBase extends RoomDatabase {

    public abstract RoomNotaDao getNotaDao();
}
