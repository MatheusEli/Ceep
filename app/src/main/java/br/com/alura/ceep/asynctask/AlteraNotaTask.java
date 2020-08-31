package br.com.alura.ceep.asynctask;

import android.os.AsyncTask;

import br.com.alura.ceep.database.dao.NotaDAO;
import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class AlteraNotaTask extends AsyncTask {

    private final Nota nota;
    private final int posicao;
    private final ListaNotasAdapter adapter;
    private final RoomNotaDao dao;

    public AlteraNotaTask(Nota nota, int posicao, ListaNotasAdapter adapter, RoomNotaDao dao) {
        this.nota = nota;
        this.posicao = posicao;
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        dao.altera(nota);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        adapter.altera(posicao, nota);
    }
}
