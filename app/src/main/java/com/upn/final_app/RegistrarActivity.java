package com.upn.final_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.final_app.entidad.Bien;

import java.util.HashMap;
import java.util.UUID;

public class RegistrarActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    Bien bien;
    HashMap map = new HashMap();

    TextView textView;
    EditText txtNombre, txtCodigo, txtVida, txtEstado;
    Button btnRegistrar;
    String id;
    boolean registra = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicializarFirebase(this);

        textView = findViewById(R.id.textView);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        txtNombre = findViewById(R.id.txtNombre);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtVida = findViewById(R.id.txtVida);
        txtEstado = findViewById(R.id.txtEstado);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        if(getIntent().hasExtra("id")){
            registra = false;
            mostrarActualizar();
        }

        btnRegistrar.setOnClickListener(v -> {
            if(registra){
                capturarDatos();
                reference.child("Bienes").child(bien.getId()).setValue(bien);
            }else{
                capturarDatos();
                reference.child("Bienes").child(id).updateChildren(map);
            }
            startActivity(new Intent(RegistrarActivity.this, MainActivity.class));

        });
    }

    private void mostrarActualizar() {
        id = getIntent().getStringExtra("id");
        String nombre = getIntent().getStringExtra("nombre");
        String codigo = getIntent().getStringExtra("codigo");
        String vida = getIntent().getStringExtra("vida");
        String estado = getIntent().getStringExtra("estado");

        String titulo = getIntent().getStringExtra("btnTitutlo");

        textView.setText(titulo);
        txtNombre.setText(nombre);
        txtCodigo.setText(codigo);
        txtVida.setText(vida);
        txtEstado.setText(estado);
        btnRegistrar.setText(titulo);
    }

    private void capturarDatos(){
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

        if(registra) {
            bien = new Bien(UUID.randomUUID().toString(), nombre, codigo, estado, Integer.parseInt(vida));
        } else {
            map.put("nombre", nombre);
            map.put("codPat", codigo);
            map.put("vida", Integer.parseInt(vida));
            map.put("estado", estado);
        }

    }

    private void inicializarFirebase(Context context){
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}