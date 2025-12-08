package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;
import ifpr.edu.br.model.dao.MusicaDAO;

public class MusicaController {
  private MusicaDAO dao;

  public MusicaController(){
    dao = new MusicaDAO();
  }

  public void cadastrarMusica(Cantor cantor, Musica musica){
    if (musica.getNome() == null || musica.getNome().trim().isEmpty()) {
      throw new IllegalArgumentException("O nome da musica não pode estar vazio");
    }

    if (musica.getLetra() == null || musica.getLetra().trim().isEmpty()) {
      throw new IllegalArgumentException("A letra da musica não pode estar vazia");
    }
    dao.salvarMusica(cantor);

    dao.salvarBandaHasMusica(musica);
    dao.salvarEstiloHasMusica(musica);
    dao.salvarInstrumentoHasMusica(musica);
    //dao.salvarUsuarioHasMusica(musica);
  }

  public List<Musica> listarMusicas(){
    return dao.listarMusicas();
  }

  public void atualizarMusica(Musica musica, int opcao) {

    switch (opcao) {

        case 1:
            dao.atualizarMusicaNome(musica);
            break;
        case 2:
            dao.atualizarMusicaDificuldade(musica);
            break;
        case 3:
            dao.atualizarMusicaDuração(musica);
            break;
        case 4:
            dao.atualizarMusicaLetra(musica);
            break;
        default:
            System.out.println("Opção inválida.");
            break;
    }
  }

  public void deletarMusica(Musica musica){
    dao.deletarMusica(musica.getMusicaID());
  }

  public List<Musica> listarMusicasPorBanda(int idBanda) {
    return dao.listarMusicasPorBanda(idBanda);
  }

  public List<Banda> listarBandasPorMusica(int idMusica){
    return dao.listarBandasPorMusica(idMusica);
  }

  public List<Instrumento> listarInstrumentosPorMusica(int idMusica){
    return dao.listarInstrumentosPorMusica(idMusica);
  }

  public List<Estilo> listarEstilosPorMusica(int idMusica){
    return dao.listarEstilosPorMusica(idMusica);
  }

  public Musica selectMusica(int idMusica){
    return dao.selectMusica(idMusica);
  }

  public void adicionarBandaNaMusica(int idBanda, int idMusica){
    dao.adicionarBandaNaMusica(idBanda, idMusica);
  }

  public void deletarBandaDaMusica(int idBanda, int idMusica){
    dao.deletarBandaDaMusica(idBanda, idMusica);
  }

  public void adicionarEstiloNaMusica(int idEstilo, int idBanda){
    dao.adicionarEstiloNaMusica(idEstilo, idBanda);
  }

  public void deletarEstiloDaMusica(int idEstilo, int idBanda){
    dao.deletarEstiloDaMusica(idEstilo, idBanda);
  }

  public void adicionarInstrumentoNaMusica(int idInstrumento, int idBanda){
    dao.adicionarInstrumentoNaMusica(idInstrumento, idBanda);
  }

  public void deletarInstrumentoDaMusica(int idInstrumento, int idBanda){
    dao.deletarInstrumentoDaMusica(idInstrumento, idBanda);
  }

}
