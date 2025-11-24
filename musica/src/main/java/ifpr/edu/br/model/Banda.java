package ifpr.edu.br.model;

import java.util.ArrayList;

public class Banda {
    private String nome;
    private ArrayList<Cantor> cantores = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();
    private Agencia agencia;
    private int bandaID;

    public int getBandaID() {
        return bandaID;
    }

    public void setBandaID(int bandaID) {
        this.bandaID = bandaID;
    }

    public Banda(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cantor> getCantores() {
        return cantores;
    }

    public void setCantores(ArrayList<Cantor> cantores) {
        this.cantores = cantores;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
