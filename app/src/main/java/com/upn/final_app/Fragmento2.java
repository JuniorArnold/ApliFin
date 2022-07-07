package com.upn.final_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.final_app.entidad.Bien;
import com.upn.final_app.util.AdaptadorBienes;

import java.util.ArrayList;
import java.util.List;

public class Fragmento2 extends Fragment {
    RecyclerView rv;
    AdaptadorBienes adaptadorBienes;
    List<Bien> listaBienes;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento2, container, false);

        rv = view.findViewById(R.id.rvListaBienes);

        listaBienes = new ArrayList<>();
        inicializarFirebase(view.getContext());
        reference.child("Bienes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaBienes.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    Bien bien = item.getValue(Bien.class);
                    listaBienes.add(bien);
                }

                adaptadorBienes = new AdaptadorBienes(view.getContext(), listaBienes);
                rv.setAdapter(adaptadorBienes);
                rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                String id = listaBienes.get(pos).getId();

                AlertDialog.Builder ventana = new AlertDialog.Builder(view.getContext());
                ventana.setTitle("Alerta");
                ventana.setMessage("Deseas eliminar el Bien?");
                ventana.setPositiveButton("Aceptar", (dialog, which) -> {
                    listaBienes.remove(pos);
                    reference.child("Bienes").child(id).removeValue();
                    adaptadorBienes.notifyDataSetChanged();
                });
                ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                });
                ventana.create().show();


            }
        }).attachToRecyclerView(rv);

        return view;
    }

    private void inicializarFirebase(Context context){
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}