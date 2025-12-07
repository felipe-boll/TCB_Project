package ifpr.edu.br.model;

import java.util.List;

public class Cantor{
    private String nome;
    private String email;
    private int idade;
    private String senha;
    // Isso esta sendo usado Felipe;
    private List<Musica> musicas;
    private Banda banda;
    private int cantorID;

    public int getCantorID() {
        return cantorID;
    }

    public void setCantorID(int cantorID) {
        this.cantorID = cantorID;
    }

    public Cantor(){

    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
