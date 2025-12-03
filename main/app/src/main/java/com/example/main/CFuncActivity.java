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

import com.example.main.model.Funcionario;
import com.example.main.model.controller.FuncControl;
import com.example.main.persistence.FuncDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CFuncActivity extends AppCompatActivity {

    private EditText id;
    private EditText nome;
    private EditText sala;
    private EditText tele;
    private EditText cargon;
    private TextView cargo;
    private TextView lista;
    private Button buscar;
    private Button inserir;
    private Button excluir;
    private Button mod;
    private Button listar;
    private FuncControl fc;
    private int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cfunc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = findViewById(R.id.etid);
        nome = findViewById(R.id.etnome);
        sala = findViewById(R.id.etsala);
        tele = findViewById(R.id.ettele);
        cargon = findViewById(R.id.etcargon);
        lista = findViewById(R.id.tvlista);
        buscar = findViewById(R.id.btnbuscar);
        inserir = findViewById(R.id.btninserir);
        excluir = findViewById(R.id.btnex);
        mod = findViewById(R.id.btnmod);
        listar = findViewById(R.id.btnlistar);
        listar.setMovementMethod(new ScrollingMovementMethod());

        try {
            fc = new FuncControl(new FuncDao(this));
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }

        inserir.setOnClickListener(click -> acaoinserir());
        excluir.setOnClickListener(click -> acaoexcluir());
        mod.setOnClickListener(click -> acaomod());
        listar.setOnClickListener(click -> acaolistar());
        buscar.setOnClickListener(click -> acaobuscarr());
    }

    private Funcionario montafun() {
        if (id.getText().toString().isEmpty() ||
                nome.getText().toString().isEmpty() ||
                sala.getText().toString().isEmpty() ||
                tele.getText().toString().isEmpty() ||
                cargon.getText().toString().isEmpty()) {

            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            return null;
        }

        Funcionario f = new Funcionario();

        f.setid(Integer.parseInt(id.getText().toString()));
        f.setNome(nome.getText().toString());
        f.setSalario(Double.parseDouble(sala.getText().toString()));
        f.setTelefone(tele.getText().toString());
        t =(Integer.parseInt(cargon.getText().toString()));
        switch (t) {
            case 1: f.setCargo("Gerente"); break;
            case 2: f.setCargo("Caixa"); break;
            case 3: f.setCargo("Atendente"); break;
            default:
                Toast.makeText(this, "Cargo inválido! Use 1, 2 ou 3.", Toast.LENGTH_SHORT).show();
                return null;
        }
        return f;
    }

    private void preenchecampos(Funcionario funcionario) {
        id.setText(String.valueOf(funcionario.getid()));
        nome.setText(funcionario.getNome());
        sala.setText(String.valueOf(funcionario.getSalario()));
        tele.setText(funcionario.getTelefone());

        String cargo = funcionario.getCargo();

        if (cargo.equalsIgnoreCase("Gerente")) {
            cargon.setText("1");
        } else if (cargo.equalsIgnoreCase("Caixa")) {
            cargon.setText("2");
        } else if (cargo.equalsIgnoreCase("Atendente")) {
            cargon.setText("3");
        } else {
            cargon.setText(""); // cargo inválido
        }
    }

    private void limpacampos() {
        id.setText("");
        nome.setText("");
        sala.setText("");
        tele.setText("");
        cargon.setText("");
    }

    private void acaoinserir() {
        Funcionario funcionario = montafun();
        if (funcionario == null) return;
        try {
            if (fc.existeId(funcionario.getid())) {
                Toast.makeText(this, "ID já existe!", Toast.LENGTH_LONG).show();
                return;
            }

            fc.inserir(funcionario);
            Toast.makeText(this, getString(R.string.funinsert), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaoexcluir() {
        Funcionario funcionario = montafun();
        if (funcionario == null) {
            Toast.makeText(this, "Preencha corretamente os campos antes de excluir.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            fc.excluir(funcionario);
            Toast.makeText(this, getString(R.string.fundelete), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaomod() {
        Funcionario funcionario = montafun();
        try {
            fc.modificar(funcionario);
            Toast.makeText(this, getString(R.string.funmod), Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpacampos();
    }

    private void acaolistar() {
        try {
            List<Funcionario> funs = fc.listar();
            StringBuffer buffer = new StringBuffer();
            for(Funcionario f : funs){
                buffer.append(f.toString());
                buffer.append("\n");
            }
            lista.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
    }

    private void acaobuscarr() {
        Funcionario funcionario = new Funcionario();
        funcionario.setid(Integer.parseInt(id.getText().toString()));

        try {
            funcionario = fc.buscar(funcionario);
            if (funcionario.getNome() != null) {
                preenchecampos(funcionario);
            } else {
                Toast.makeText(this, getString(R.string.funbusca), Toast.LENGTH_LONG).show();
                limpacampos();
            }
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
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