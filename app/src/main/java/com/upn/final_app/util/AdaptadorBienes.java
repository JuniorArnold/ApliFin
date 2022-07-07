package com.upn.final_app.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.upn.final_app.Fragmento1;
import com.upn.final_app.R;
import com.upn.final_app.RegistrarActivity;
import com.upn.final_app.entidad.Bien;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorBienes extends RecyclerView.Adapter<AdaptadorBienes.MyViewHolder> {

    Context context;
    List<Bien> listaBienes = new ArrayList<>();

    public AdaptadorBienes(Context context, List<Bien> listaBienes){
        this.context = context;
        this.listaBienes = listaBienes;
    }

    @NonNull
    @Override
    public AdaptadorBienes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_bienes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorBienes.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(listaBienes.get(position).getNombre()+"");
        holder.codigo.setText(listaBienes.get(position).getCodPat()+"");
        holder.estado.setText(listaBienes.get(position).getEstado()+"");
        holder.vida.setText(listaBienes.get(position).getVida()+"");

        holder.linearLayout.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, RegistrarActivity.class);
            intent.putExtra("id", listaBienes.get(position).getId()+"");
            intent.putExtra("nombre", listaBienes.get(position).getNombre()+"");
            intent.putExtra("codigo", listaBienes.get(position).getCodPat()+"");
            intent.putExtra("estado", listaBienes.get(position).getEstado()+"");
            intent.putExtra("vida", listaBienes.get(position).getVida()+"");

            intent.putExtra("btnTitutlo", "Actualizar Bien");
            context.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listaBienes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText nombre, codigo, estado, vida;
        LinearLayout linearLayout;
        CardView fila_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.fila_nombre);
            codigo = itemView.findViewById(R.id.fila_codigo);
            estado = itemView.findViewById(R.id.fila_estado);
            vida = itemView.findViewById(R.id.fila_vida);

            linearLayout = itemView.findViewById(R.id.fila_layout);
            fila_card = itemView.findViewById(R.id.fila_card);
        }
    }
}
