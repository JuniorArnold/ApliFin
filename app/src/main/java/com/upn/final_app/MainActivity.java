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

}