package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

import com.example.main.model.Produto;
import com.example.main.model.controller.ProdutoController;
import com.example.main.persistence.ProdDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
    private ProdutoController produtoController;

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
        listar.setMovementMethod(new ScrollingMovementMethod());

        produtoController = new ProdutoController(new ProdDao(this));

        inserir.setOnClickListener(click -> acaoinserir());
        excluir.setOnClickListener(click -> acaoexcluir());
        mod.setOnClickListener(click -> acaomod());
        listar.setOnClickListener(click -> acaolistar());
        buscar.setOnClickListener(click -> acaobuscar());
    }
    private Produto montaProduto() {
        if (id.getText().toString().isEmpty() ||
            nome.getText().toString().isEmpty() ||
            valor.getText().toString().isEmpty()) {

            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            return null;
        }
        Produto produto = new Produto();
        produto.setId(Integer.parseInt(id.getText().toString()));
        produto.setNome(nome.getText().toString());
        produto.setValor(Double.parseDouble(valor.getText().toString()));

        return produto;
    }


    private void preencherCampos(Produto produto) {
        id.setText(String.valueOf(produto.getId()));
        nome.setText(produto.getNome());
        valor.setText(String.valueOf(produto.getValor()));
    }


    private void limpaCampos() {
        id.setText("");
        nome.setText("");
        valor.setText("");
    }


    private void acaoinserir() {
        Produto produto = montaProduto();
        if (produto == null) return;
        try {
            if (produtoController.existeId(produto.getId())) {
                Toast.makeText(this, "ID já existe!", Toast.LENGTH_LONG).show();
                return;
            }

            produtoController.inserir(produto);
            Toast.makeText(this, getString(R.string.prodinsert), Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            android.util.Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
        limpaCampos();
    }


    private void acaomod() {
        Produto produto = montaProduto();

        try {
            produtoController.modificar(produto);
            Toast.makeText(this, getString(R.string.prodmod), Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            android.util.Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }

        limpaCampos();
    }


    private void acaoexcluir() {
        Produto produto = montaProduto();

        try {
            produtoController.excluir(produto);
            Toast.makeText(this, getString(R.string.proddelete), Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            android.util.Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }

        limpaCampos();
    }


    private void acaobuscar() {
        Produto produto = new Produto();
        produto.setId(Integer.parseInt(id.getText().toString()));

        try {
            produto = produtoController.buscar(produto);

            if (produto.getNome() != null) {
                preencherCampos(produto);
            } else {
                Toast.makeText(this, getString(R.string.prodbusca), Toast.LENGTH_LONG).show();
                limpaCampos();
            }

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            android.util.Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
        }
    }


    private void acaolistar() {
        try {
            List<Produto> produto = produtoController.listar();
            StringBuffer buffer = new StringBuffer();

            for (Produto p : produto) {
                buffer.append(p.toString());
                buffer.append("\n");
            }

            lista.setText(buffer.toString());

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            android.util.Log.e("SQLException", Objects.requireNonNull(e.getMessage()));
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