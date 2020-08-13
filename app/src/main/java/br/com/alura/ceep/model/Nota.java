package br.com.alura.ceep.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import java.io.Serializable;

import br.com.alura.ceep.R;

public class Nota implements Serializable {

    private final String titulo;
    private final String descricao;
    private final Drawable cor;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        cor = ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.fundo_branco_drawable, null);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}