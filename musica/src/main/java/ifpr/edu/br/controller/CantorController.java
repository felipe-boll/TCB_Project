package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.dao.CantorDAO;

public class CantorController {
  private CantorDAO dao;

  public CantorController() {
    dao = new CantorDAO();
  }

  public void cadastrarCantor(Cantor cantor, int idBanda) {
    if (cantor.getNome() == null || cantor.getNome().trim().isEmpty()) {
      throw new  IllegalArgumentException("O nome do cantor não pode ser vazio.");
    }
    
    dao.salvarCantor(cantor, idBanda);
  }

  public List<Cantor> listarCantores(){
    return dao.listarCantores();
  }

  public List<Cantor> listarCantoresPorBanda(int idBanda){
    return dao.listarCantoresPorBanda(idBanda);
  }

  public void atualizarCantorSenha(Cantor cantor){
    dao.atualizarCantorSenha(cantor);
  }

  public void atualizarCantorBanda(Cantor cantor, int idBanda){
    dao.atualizarCantorBanda(cantor, idBanda);
  }

  public Cantor login(int idCantor, String senha){
    return dao.login(idCantor, senha);
  }

  public void deletarCantor(Cantor cantor){
    dao.deletarCantor(cantor.getCantorID());
  }
}
