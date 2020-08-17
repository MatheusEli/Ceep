package br.com.alura.ceep.database.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.ceep.model.Cor;

public class CorDAO {

    private final static ArrayList<Cor> CORES = new ArrayList<>();

    public List<Cor> todos(){

        return (List<Cor>) CORES.clone();
    }

    public void insere(Cor... cores) {

        CorDAO.CORES.addAll(Arrays.asList(cores));
    }

}
