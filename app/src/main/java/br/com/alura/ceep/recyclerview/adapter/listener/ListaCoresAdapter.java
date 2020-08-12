package br.com.alura.ceep.recyclerview.adapter.listener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Cores;

public class ListaCoresAdapter extends RecyclerView.Adapter {

    private Context context;

    public ListaCoresAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_cor, parent, false);

        return new RecyclerView.ViewHolder(viewCriada) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cores cor = Cores.AZUL;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
