package br.com.alura.ceep.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.alura.ceep.model.Cor;

@Dao
public interface RoomCorDao {

    @Query("SELECT * FROM Cor")
    List<Cor> todos();

    @Insert
    void insere(Cor... cores);

}
