package br.com.alura.ceep.asynctask;

import android.os.AsyncTask;

import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class InsereNotaTask extends AsyncTask {
    private final Nota nota;
    private final RoomNotaDao dao;
    private final ListaNotasAdapter adapter;
    private final int posicaoContador;

    public InsereNotaTask(Nota nota, RoomNotaDao dao, ListaNotasAdapter adapter, int posicaoContador) {
        this.nota = nota;
        this.dao = dao;
        this.adapter = adapter;
        this.posicaoContador = posicaoContador;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        nota.setPosicao(posicaoContador);
        dao.insere(nota);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        adapter.adiciona(nota);
    }
}
