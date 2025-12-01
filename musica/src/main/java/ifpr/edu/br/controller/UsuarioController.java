package ifpr.edu.br.controller;

import java.util.List;

import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.dao.UsuarioDAO;

public class UsuarioController {
  private UsuarioDAO dao;

  public UsuarioController(){
    this.dao = new UsuarioDAO();
  }

  public void cadastrarUsuario(Usuario usuario){
    dao.cadastrarUsuario(usuario);
  }

  public Usuario login(String email, String senha){
    return dao.login(email, senha);
  }

  public void atualizarUsuario(Usuario usuario){
    dao.atualizarUsuario(usuario);
  }

  public void deletarUsuario(Usuario usuario){
    dao.deletarUsuario(usuario.getUsuarioID());
  }

  public List<Usuario> listaUsuarios(){
    return dao.listarUsuarios();
  }
  
}
