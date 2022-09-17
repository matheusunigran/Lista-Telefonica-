package br.unigran.listatelefonica.listaTelefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.unigran.listatelefonica.R;
import br.unigran.listatelefonica.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nome;
        EditText telefone;
        ListView listagem;
        List<Contato> dados;
        DBHelper db;

    }
}