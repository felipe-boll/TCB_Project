package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.dao.InstrumentoDAO;

public class InstrumentoController {
  private InstrumentoDAO dao;

  public InstrumentoController(){
    dao = new InstrumentoDAO();
  }

  public void cadastrarIntrumento(Instrumento instrumento){
    if (instrumento.getNome() == null || instrumento.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do intrumento não pode ser vazio");
    }

    dao.salvarInstrumento(instrumento);
  }

  public List<Instrumento> listaInstrumentos(){
    return dao.listarInstrumentos();
  }

  public void atualizarInstrumentos(Instrumento instrumento){
    dao.atualizarInstrumentos(instrumento);
  }

  public void deletarInstrumento(Instrumento instrumento){
    dao.deletarInstrumento(instrumento.getInstrumentoID());
  }

  public Instrumento selectInstrumento(int idInstrumento){
    return dao.selectInstrumento(idInstrumento);
  }
}
