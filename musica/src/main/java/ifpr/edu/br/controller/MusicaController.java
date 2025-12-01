package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Cantor;
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
    dao.salvarUsuarioHasMusica(musica);
  }

  public List<Musica> listarMusicas(){
    return dao.listarMusicas();
  }

  public void atualizarMusica(Musica musica, int opcao) {

    switch (opcao) {

        case 1:
            dao.atualizarMusica(musica);
            break;

        case 2:
            dao.atualizarBandasDaMusica(musica);
            break;

        case 3:
            dao.atualizarEstilosDaMusica(musica);
            break;

        case 4:
            dao.atualizarInstrumentosDaMusica(musica);
            break;

        case 5:
            dao.atualizarUsuariosDaMusica(musica);
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

}
