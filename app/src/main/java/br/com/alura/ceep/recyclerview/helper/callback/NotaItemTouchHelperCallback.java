package br.com.alura.ceep.recyclerview.helper.callback;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.database.CeepDataBase;
import br.com.alura.ceep.database.dao.NotaDAO;
import br.com.alura.ceep.database.dao.RoomNotaDao;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {


    private final ListaNotasAdapter adapter;
    private RoomNotaDao dao;
    private Context context;

    public NotaItemTouchHelperCallback(ListaNotasAdapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
        dao = CeepDataBase.getInstance(context).getNotaDao();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(marcacoesDeArrastar,marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        Nota nota = dao.devolveNota(viewHolder.getAdapterPosition());
        Toast.makeText(context, "Nome da Nota: "+nota.getTitulo()
                        +"\nDescrição da Nota: "+nota.getDescricao()
                        +"\nID da Nota: "+nota.getId()
                        +"\nPosição da Nota: "+nota.getPosicao(),
                Toast.LENGTH_LONG).show();
//        nota.setPosicao(target.getAdapterPosition());
//        dao.altera(nota);
//        adapter.troca(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        Nota nota = dao.devolveNota(viewHolder.getAdapterPosition());
        Nota nota2 = dao.devolveNota((viewHolder.getAdapterPosition())+1);

        dao.remove(nota);
        adapter.remove(nota.getPosicao());

        nota2.setPosicao(nota.getPosicao());
    }
}
