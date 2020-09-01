package br.com.alura.ceep.asynctask;

import android.os.AsyncTask;

import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class RemoveNotaTask extends AsyncTask<Void,Void,Integer> {

    private Nota nota;
    private ListaNotasAdapter adapter;
    private RoomNotaDao dao;

    public RemoveNotaTask(Nota nota, ListaNotasAdapter adapter, RoomNotaDao dao) {
        this.nota = nota;
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        int posicao = nota.getPosicao();
        dao.remove(nota);
        return posicao;
    }

    @Override
    protected void onPostExecute(Integer posicao) {
        super.onPostExecute(posicao);
        adapter.remove(posicao);
    }
}
