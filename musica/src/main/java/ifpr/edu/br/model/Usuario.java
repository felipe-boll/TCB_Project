package ifpr.edu.br.model;

public class Usuario extends Pessoa {
    private String objetivo;
    private int usuarioID;

    public Usuario(String nome, String cpf, String email, int idade, String objetivo){
        super(nome, cpf, email, idade);
        this.objetivo = objetivo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
