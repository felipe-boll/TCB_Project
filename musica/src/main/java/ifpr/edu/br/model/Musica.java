package ifpr.edu.br.model;

import java.sql.Time;
import java.util.ArrayList;

public class Musica {
    private String nome;
    private double dificuldade;
    private Time duracao;
    private String letra;
    private ArrayList<Instrumento> instrumentos = new ArrayList<>();
    private ArrayList<Banda> bandas = new ArrayList<>();
    private ArrayList<Estilo> estilos = new ArrayList<>();

    public Musica(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public ArrayList<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public ArrayList<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }

    public ArrayList<Estilo> getEstilos() {
        return estilos;
    }

    public void setEstilos(ArrayList<Estilo> estilos) {
        this.estilos = estilos;
    }

    public double getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(double dificuldade) {
        this.dificuldade = dificuldade;
    }

}
