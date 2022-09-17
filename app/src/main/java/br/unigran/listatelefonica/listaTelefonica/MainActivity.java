package br.unigran.listatelefonica.listaTelefonica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unigran.listatelefonica.R;
import br.unigran.listatelefonica.bancoDados.ContatoDB;
import br.unigran.listatelefonica.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    EditText data;
    ListView listagem;
    List<Contato> dados;
    DBHelper db;
    ContatoDB contatoDB;
    Integer atualiza;
    Integer confirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //banco de dados
        db = new DBHelper(this);
        //mapeia campos da tela
        nome = findViewById(R.id.nameID);
        telefone = findViewById(R.id.phoneID);
        data = findViewById(R.id.dateID);
        listagem = findViewById(R.id.listID);
        dados = new ArrayList(); //aloca lista
        //vincula adapter
        ArrayAdapter adapter = new ArrayAdapter(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(adapter);
        contatoDB = new ContatoDB(db);
        contatoDB.lista(dados);//lista incial
        acoes();
    }



}