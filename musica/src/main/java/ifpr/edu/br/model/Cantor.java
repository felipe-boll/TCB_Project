package ifpr.edu.br.model;

import java.util.List;

public class Cantor{
    private String nome;
    private String cpf;
    private String email;
    private int idade;
    private List<Musica> musicas;
    private List<Banda> bandas;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    
}
