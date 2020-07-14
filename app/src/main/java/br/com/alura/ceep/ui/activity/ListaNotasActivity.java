package br.com.alura.ceep.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        ListView listview = findViewById(R.id.listView);
        NotaDAO dao = new NotaDAO();

        dao.insere(new Nota("Alemão","Ich bin Matheus: Eu sou Matheus, I habe hunger: Eu tenho fome, Ich mag Deutschland: Eu gosto da Alemanha."));

        List<Nota> listaNotas = dao.todos();
        listview.setAdapter(new ListaNotasAdapter(this,listaNotas));
    }
}