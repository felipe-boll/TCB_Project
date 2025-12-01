package ifpr.edu.br.view;

import java.util.List;
import java.util.Scanner;

import ifpr.edu.br.controller.AgenciaController;
import ifpr.edu.br.controller.BandaController;
import ifpr.edu.br.controller.CantorController;
import ifpr.edu.br.controller.EstiloController;
import ifpr.edu.br.controller.InstrumentoController;
import ifpr.edu.br.controller.MusicaController;
import ifpr.edu.br.controller.UsuarioController;
import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Musica;
import ifpr.edu.br.model.Usuario;

public class Main {

    public static Scanner tecladoScanner = new Scanner(System.in);

    static AgenciaController controllerAgencia;
    static BandaController controllerBanda;
    static CantorController controllerCantor;
    static EstiloController controllerEstilo;
    static InstrumentoController controllerInstrumento;
    static MusicaController controllerMusica;
    static UsuarioController controllerUsuario;

    public static void iniciarControllers(){
        controllerAgencia = new AgenciaController();
        controllerBanda = new BandaController();
        controllerCantor = new CantorController();
        controllerEstilo = new EstiloController();
        controllerInstrumento = new InstrumentoController();
        controllerMusica = new MusicaController();
        controllerUsuario = new UsuarioController();
    }

