package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    private ListaNotasAdapter adapter;
    private List<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        listaNotas = notasDeExemplo();
        configuraRecyclerView(listaNotas);

        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        Intent intent = new Intent(ListaNotasActivity.this,FormularioNotaActivity.class);
        startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data.hasExtra("nota") && requestCode == 1 && resultCode == 2){

            Nota notaRecebida = (Nota) data.getSerializableExtra("nota");
            NotaDAO dao = new NotaDAO();
            dao.insere(notaRecebida);
            adapter.adiciona(notaRecebida);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private List<Nota> notasDeExemplo() {
        NotaDAO dao = new NotaDAO();
        dao.insere(new Nota("Alemão","Ich bin Matheus: Eu sou Matheus, I habe hunger: Eu tenho fome, Ich mag Deutschland: Eu gosto da Alemanha."));
        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> listaNotas) {
        RecyclerView recyclerView = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(listaNotas, recyclerView);
    }

    private void configuraAdapter(List<Nota> listaNotas, RecyclerView recyclerView) {
        adapter = new ListaNotasAdapter(this, listaNotas);
        recyclerView.setAdapter(adapter);
    }

}