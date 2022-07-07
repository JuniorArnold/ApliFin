package com.upn.final_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias(){
        bNavigation = findViewById(R.id.bNavigation);

       bNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_1){
                    mostrarFragmento(new Fragmento2());
                    item.setChecked(true);

                }
                if (item.getItemId() == R.id.menu_2){
                    Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                    startActivity(intent);
                    //mostrarFragmento(new Fragmento1());
                    item.setChecked(true);

                }
                if (item.getItemId() == R.id.menu_3){
                    /*mostrarFragmento(new Fragmento3());
                    item.setChecked(true);
                    */
                    mostrarAlerta();
                }

                return false;
            }
        });
    }
    private void mostrarFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Escoger una ubicación");
        alerta.setNegativeButton("Casa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                intent.putExtra("titulo", "Casa");
                intent.putExtra("latitud", "-7.176972");
                intent.putExtra("longitud", "-76.725596");
                startActivity(intent);
            }
        });
        alerta.setPositiveButton("UPN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                intent.putExtra("titulo", "UPN");
                intent.putExtra("latitud", "-12.058463");
                intent.putExtra("longitud", "-77.058496");
                startActivity(intent);
            }
        });
        alerta.create().show();
    }

}