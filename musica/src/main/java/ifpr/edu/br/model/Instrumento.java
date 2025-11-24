package ifpr.edu.br.model;

public class Instrumento {
    private String nome;
    private String descricao;
    private int instrumentoID;

    public int getInstrumentoID() {
        return instrumentoID;
    }

    public void setInstrumentoID(int instrumentoID) {
        this.instrumentoID = instrumentoID;
    }

    public Instrumento(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
