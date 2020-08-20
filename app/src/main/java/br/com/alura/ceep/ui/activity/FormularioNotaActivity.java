package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.database.CeepDataBase;
import br.com.alura.ceep.database.dao.CorDAO;
import br.com.alura.ceep.database.dao.RoomCorDao;
import br.com.alura.ceep.model.Cor;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaCoresAdapter;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

public class FormularioNotaActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_INSERE = "Inserir Nota";
    public static final String TITULO_APPBAR_ALTERA = "Alterar Nota";
    private int posicaoRecebida = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;
    private RecyclerView recyclerViewCores;
    private ListaCoresAdapter adapter;
    private List<Cor> listaCores;
    private ConstraintLayout telaFormulario;
    private int corNotaRes;
    private RoomCorDao dao;
    private Nota notaRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        recyclerViewCores = findViewById(R.id.formulario_lista_cores_recyclerview);
        telaFormulario = findViewById(R.id.formulario_nota_constraint_layout);
        corNotaRes = R.drawable.fundo_branco_drawable;
        dao = Room
                .databaseBuilder
                        (this, CeepDataBase.class, "ceep.db")
                .allowMainThreadQueries()
                .build().getCorDao();

        setTitle(TITULO_APPBAR_INSERE);
        preencheListaCores();
        listaCores = dao.todos();
        configuraAdapter(listaCores, recyclerViewCores);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CHAVE_NOTA)){
            setTitle(TITULO_APPBAR_ALTERA);
            notaRecebida = (Nota) dadosRecebidos.getSerializableExtra(CHAVE_NOTA);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos(notaRecebida);
        }
    }

    private void preencheListaCores() {

        Resources res = getResources();

        Drawable azulDrawable = getDrawable(res, R.drawable.fundo_azul_drawable);
        Drawable brancoDrawable = getDrawable(res, R.drawable.fundo_branco_drawable);
        Drawable vermelhoDrawable = getDrawable(res, R.drawable.fundo_vermelho_drawable);
        Drawable verdeDrawable = getDrawable(res, R.drawable.fundo_verde_drawable);
        Drawable amareloDrawable = getDrawable(res, R.drawable.fundo_amarelo_drawable);
        Drawable lilasDrawable = getDrawable(res, R.drawable.fundo_lilas_drawable);
        Drawable cinzaDrawable = getDrawable(res, R.drawable.fundo_cinza_drawable);
        Drawable marromDrawable = getDrawable(res, R.drawable.fundo_marrom_drawable);
        Drawable roxoDrawable = getDrawable(res, R.drawable.fundo_roxo_drawable);

        dao.insere(new Cor("AZUL", azulDrawable,R.drawable.fundo_azul_drawable),
                    new Cor("BRANCO", brancoDrawable, R.drawable.fundo_branco_drawable),
                    new Cor("VERMELHO", vermelhoDrawable, R.drawable.fundo_vermelho_drawable),
                    new Cor("VERDE", verdeDrawable, R.drawable.fundo_verde_drawable),
                    new Cor("AMARELO", amareloDrawable, R.drawable.fundo_amarelo_drawable),
                    new Cor("LILAS", lilasDrawable, R.drawable.fundo_lilas_drawable),
                    new Cor("CINZA", cinzaDrawable, R.drawable.fundo_cinza_drawable),
                    new Cor("MARROM", marromDrawable, R.drawable.fundo_marrom_drawable),
                    new Cor("ROXO", roxoDrawable, R.drawable.fundo_roxo_drawable));

    }

    private Drawable getDrawable(Resources res, int p) {
        return ResourcesCompat.getDrawable(res, p, null);
    }


    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
        corNotaRes = notaRecebida.getCorRes();
        telaFormulario.setBackground(getDrawable(getResources(), notaRecebida.getCorRes()));
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(eMenuSalvaNota(item)){

            Nota notaCriada = criaNota();
            notaCriada.setCorRes(corNotaRes);
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent intentResult = new Intent();
        intentResult.putExtra(CHAVE_NOTA, nota);
        intentResult.putExtra(CHAVE_POSICAO , posicaoRecebida);
        setResult(Activity.RESULT_OK,intentResult);
    }

    private Nota criaNota() {
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }

    private boolean eMenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }

    private void configuraAdapter(List<Cor> listaCores, RecyclerView recyclerView) {
        adapter = new ListaCoresAdapter(this, listaCores);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListaCoresAdapter.OnItemClickListenerCores() {
            @Override
            public void onItemClick(Cor cor, String nome) {
                telaFormulario.setBackground(cor.getCor());
                corNotaRes = cor.getCorRes();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable("NOTA",notaRecebida);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
         notaRecebida = (Nota) savedInstanceState.getSerializable("NOTA");
    }
}