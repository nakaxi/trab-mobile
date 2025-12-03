package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

public class ComaActivity extends AppCompatActivity {

    private EditText id;
    private Button buscar;
    private Button inserir;
    private Button excluir;
    private Button mod;
    private Button listar;
    private TextView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = findViewById(R.id.etid);
        buscar = findViewById(R.id.btnbuscar3);
        inserir = findViewById(R.id.btninserir3);
        excluir = findViewById(R.id.btnex3);
        mod = findViewById(R.id.btnmod3);
        listar = findViewById(R.id.btnlistar3);
        lista = findViewById(R.id.tvlista3);
        listar.setMovementMethod(new ScrollingMovementMethod());
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