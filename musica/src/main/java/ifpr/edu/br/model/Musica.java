package ifpr.edu.br.model;

import java.util.List;

public class Musica {
    private String nome;
    private double dificuldade;
    private String duracao;
    private String letra;
    private int musicaID;
    private List<Instrumento> instrumentos;
    private List<Banda> bandas;
    private List<Estilo> estilos;
    private List<Usuario> usuarios;

    public Musica(){}

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
    public int getMusicaID() {
        return musicaID;
    }

    public void setMusicaID(int musicaID) {
        this.musicaID = musicaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public List<Estilo> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<Estilo> estilos) {
        this.estilos = estilos;
    }

    public double getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(double dificuldade) {
        this.dificuldade = dificuldade;
    }

}
