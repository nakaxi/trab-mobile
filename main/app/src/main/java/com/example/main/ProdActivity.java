package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProdActivity extends AppCompatActivity {

    private EditText id;
    private EditText nome;
    private EditText valor;
    private TextView lista;
    private Button buscar;
    private Button inserir;
    private Button excluir;
    private Button mod;
    private Button listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prod);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        id = findViewById(R.id.etid2);
        nome = findViewById(R.id.etnome2);
        valor = findViewById(R.id.etvalor2);
        lista = findViewById(R.id.tvlista2);
        buscar = findViewById(R.id.btnbuscar2);
        inserir = findViewById(R.id.btninserir2);
        excluir = findViewById(R.id.btnex2);
        mod = findViewById(R.id.btnmod2);
        listar = findViewById(R.id.btnlistar2);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuact1){
            Intent i = new Intent(this, CFuncActivity.class);
            this.startActivity(i);
            this.finish();
            return true;
        }
        if (id == R.id.menuact2) {
            Intent i = new Intent(this, ProdActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.menuact3) {
            Intent i = new Intent(this, ComaActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}