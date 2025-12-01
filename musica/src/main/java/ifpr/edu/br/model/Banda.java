package ifpr.edu.br.model;

import java.util.ArrayList;
import java.util.List;

public class Banda {
    private String nome;
    private List<Cantor> cantores = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();
    private int agenciaID;
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

    public List<Cantor> getCantores() {
        return cantores;
    }

    public void setCantores(List<Cantor> cantores) {
        this.cantores = cantores;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public int getAgenciaID() {
        return agenciaID;
    }

    public void setAgenciaID(int agenciaID) {
        this.agenciaID = agenciaID;
    }
}
