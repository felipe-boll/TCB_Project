package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.dao.BandaDAO;

public class BandaController {
  private BandaDAO dao;

  public BandaController(){
    dao = new BandaDAO();
  }

  public void cadastrarBanda(Banda banda){
    if (banda.getNome() == null || banda.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome da banda não pode ser vazio");
    }

    dao.salvarBanda(banda);

    System.out.printf("Banda Cadastrada com sucesso!\nID da banda: %d\n", banda.getBandaID());
  }

  public List<Banda> listarBandas(){
    return dao.listarBanda();
  }

  public void atualizarNomeBanda(Banda banda){
    dao.atualizarNomeBanda(banda);
  }

  public void atualizarAgenciaBanda(Banda banda){
    dao.atualizarAgenciaBanda(banda);
  }

  public void deletarBanda(Banda banda){
    dao.deletarBanda(banda.getBandaID());
  }

  public Banda buscarPorID(int idBanda){
    return dao.selectBanda(idBanda);
  }

  public void removerCantorDaBanda(int idBanda, int idCantor){
    dao.removerCantorDaBanda(idBanda, idCantor);
  }
}
