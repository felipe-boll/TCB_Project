package ifpr.edu.br.model;

import java.util.ArrayList;

public class Cantor extends Pessoa {
    private ArrayList<Musica> musicas = new ArrayList<>();
    private Banda banda;

    public Cantor(String nome, String cpf, String email, int idade, ArrayList<Musica> musicas, Banda banda){
        super(nome, email, cpf, idade);
        this.banda = banda;
        this.musicas = musicas;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    
}
