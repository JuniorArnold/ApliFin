package com.upn.final_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bNavigation;
    EditText txtNombre, txtCodigo, txtVida, txtEstado;
    Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }


    private void asignarReferencias(){
        bNavigation = findViewById(R.id.bNavigation);

        txtNombre = findViewById(R.id.txtNombre);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtVida = findViewById(R.id.txtVida);
        txtEstado = findViewById(R.id.txtEstado);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
            }
        });

       bNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_1){
                    mostrarFragmento(new Fragmento1());
                    item.setChecked(true);

                }
                if (item.getItemId() == R.id.menu_2){
                    mostrarFragmento(new Fragmento2());
                    item.setChecked(true);

                }
                if (item.getItemId() == R.id.menu_3){
                    mostrarFragmento(new Fragmento3());
                    item.setChecked(true);

                }

                return false;
            }
        });
    }
    private void mostrarFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
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