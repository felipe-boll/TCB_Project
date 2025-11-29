package ifpr.edu.br.controller;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Pessoa;
import ifpr.edu.br.model.dao.CantorDAO;

public class CantorController {
  private CantorDAO dao;

  public CantorController() {
    dao = new CantorDAO();
  }

  public void cadastrarCantor(Cantor cantor, Banda banda, Pessoa pessoa) {
    if (cantor.getNome() == null || cantor.getNome().trim().isEmpty()) {
      throw new  IllegalArgumentException("O nome do cantor não pode ser vazio.");
    }
    
    dao.salvarCantor(banda, pessoa, cantor);
  }
}
