package br.com.alura.ceep.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        RecyclerView recyclerView = findViewById(R.id.lista_notas_recyclerview);
        NotaDAO dao = new NotaDAO();

        for (int i = 0; i < 10000; i++) {

            dao.insere(new Nota("Alemão","Ich bin Matheus: Eu sou Matheus, I habe hunger: Eu tenho fome, Ich mag Deutschland: Eu gosto da Alemanha."));
        }

        List<Nota> listaNotas = dao.todos();
        recyclerView.setAdapter(new ListaNotasAdapter(this, listaNotas));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}