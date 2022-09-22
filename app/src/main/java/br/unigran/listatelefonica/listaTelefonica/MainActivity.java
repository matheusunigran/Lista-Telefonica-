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
    Integer confirma = null;

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

    private void acoes() {
        confirma = null;
        listagem.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int i, long l) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(view.getContext());
                        mensagem.setTitle("Opções");
                        mensagem.setMessage("Escolha a opção que deseja realizar");
                        mensagem.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                new AlertDialog.Builder(view.getContext())
                                        .setMessage("Deseja realmente remover")
                                        .setPositiveButton("Confirmar",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface,
                                                                        int k) {
                                                        contatoDB.remover(dados.get(i).getId());
                                                        contatoDB.lista(dados);
                                                        ((ArrayAdapter) listagem.getAdapter()
                                                        ).notifyDataSetChanged();
                                                        String msg1 = "Contato removido com sucesso";
                                                        Toast.makeText(getApplicationContext(), msg1, Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                        .setNegativeButton("cancelar", null)
                                        .create().show();
                            }
                        });
                        mensagem.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                atualiza = dados.get(i).getId();
                                nome.setText(dados.get(i).getNome());
                                telefone.setText(dados.get(i).getTelefone().toString());
                                data.setText(dados.get(i).getDatanascimento().toString());

                                contatoDB.atualizar(dados.get(i));
                                contatoDB.lista(dados);

                                confirma = 1;

                            }
                        });
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.show();
                        return false;
                    }
                });
    }

    public boolean verificar() {
        String s1 = nome.getText().toString();
        String s2 = telefone.getText().toString();
        String s3 = data.getText().toString();
        if ((s1.equals(null) || s2.equals(null) || s3.equals(null))
                || (s1.equals("") || s2.equals("") || s3.equals(""))) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void salvar(View view) {
        if (verificar()) {
            Contato contato = new Contato();
            if (atualiza != null) {
                contato.setId(atualiza);

                contatoDB.lista(dados);
                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
            contato.setNome(nome.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setDatanascimento(data.getText().toString());

            if (atualiza != null)
                contatoDB.atualizar(contato);
            else {
                contatoDB.inserir(contato);
                contatoDB.lista(dados);
                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
            contatoDB.lista(dados);
            listagem.invalidateViews();
            limpar();
            atualiza = null;
            confirma = null;
        }
    }

    private void limpar() {
        nome.setText("");
        telefone.setText("");
        data.setText("");
    }

    @Override
    public void onBackPressed() {
        if (confirma != null) {
            limpar();
            String msgCancelar = "Edição cancelada";
            Toast.makeText(getApplicationContext(), msgCancelar, Toast.LENGTH_SHORT).show();
            confirma = null;
        } else {
            super.onBackPressed();
            limpar();
            String msgSair = "Saindo...";
            Toast.makeText(getApplicationContext(), msgSair, Toast.LENGTH_SHORT).show();

        }
    }
}