    public static void limparTela() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
        System.out.print("\033\143");
    }

    public static void limparBuffer(){
        tecladoScanner.nextLine();
    }

    public static void espera() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void menuInicial(){
        limparTela();
        System.out.println("  ____                        _ _       ");
        System.out.println(" / ___|  ___  _   _ _ __   __| | |_   _ ");
        System.out.println(" \\___ \\ / _ \\| | | | '_ \\ / _` | | | | |");
        System.out.println("  ___) | (_) | |_| | | | | (_| | | |_| |");
        System.out.println(" |____/ \\___/ \\__,_|_| |_|\\__,_|_|\\__, |");
        System.out.println("                                 |___/  ");

        System.out.printf("Bem-Vindo(a) \n\nOque deseja?\n\n1.Criar Conta\n2.Fazer Login como usuario\n3.Fazer Login como cantor\n4.Sair\n");
    }

    public static void criarConta(){
        limparBuffer();
        limparTela();
        System.out.printf("====Criar Conta====\n");
        Usuario u = new Usuario();
        System.out.print("Nome: ");
        u.setNome(tecladoScanner.nextLine());
        System.out.print("Email: ");
        u.setEmail(tecladoScanner.nextLine());
        System.out.print("Senha: ");
        u.setSenha(tecladoScanner.nextLine());
        System.out.print("Idade: ");
        u.setIdade(tecladoScanner.nextInt());
        System.out.print("Objetivo: ");
        u.setObjetivo(tecladoScanner.next());

        controllerUsuario.cadastrarUsuario(u);
        limparTela();
        System.out.printf("\nUsuario cadastrado com sucesso\n");
        espera();
        return;
    }

    public static Usuario fazerLogin(){
        limparBuffer();
        limparTela();
        System.out.printf("====Login====\n");
        System.out.print("Email: ");
        String email = tecladoScanner.nextLine();
        System.out.print("Senha: ");
        String senha = tecladoScanner.nextLine();

        Usuario u = controllerUsuario.login(email, senha);
        if (u != null) {
            limparTela();
            System.out.println("Login realizado com sucesso");
            espera();
            return u;
        } else{
            limparTela();
            System.out.println("Falha no login!");
            espera();
            return u;
        }
    }

    public static void menuUsuario(Usuario u){
        limparTela();
        System.out.printf("Bem-vindo(a), %s!", u.getNome());
        espera();
        System.out.printf("\nOque deseja fazer?\n1.Listar Musicas\n2.Listar Bandas\n3.Acessar uma musica\n4.Acessar uma banda\n5.Mudar Senha\n6.Mudar objetivo\n7.Logout\n");
        int opcao = tecladoScanner.nextInt();
        switch (opcao) {
            case 1:
                limparTela();
                for(Musica musica : controllerMusica.listarMusicas()){
                    String bandas = "";
                    String estilos = "";
                    String instrumentos = "";
                    for(int i = 0; i < musica.getBandas().size(); i++){
                        if (i == musica.getBandas().size() - 1) {
                            bandas += musica.getBandas().get(i).getNome();
                        } else{
                            bandas += musica.getBandas().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getEstilos().size(); i++){
                        if (i == musica.getEstilos().size() - 1) {
                            estilos += musica.getEstilos().get(i).getNome();
                        } else{
                            estilos += musica.getEstilos().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getInstrumentos().size(); i++){
                        if (i == musica.getInstrumentos().size() - 1) {
                            instrumentos += musica.getInstrumentos().get(i).getNome();
                        } else{
                            instrumentos += musica.getInstrumentos().get(i).getNome() + ", ";
                        }
                    }
                    System.out.printf("ID: %d | Nome: %s | Duração: %s | Dificuldade: %.1f | Bandas: %s | Estilos: %s | Instrumentos: %s", musica.getMusicaID(), musica.getNome(), musica.getDuracao(), musica.getDificuldade(), bandas, estilos, instrumentos);
                }
                System.out.print("Pressione Enter para continuar...");
                limparBuffer();
                tecladoScanner.nextLine();
                break;
            case 2:
                limparTela();
                for(Banda banda : controllerBanda.listarBandas()){
                    String cantores = "";
                    for(int i = 0; i < banda.getCantores().size(); i++){
                        if (i == banda.getCantores().size() - 1) {
                            cantores += banda.getCantores().get(i).getNome();
                        } else{
                            cantores += banda.getCantores().get(i).getNome() + ", ";
                        }
                    }
                    System.out.printf("ID: %d | Agencia: %d | Nome: %s | Cantores %s", banda.getBandaID(), banda.getAgenciaID(), banda.getNome(), cantores);
                }
                System.out.println("Pressione Enter para continuar ...");
                limparBuffer();
                tecladoScanner.nextLine();
                break;
            case 3:
                limparTela();
                String bandas = "";
                String estilos = "";
                String instrumentos = "";
                for(Musica musica : controllerMusica.listarMusicas()){
                    for(int i = 0; i < musica.getBandas().size(); i++){
                        if (i == musica.getBandas().size() - 1) {
                            bandas += musica.getBandas().get(i).getNome();
                        } else{
                            bandas += musica.getBandas().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getEstilos().size(); i++){
                        if (i == musica.getEstilos().size() - 1) {
                            estilos += musica.getEstilos().get(i).getNome();
                        } else{
                            estilos += musica.getEstilos().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getInstrumentos().size(); i++){
                        if (i == musica.getInstrumentos().size() - 1) {
                            instrumentos += musica.getInstrumentos().get(i).getNome();
                        } else{
                            instrumentos += musica.getInstrumentos().get(i).getNome() + ", ";
                        }
                    }
                    System.out.printf("ID: %d | Nome: %s | Duração: %s | Dificuldade: %.1f | Bandas: %s | Estilos: %s | Instrumentos: %s", musica.getMusicaID(), musica.getNome(), musica.getDuracao(), musica.getDificuldade(), bandas, estilos, instrumentos);
                }
                System.out.println("Digite o ID da musica que deseja acessar: ");
                List<Musica> musicas = controllerMusica.listarMusicas();
                for(Musica musica : musicas){
                    System.out.printf("ID: %d | Nome: %s", musica.getMusicaID(), musica.getNome());
                }
                int idMusica = tecladoScanner.nextInt();
                Musica musicaSelecionada = null;
                for(Musica musica : controllerMusica.listarMusicas()){
                    if (musica.getMusicaID() == idMusica) {
                        musicaSelecionada = musica;
                        break;
                    }
                }
                limparTela();
                System.out.printf("====%s====\n", musicaSelecionada.getNome());
                System.out.printf("Duração: %s\nDificuldade: %.1f\nBandas: %s\nEstilos: %s\n Instrumentos: %s\n\n%s\n\n", musicaSelecionada.getDuracao(), musicaSelecionada.getDificuldade(), bandas, estilos, instrumentos, musicaSelecionada.getLetra());
                System.out.println("Pressione Enter para sair...");
                limparBuffer();
                tecladoScanner.nextLine();
                break;
            case 4: 
                limparTela();
                String cantores = "";
                bandas = "";
                estilos = "";
                instrumentos = "";
                for(Musica musica : controllerMusica.listarMusicas()){
                    for(int i = 0; i < musica.getBandas().size(); i++){
                        if (i == musica.getBandas().size() - 1) {
                            bandas += musica.getBandas().get(i).getNome();
                        } else{
                            bandas += musica.getBandas().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getEstilos().size(); i++){
                        if (i == musica.getEstilos().size() - 1) {
                            estilos += musica.getEstilos().get(i).getNome();
                        } else{
                            estilos += musica.getEstilos().get(i).getNome() + ", ";
                        }
                    }
                    for(int i = 0; i < musica.getInstrumentos().size(); i++){
                        if (i == musica.getInstrumentos().size() - 1) {
                            instrumentos += musica.getInstrumentos().get(i).getNome();
                        } else{
                            instrumentos += musica.getInstrumentos().get(i).getNome() + ", ";
                        }
                    }
                }
                for(Banda banda : controllerBanda.listarBandas()){
                    for(int i = 0; i < banda.getCantores().size(); i++){
                        if (i == banda.getCantores().size() - 1) {
                            cantores += banda.getCantores().get(i).getNome();
                        } else{
                            cantores += banda.getCantores().get(i).getNome() + ", ";
                        }
                    }
                    System.out.printf("ID: %d | Agencia: %d | Nome: %s | Cantores %s", banda.getBandaID(), banda.getAgenciaID(), banda.getNome(), cantores);
                }
                System.out.println("Digite o ID da banda que deseja acesar: ");
                List<Banda> bandass = controllerBanda.listarBandas();
                for(Banda banda : bandass){
                    System.out.printf("ID: %d | Nome: %s", banda.getBandaID(), banda.getNome());
                }
                int idBanda = tecladoScanner.nextInt();
                Banda bandaSelecionada = null;
                for(Banda banda : controllerBanda.listarBandas()){
                    if (banda.getBandaID() == idBanda) {
                        bandaSelecionada = banda;
                        break;
                    }
                }
                limparTela();
                System.out.printf("====%s====", bandaSelecionada.getNome());
                System.out.printf("Agencia: %d\nIntegrantes: %s\n", bandaSelecionada.getAgenciaID(), cantores);
                System.out.println("Digite o ID da musica que deseja acessar: ");
                List<Musica> musicass = controllerMusica.listarMusicas();
                for(Musica musica : musicass){
                    System.out.printf("ID: %d | Nome: %s", musica.getMusicaID(), musica.getNome());
                }
                idMusica = tecladoScanner.nextInt();
                musicaSelecionada = null;
                for(Musica musica : controllerMusica.listarMusicas()){
                    if (musica.getMusicaID() == idMusica) {
                        musicaSelecionada = musica;
                        break;
                    }
                }
                limparTela();
                System.out.printf("====%s====", musicaSelecionada.getNome());
                System.out.printf("Duração: %s\nDificuldade: %.1f\nBandas: %s\nEstilos: %s\n Instrumentos: %s\n\n%s\n\n", musicaSelecionada.getDuracao(), musicaSelecionada.getDificuldade(), bandas, estilos, instrumentos, musicaSelecionada.getLetra());
                System.out.println("Pressione Enter para sair...");
                limparBuffer();
                tecladoScanner.nextInt();
                break;
            case 5:
                limparTela();
                System.out.print("Digite sua nova senha: ");
                limparBuffer();
                String novaSenha = tecladoScanner.next();
                u.setSenha(novaSenha);
                controllerUsuario.atualizarUsuario(u);
                limparTela();
                System.out.println("Senha atualizada com sucesso");
                espera();
                break;
            case 6:
                limparTela();
                System.out.printf("Digite seu novo objetivo: ");
                limparBuffer();
                String novoObjetivo = tecladoScanner.next();
                u.setObjetivo(novoObjetivo);
                controllerUsuario.atualizarUsuario(u);
                limparTela();
                System.out.println("Objetivo atualizado com sucesso");
                espera();
                break;
            case 7:
                limparTela();
                System.out.println("Fazendo logout");
                espera();
                return;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        iniciarControllers();

        while (true) {
            menuInicial();
            int opcao = tecladoScanner.nextInt();
            if (opcao == 1) {
                criarConta();
                limparBuffer();
            } else if (opcao == 2) {
                Usuario u = fazerLogin();
                if (u != null) {
                    menuUsuario(u);
                    limparBuffer();
                }
            }
        }
    }
}