package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

public class Cores {

    private String nome;
    private Drawable cor;


    public Cores(String nome, int cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public Drawable getCor() {
        return cor;
    }
}
