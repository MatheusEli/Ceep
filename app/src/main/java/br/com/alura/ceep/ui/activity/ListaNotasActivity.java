package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.database.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;
import br.com.alura.ceep.recyclerview.helper.callback.NotaItemTouchHelperCallback;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CODIGO_REQUISICAO_ALTERA_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

public class ListaNotasActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Notas";
    private static final String TEXTO_LAYOUT = "LAYOUT";
    private static final String TEXTO_LINEAR_LAYOUT = "LINEAR";
    private static final String TEXTO_GRID_LAYOUT = "GRID";
    private ListaNotasAdapter adapter;
    private MenuItem gridIcon;
    private MenuItem linearIcon;
    private RecyclerView recyclerView;
    private int corNota;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_notas);
        List<Nota> listaNotas = pegaTodasNotas();
        recyclerView = findViewById(R.id.lista_notas_recyclerview);
        setTitle(TITULO_APPBAR);
        configuraRecyclerView(listaNotas);
        configuraBotaoInsereNota();
        shared = getSharedPreferences(TEXTO_LAYOUT, MODE_PRIVATE);
        editor = shared.edit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_de_notas, menu);
        linearIcon = menu.findItem(R.id.menu_lista_notas_linear_layout);
        gridIcon = menu.findItem(R.id.menu_lista_notas_grid_layout);

        if(shared.getString(TEXTO_LAYOUT, TEXTO_LINEAR_LAYOUT)
                .equalsIgnoreCase(TEXTO_LINEAR_LAYOUT)){
            trocaIconeLayout(true, false);
            converteParaLinear();
        }else if(shared.getString(TEXTO_LAYOUT, TEXTO_GRID_LAYOUT)
                .equalsIgnoreCase(TEXTO_GRID_LAYOUT)){
            trocaIconeLayout(false, true);
            converteParaStaggeredGrid();
        }else{
            linearIcon.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_lista_notas_grid_layout){
            trocaIconeLayout(false, true);
            converteParaStaggeredGrid();
            salvaLayout(TEXTO_GRID_LAYOUT);
        }
        if(item.getItemId() == R.id.menu_lista_notas_linear_layout){
            trocaIconeLayout(true, false);
            converteParaLinear();
            salvaLayout(TEXTO_LINEAR_LAYOUT);
        }
        return super.onOptionsItemSelected(item);
    }

    private void converteParaLinear() {
        LinearLayoutManager linear = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);
    }

    private void salvaLayout(String layout) {
        editor.putString(TEXTO_LAYOUT, layout);
        editor.commit();
    }

    private void converteParaStaggeredGrid() {
        StaggeredGridLayoutManager grid = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(grid);
    }

    private void trocaIconeLayout(boolean b, boolean b2) {
        gridIcon.setVisible(b);
        linearIcon.setVisible(b2);
    }

    private void configuraBotaoInsereNota() {
        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaFormularioNotaActivityInsere();
            }
        });
    }

    private void vaiParaFormularioNotaActivityInsere() {
        Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
        startActivityForResult(intent, NotaActivityConstantes.CODIGO_REQUISICAO_INSERE_NOTA);
    }

    private List<Nota> pegaTodasNotas() {
        NotaDAO dao = new NotaDAO();
        return dao.todos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (eUmResultadoInsereNota(requestCode, data)) {

            if (resultadoOk(resultCode)) {
                Nota notaRecebida = (Nota) data.getSerializableExtra(CHAVE_NOTA);
                adiciona(notaRecebida);
            }
        }

        if (eResultadoAlteraNota(requestCode, data)) {

            if (resultadoOk(resultCode)) {
                Nota notaRecebida = (Nota) data.getSerializableExtra(CHAVE_NOTA);
                int posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

                if (ePosicaoValida(posicao)) {
                    altera(notaRecebida, posicao);
                }
            }


        }

    }

    private void altera(Nota nota, int posicao) {
        new NotaDAO().altera(posicao, nota);
        adapter.altera(posicao, nota);
    }

    private boolean ePosicaoValida(int posicao) {
        return posicao > POSICAO_INVALIDA;
    }

    private boolean eResultadoAlteraNota(int requestCode, Intent data) {
        return data != null && (eCodigoRequisicaoAlteraNota(requestCode) && data.hasExtra(CHAVE_NOTA));
    }

    private boolean eCodigoRequisicaoAlteraNota(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_ALTERA_NOTA;
    }

    private void adiciona(Nota nota) {
        new NotaDAO().insere(nota);
        adapter.adiciona(nota);
    }

    private boolean eUmResultadoInsereNota(int requestCode, @Nullable Intent data) {
        return temNota(data) && eCodigoRequisicaoInsereNota(requestCode);
    }

    private boolean temNota(@Nullable Intent data) {
        return data != null && data.hasExtra(CHAVE_NOTA);
    }

    private boolean resultadoOk(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean eCodigoRequisicaoInsereNota(int requestCode) {
        return requestCode == NotaActivityConstantes.CODIGO_REQUISICAO_INSERE_NOTA;
    }

    private void configuraRecyclerView(List<Nota> listaNotas) {
        configuraAdapter(listaNotas, recyclerView);
        configuraItemTouchHelper(recyclerView);
    }

    private void configuraItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void configuraAdapter(List<Nota> listaNotas, RecyclerView recyclerView) {
        adapter = new ListaNotasAdapter(this, listaNotas);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListaNotasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota, int posicao) {
                vaiParaFormlarioNotaActivityAltera(nota, posicao);
            }
        });
    }

    private void vaiParaFormlarioNotaActivityAltera(Nota nota, int posicao) {
        Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
        intent.putExtra(CHAVE_NOTA, nota);
        intent.putExtra(CHAVE_POSICAO, posicao);
        startActivityForResult(intent, CODIGO_REQUISICAO_ALTERA_NOTA);
    }

}