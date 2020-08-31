package br.com.alura.ceep.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;

public class BuscaNotasTask extends AsyncTask<Void, Void, List<Nota>> {

    private RoomNotaDao dao;
    private List<Nota> notas;

    public BuscaNotasTask(RoomNotaDao dao) {
        this.dao = dao;
    }

    @Override
    protected List<Nota> doInBackground(Void... voids) {
        notas = dao.todos();
        return notas;
    }
}
