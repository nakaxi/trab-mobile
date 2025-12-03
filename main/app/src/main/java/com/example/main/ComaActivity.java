package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.main.model.Comanda;
import com.example.main.model.Funcionario;
import com.example.main.model.controller.ComandaController;
import com.example.main.model.controller.FuncControl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ComaActivity extends AppCompatActivity {

    private EditText id;
    private Button buscar;
    private Button inserir;
    private Button excluir;
    private Button mod;
    private Button listar;
    private TextView lista;
    private ComandaController cc;


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

        inserir.setOnClickListener(click -> acaoinserir());
        excluir.setOnClickListener(click -> acaoexcluir());
        mod.setOnClickListener(click -> acaomod());
        listar.setOnClickListener(click -> acaolistar());
        buscar.setOnClickListener(click -> acaobuscarr());
    }

    private Comanda montacom() {
        if (id.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha o campo!", Toast.LENGTH_LONG).show();
            return null;
        }

        Comanda c = new Comanda();
        c.setId(Integer.parseInt(id.getText().toString()));
        return c;
    }

    private void preenchecampos(Comanda comanda) {
        id.setText(String.valueOf(comanda.getId()));
    }

    private void limpacampos() {
        id.setText("");

    }

    private void acaoinserir() {
        Comanda comanda = montacom();
        if (comanda == null) return;
        try {
            if (cc.existeId(comanda.getId())) {
                Toast.makeText(this, "ID já existe!", Toast.LENGTH_LONG).show();
                return;
            }

            cc.inserir(comanda);
            Toast.makeText(this, getString(R.string.funinsert), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaoexcluir() {
        Comanda comanda = montacom();
        if (comanda == null) {
            Toast.makeText(this, "Preencha corretamente os campos antes de excluir.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            cc.excluir(comanda);
            Toast.makeText(this, getString(R.string.fundelete), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaomod() {
        Comanda comanda = montacom();
        try {
            cc.modificar(comanda);
            Toast.makeText(this, getString(R.string.funmod), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaolistar() {
        try {
            List<Comanda> coms = cc.listar();
            StringBuffer buffer = new StringBuffer();
            for(Comanda c : coms){
                buffer.append(c.toString());
                buffer.append("\n");
            }
            lista.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
    }

    private void acaobuscarr() {
        Comanda comanda = new Comanda();
        comanda.setId(Integer.parseInt(id.getText().toString()));
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