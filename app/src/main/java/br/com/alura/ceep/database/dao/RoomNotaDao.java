package br.com.alura.ceep.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.ceep.model.Nota;

@Dao
public interface RoomNotaDao {

    @Insert
    void insere(Nota... notas);

    @Query("SELECT * FROM Nota ORDER BY posicao ASC")
    List<Nota> todos();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void altera(Nota nota);

    @Delete
    void remove(Nota nota);

    @Query("SELECT * FROM Nota WHERE Nota.posicao = :posicao")
    Nota devolveNota(int posicao);
}
