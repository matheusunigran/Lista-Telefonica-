package br.unigran.listatelefonica.listaTelefonica;

public class Contato {
    private String nome;
    private String telefone;
    private String datanascimento;
    private Integer id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "Nome - " + nome +
                        " | Tel - " + telefone +
                        " | Nasc - " + datanascimento;
    }
}
