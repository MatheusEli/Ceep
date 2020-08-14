package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.CorDAO;
import br.com.alura.ceep.model.Cores;
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
    private List<Cores> listaCores;
    private ConstraintLayout telaFormulario;
    private Drawable corNota;
    private CorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        recyclerViewCores = findViewById(R.id.formulario_lista_cores_recyclerview);
        telaFormulario = findViewById(R.id.formulario_nota_constraint_layout);
        dao = new CorDAO();
        setTitle(TITULO_APPBAR_INSERE);
        preencheListaCores();
        listaCores = dao.todos();
        configuraAdapter(listaCores, recyclerViewCores);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CHAVE_NOTA)){
            setTitle(TITULO_APPBAR_ALTERA);
            Nota notaRecebida = (Nota) dadosRecebidos.getSerializableExtra(CHAVE_NOTA);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos(notaRecebida);
        }
    }

    private void preencheListaCores() {

        Resources res = getResources();

        Drawable azulDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_azul_drawable, null);
        Drawable brancoDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_branco_drawable, null);
        Drawable vermelhoDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_vermelho_drawable, null);
        Drawable verdeDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_verde_drawable, null);
        Drawable amareloDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_amarelo_drawable, null);
        Drawable lilasDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_lilas_drawable, null);
        Drawable cinzaDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_cinza_drawable, null);
        Drawable marromDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_marrom_drawable, null);
        Drawable roxoDrawable = ResourcesCompat.getDrawable(res, R.drawable.fundo_roxo_drawable, null);

        dao.insere(new Cores("AZUL", azulDrawable), new Cores("BRANCO", brancoDrawable), new Cores("VERMELHO", vermelhoDrawable),
        new Cores("VERDE", verdeDrawable),new Cores("AMARELO", amareloDrawable),new Cores("LILAS", lilasDrawable),new Cores("CINZA", cinzaDrawable),
                new Cores("MARROM", marromDrawable),new Cores("ROXO", roxoDrawable));

        corNota = brancoDrawable;

    }


    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
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
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent intentResult = new Intent();
        intentResult.putExtra(CHAVE_NOTA, nota);
        intentResult.putExtra(CHAVE_POSICAO , posicaoRecebida);
        intentResult.putExtra("Cor nota", (Parcelable) corNota);
        setResult(Activity.RESULT_OK,intentResult);
    }

    private Nota criaNota() {
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }

    private boolean eMenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }

    private void configuraAdapter(List<Cores> listaCores, RecyclerView recyclerView) {
        adapter = new ListaCoresAdapter(this, listaCores);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListaCoresAdapter.OnItemClickListenerCores() {
            @Override
            public void onItemClick(Cores cor, String nome) {
                telaFormulario.setBackground(cor.getCor());
                corNota = cor.getCor();
            }
        });
    }
}