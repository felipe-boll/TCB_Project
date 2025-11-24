package ifpr.edu.br.model;

public class Pessoa {
    private String nome;
    private String email;
    private int idade;
    private String cpf;
    private int pessoaID;

    public Pessoa(String nome, String email, String cpf, int idade){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }


    public int getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(int pessoaID) {
        this.pessoaID = pessoaID;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
