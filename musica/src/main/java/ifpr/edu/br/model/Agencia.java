package ifpr.edu.br.model;

import java.util.ArrayList;

public class Agencia {
    private String nome;
    private String email;
    private String cnpj;
    private ArrayList<Banda> bandas = new ArrayList<>();

    public Agencia(){}

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ArrayList<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }
}
