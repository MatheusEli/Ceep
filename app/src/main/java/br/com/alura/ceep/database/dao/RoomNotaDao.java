package br.com.alura.ceep.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.ceep.model.Nota;

@Dao
public interface RoomNotaDao {

    @Insert
    void insere(Nota... notas);

    @Query("SELECT * FROM Nota")
    List<Nota> todos();

    @Update
    void altera(Nota nota);
}
