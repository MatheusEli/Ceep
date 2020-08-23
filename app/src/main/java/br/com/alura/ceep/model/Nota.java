package br.com.alura.ceep.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import br.com.alura.ceep.R;

@Entity
public class Nota implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id = 0;
    private final String titulo;
    private final String descricao;
    private int corRes;
    private int posicao;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.corRes = R.drawable.fundo_branco_drawable;
        posicao = -1;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCorRes() {
        return corRes;
    }

    public void setCorRes(int corRes) {
        this.corRes = corRes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}