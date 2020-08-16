package br.com.alura.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.ceep.model.Cores;

public class CorDAO {

    private final static ArrayList<Cores> cores = new ArrayList<>();

    public List<Cores> todos(){

        return (List<Cores>) cores.clone();
    }

    public void insere(Cores... cores) {

        CorDAO.cores.addAll(Arrays.asList(cores));
    }

}
