package br.com.alura.ceep.dao;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Cores;
import br.com.alura.ceep.model.Nota;

public class CorDAO {

    private final static ArrayList<Cores> cores = new ArrayList<>();

    public List<Cores> todos(){

        return (List<Cores>) cores.clone();
    }

    public void insere(Cores... cores) {

        CorDAO.cores.addAll(Arrays.asList(cores));
    }

    public void altera(int posicao, Cores cor) {

        cores.set(posicao, cor);
    }

    public void remove(int posicao) {

        cores.remove(posicao);
    }

    public void troca(int posicaoInicio, int posicaoFim) {

        Collections.swap(cores, posicaoInicio, posicaoFim);
    }

    public void removeTodos() {

        cores.clear();
    }

}
