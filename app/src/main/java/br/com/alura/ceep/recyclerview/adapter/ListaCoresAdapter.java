package br.com.alura.ceep.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Cores;

public class ListaCoresAdapter extends RecyclerView.Adapter<ListaCoresAdapter.CorViewHolder> {

    private Context context;
    private final List<Cores> cores;
    private OnItemClickListenerCores onItemClickListener;

    public ListaCoresAdapter(Context context, List<Cores> cores) {
        this.context = context;
        this.cores = cores;
    }

    @NonNull
    @Override
    public ListaCoresAdapter.CorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_cor, parent, false);
        return new CorViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull CorViewHolder holder, int position) {
        Cores cor = cores.get(position);
        holder.vincula(cor);
    }

    @Override
    public int getItemCount() {
        return cores.size();
    }


    public void setOnItemClickListener(OnItemClickListenerCores onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class CorViewHolder extends RecyclerView.ViewHolder {

        private final CardView circulo;
        private Cores cor;

        public CorViewHolder(@NonNull View itemView) {
            super(itemView);
            circulo = itemView.findViewById(R.id.item_circulo_cor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(cor,cor.getNome());
                }
            });

        }

        public void vincula(Cores cor) {
            this.cor = cor;
            circulo.setForeground(cor.getCor());
        }
    }

    public void adiciona(Cores cor){

        cores.add(cor);
        notifyDataSetChanged();
    }

    public interface OnItemClickListenerCores {

        void onItemClick(Cores cor, String nome);
    }
}