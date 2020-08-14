package br.com.alura.ceep.model;


import java.io.Serializable;

import br.com.alura.ceep.R;

public class Nota implements Serializable {

    private final String titulo;
    private final String descricao;
    private int corRes;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.corRes = R.drawable.fundo_branco_drawable;
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
}