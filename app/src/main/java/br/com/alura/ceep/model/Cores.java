package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Cores implements Serializable {

    private String nome;
    private Drawable cor;
    private int corRes;

    public Cores(String nome, Drawable cor, int corRes) {
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
}
