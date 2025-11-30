package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Agencia;
import ifpr.edu.br.model.dao.AgenciaDAO;

public class AgenciaController {
  private AgenciaDAO dao;

  public AgenciaController(){
    dao = new AgenciaDAO();
  }

  public void cadastrarAgencia(Agencia agencia){
    if (agencia.getNome() == null || agencia.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome da agencia é obrigatorio");
    }
    dao.salvarAgencia(agencia);
  }

  public List<Agencia> listarAgencias(){
    return dao.listarAgencias();
  }

  public void atualizarAgencia(Agencia agencia){
    dao.atualizarAgencia(agencia);
  }

  public void deletarAgencia(Agencia agencia){
    dao.deletarAgencia(agencia.getAgenciaID());
  }
}
