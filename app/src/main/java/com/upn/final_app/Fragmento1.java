package com.upn.final_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragmento1 extends Fragment {

    EditText txtNombre, txtCodigo, txtVida, txtEstado;
    Button btnRegistrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);

        btnRegistrar = view.findViewById(R.id.btnRegistrar);
        txtNombre = view.findViewById(R.id.txtNombre);
        txtCodigo = view.findViewById(R.id.txtCodigo);
        txtVida = view.findViewById(R.id.txtVida);
        txtEstado = view.findViewById(R.id.txtEstado);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
            }
        });

        return view;
    }

    private boolean capturarDatos(){
        String nombre = txtNombre.getText().toString();
        String codigo = txtCodigo.getText().toString();
        String vida = txtVida.getText().toString();
        String estado = txtEstado.getText().toString();
        boolean valida = true;
        if (nombre.equals("")){
            txtNombre.setError("Titulo es obligatorio");
            valida = false;
        }
        if (codigo.equals("")){
            txtCodigo.setError("Codigo es obligatorio");
            valida = false;
        }
        if (vida.equals("")){
            txtVida.setError("Tiempo de vida es obligatorio");
            valida = false;
        }
        if (estado.equals("")){
            txtEstado.setError("Estado de bien es obligatorio");
            valida = false;
        }
        return valida;
    }
}