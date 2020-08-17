package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cor implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id = 0;
    private String nome;
    private Drawable cor;
    private int corRes;

    public Cor(String nome, Drawable cor, int corRes) {
        this.nome = nome;
        this.cor = cor;
        this.corRes = corRes;
    }

    public String getNome() {
        return nome;
    }

    public Drawable getCor() {
        return cor;
    }

    public int getCorRes() {
        return corRes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
