package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.dao.EstiloDAO;

public class EstiloController {
  private EstiloDAO dao;

  public EstiloController(){
    dao = new EstiloDAO();
  }

  public void cadastrarEstilo(Estilo estilo){
    if(estilo.getNome() == null || estilo.getNome().trim().isEmpty()){
      throw new IllegalArgumentException("O nome do estilo não pode ser vazio");
    }

    dao.salvarEstilo(estilo);
  }

  public List<Estilo> listarEstilos(){
    return dao.listarEstilos();
  }

  public void atualizarEstilos(Estilo estilo){
    dao.atualizarEstilos(estilo);
  }

  public void deletarEstilo(Estilo estilo){
    dao.deletarEstilo(estilo.getEstiloID());
  }

  public Estilo selectEstilo(int idEstilo){
    return dao.selectEstilo(idEstilo);
  }
}
