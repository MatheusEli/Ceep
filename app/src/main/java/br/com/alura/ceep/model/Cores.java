package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Cores implements Serializable {

    private String nome;
    private Drawable cor;


    public Cores(String nome, Drawable cor) {
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
