package ifpr.edu.br.view;

import java.util.ArrayList;
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
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Instrumento;
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
        System.out.printf("Gostaria de criar um conta como:\n1.Usuario\n2.Cantor\n");
        int opcao = tecladoScanner.nextInt();
        if (opcao == 1) {
            limparTela();
            limparBuffer();
            System.out.printf("====Criar Conta como Usuario====\n");
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
            limparBuffer();
            u.setObjetivo(tecladoScanner.nextLine());

            controllerUsuario.cadastrarUsuario(u);
            limparTela();
            System.out.printf("\nUsuario cadastrado com sucesso\n");
            espera();
            return;
        } else{
            limparTela();
            limparBuffer();
            System.out.printf("====Criar Conta como Cantor====\n");
            Cantor c = new Cantor();
            System.out.print("Nome: ");
            c.setNome(tecladoScanner.nextLine());
            System.out.print("Email: ");
            c.setEmail(tecladoScanner.nextLine());
            System.out.print("Idade: ");
            c.setIdade(tecladoScanner.nextInt());
            System.out.print("Senha: ");
            c.setSenha(tecladoScanner.nextLine());
            System.out.print("Ja possui uma banda cadastrada(S/N): ");
            String resposta = tecladoScanner.next().toLowerCase();
            int idBanda;
            if (resposta.equals("s")) {
                System.out.print("ID Banda: ");
                idBanda = tecladoScanner.nextInt();

                controllerCantor.cadastrarCantor(c, idBanda);
                limparTela();
            } else{
                espera();
                limparTela();
                limparBuffer();
                System.out.printf("====Então vamos cadastrar uma Banda\n");
                Banda b = new Banda();
                System.out.print("Nome: ");
                b.setNome(tecladoScanner.nextLine());
                System.out.print("ID da Agencia: ");
                b.setAgenciaID(tecladoScanner.nextInt());

                controllerBanda.cadastrarBanda(b);

                System.out.print("Pressione Enter para continuar...");
                limparBuffer();
                tecladoScanner.nextLine();
                limparTela();
                
                idBanda = b.getBandaID();

                controllerCantor.cadastrarCantor(c, idBanda);
                limparTela();
            }
        }
    }

    public static Usuario fazerLoginUsuario(){
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
        while (true) {
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
                limparBuffer();
                System.out.print("Pressione Enter para continuar...\n");
                tecladoScanner.nextLine();
                limparBuffer();
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
                    System.out.printf("ID: %d | Agencia: %d | Nome: %s | Cantores: %s\n", banda.getBandaID(), banda.getAgenciaID(), banda.getNome(), cantores);
                }
                limparBuffer();
                System.out.println("Pressione Enter para continuar ...");
                tecladoScanner.nextLine();
                limparBuffer();
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
                    System.out.printf("ID: %d | Agencia: %d | Nome: %s | Cantores: %s\n", banda.getBandaID(), banda.getAgenciaID(), banda.getNome(), cantores);
                    cantores = "";
                }
                System.out.println("Digite o ID da banda que deseja acesar: ");
                List<Banda> bandass = controllerBanda.listarBandas();
                for(Banda banda : bandass){
                    System.out.printf("ID: %d | Nome: %s\n", banda.getBandaID(), banda.getNome());
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
                System.out.printf("====%s====\n", bandaSelecionada.getNome());
                for(int i = 0; i < bandaSelecionada.getCantores().size(); i++){
                    if (i == bandaSelecionada.getCantores().size() - 1) {
                        cantores += bandaSelecionada.getCantores().get(i).getNome();
                    } else{
                        cantores += bandaSelecionada.getCantores().get(i).getNome() + ", ";
                    }
                }
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
                u.setSenha(tecladoScanner.nextLine());
                controllerUsuario.atualizarSenha(u);
                limparTela();
                System.out.println("Senha atualizada com sucesso");
                espera();
                break;
            case 6:
                limparTela();
                System.out.printf("Digite seu novo objetivo: ");
                limparBuffer();
                String novoObjetivo = tecladoScanner.nextLine();
                u.setObjetivo(novoObjetivo);
                controllerUsuario.atualizarObjetivo(u);
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
        limparBuffer();
        }
    }

    public static Cantor fazerLoginCantor(){
        limparBuffer();
        limparTela();
        System.out.printf("====Login====\n");
        System.out.print("ID cantor: ");
        int idcantor = tecladoScanner.nextInt();
        System.out.print("Senha: ");
        String senha = tecladoScanner.nextLine();

        Cantor c = controllerCantor.login(idcantor, senha);
        if (c != null) {
            limparTela();
            System.out.println("Login realizado com sucesso");
            espera();
            return c;
        } else{
            limparTela();
            System.out.println("Falha no login!");
            espera();
            return c;
        }
    }

    public static void menuCantor(Cantor c, Musica m, Banda b){
        while (true) {
            limparTela();
            System.out.printf("Bem-vindo(a), %s!", c.getNome());
            espera();
            System.out.printf("\nOque deseja fazer?\n1.Listar suas Musicas\n2.Adicionar uma musica nova\n3.Alterar uma musica\n4.Editar banda\n5.Mudar sua senha\n6.Logout");
            int opcao = tecladoScanner.nextInt();
            switch (opcao) {
                case 1:
                    limparTela();
                    int idbanda = c.getBanda().getBandaID();
                    List<Musica> musicas = controllerMusica.listarMusicasPorBanda(idbanda);

                    if (musicas.isEmpty()) {
                        System.out.println("Essa banda não possui musicas cadastradas");
                        return;
                    }
                    System.out.printf("====Musicas da Banda %s====", c.getBanda().getNome());
                    for(Musica musica : musicas){
                        System.out.printf("%d - %s\n", musica.getMusicaID() ,musica.getNome());
                    }
                case 2:
                    limparTela();
                    System.out.printf("====Nova Musica====\n");
                    System.out.print("Nome: ");
                    m.setNome(tecladoScanner.nextLine());
                    System.out.print("Duração: ");
                    m.setDuracao(tecladoScanner.nextLine());
                    System.out.print("Dificuldade (0-5): ");
                    m.setDificuldade(tecladoScanner.nextDouble());
                    System.out.print("Letra: ");
                    m.setLetra(tecladoScanner.nextLine());
                    limparTela();
                    System.out.printf("====Vamos adicionar os estilos====\n");
                    List<Estilo> estilos = new ArrayList<>();
                    for(Estilo estilo : controllerEstilo.listarEstilos()){
                        System.out.printf("ID: %d | Nome: %s\n", estilo.getEstiloID(), estilo.getNome());
                    }
                    while (true) {
                        System.out.print("ID do Estilo: ");
                        int idEstilo = Integer.parseInt(tecladoScanner.nextLine());

                        Estilo e = controllerEstilo.selectEstilo(idEstilo);

                        if (e == null) {
                            System.out.println("Estilo não encontrado! Tente novamente");
                            continue;
                        }

                        estilos.add(e);
                        System.out.printf("Estilo adicionado: %s", e.getNome());

                        System.out.print("Deseja adicionar outro estilo? (s/n): ");
                        String resposta = tecladoScanner.nextLine().toLowerCase();

                        if (resposta.equals("n")) {
                            break;
                        }
                    }
                    m.setEstilos(estilos);
                    System.out.printf("====Vamos adicionar as bandas====\n");
                    List<Banda> bandas = new ArrayList<>();
                    for(Banda banda : controllerBanda.listarBandas()){
                        String cantores = "";
                        for(int i = 0; i < banda.getCantores().size(); i++){
                            if (i == banda.getCantores().size() - 1) {
                                cantores += banda.getCantores().get(i).getNome();
                            } else{
                                cantores += banda.getCantores().get(i).getNome() + ", ";
                            }
                        }
                        System.out.printf("ID: %d | Agencia: %d | Nome: %s | Cantores: %s\n", banda.getBandaID(), banda.getAgenciaID(), banda.getNome(), cantores);
                    }
                    while (true) {
                        System.out.print("ID da Banda: ");
                        int idBanda = Integer.parseInt(tecladoScanner.nextLine());

                        b = controllerBanda.buscarPorID(idBanda);

                        if (b == null) {
                            System.out.println("Banda não encontrada, tente novamente");
                            continue;
                        }

                        bandas.add(b);
                        System.out.printf("Banda adicionada: %s", b.getNome());

                        System.out.print("Deseja adicionar outra banda? (s/n): ");
                        String resposta = tecladoScanner.nextLine().toLowerCase();

                        if (resposta.equals("n")) {
                            break;
                        }
                    }
                    m.setBandas(bandas);
                    System.out.printf("====Vamos adicionar os instrumentos====\n");
                    List<Instrumento> instrumentos = new ArrayList<>();
                    for(Instrumento instrumento : controllerInstrumento.listaInstrumentos()){
                        System.out.printf("ID: %d | Nome: %s", instrumento.getInstrumentoID(), instrumento.getNome());
                    }
                    while(true){
                        System.out.print("ID do Instrumento: ");
                        int idInstrumento = Integer.parseInt(tecladoScanner.nextLine());

                        Instrumento i = controllerInstrumento.selectInstrumento(idInstrumento);

                        if (i == null) {
                            System.out.print("Instrumento não encontrado! Tente novamente");
                            continue;
                        }

                        instrumentos.add(i);
                        System.out.printf("Instrumento adicionado: %s", i.getNome());

                        System.out.print("Deseja adicionar outro instrumento? (s/n): ");
                        String resposta = tecladoScanner.nextLine().toLowerCase();

                        if (resposta.equals("n")) {
                            break;
                        }
                    }
                    m.setInstrumentos(instrumentos);

                    controllerMusica.cadastrarMusica(c, m);
                case 3:
                    limparTela();
                    // ID da banda do cantor
                    int idbandaa = c.getBanda().getBandaID();
                    // Musicas da banda do cantor
                    List<Musica> musicass = controllerMusica.listarMusicasPorBanda(idbandaa);

                    if (musicass.isEmpty()) {
                        System.out.println("Essa banda não possui musicas cadastradas");
                        return;
                    }
                    System.out.printf("====Musicas da Banda %s====", c.getBanda().getNome());
                    for(Musica musica : musicass){
                        System.out.printf("%d - %s\n", musica.getMusicaID() ,musica.getNome());
                    }
                    System.out.println("Insira o ID da musica que deseja alterar");
                    int idMusica = Integer.parseInt(tecladoScanner.nextLine());

                    m = controllerMusica.selectMusica(idMusica);

                    if (m == null) {
                        System.out.print("Musica não encontrada! Tente novamente");
                        continue;
                    }

                    while (true) {
                        limparTela();
                        System.out.printf("Oque deseja modificar na musica?\n1.Nome\n2.Dificuldade\n3.Duração\n4.Letra\n5.Bandas\n6.Estilos\n7.Instrumentos\n8.Voltar");
                        limparBuffer();
                        int alternativa = Integer.parseInt(tecladoScanner.nextLine());
                        switch (alternativa) {
                            case 1:
                                limparTela();
                                System.out.print("Informe o novo nome: ");
                                limparBuffer();
                                m.setNome(tecladoScanner.nextLine());
                                controllerMusica.atualizarMusica(m, alternativa);
                                System.out.println("Nome da musica atualizada");
                                espera();
                                break;
                            case 2:
                                limparTela();
                                System.out.print("Informe a nova dificuldade: ");
                                limparBuffer();
                                m.setDificuldade(tecladoScanner.nextDouble());
                                controllerMusica.atualizarMusica(m, alternativa);
                                System.out.println("Dificuldade da musica atualizada");
                                espera();
                                break;
                            case 3:
                                limparTela();
                                System.out.print("Informe a nova duração: ");
                                limparBuffer();
                                m.setDuracao(tecladoScanner.nextLine());
                                controllerMusica.atualizarMusica(m, alternativa);
                                System.out.println("Duração da musica atualizada");
                                espera();
                                break;
                            case 4:
                                limparTela();
                                System.out.print("Informe a nova letra: ");
                                limparBuffer();
                                m.setLetra(tecladoScanner.nextLine());
                                controllerMusica.atualizarMusica(m, alternativa);
                                System.out.println("Letra da musica atualizada");
                                espera();
                                break;
                            case 5:
                                limparTela();
                                System.out.print("Oque deseja?\n1.Adicionar uma banda\n2.Remover uma banda");
                                int resposta = Integer.parseInt(tecladoScanner.nextLine());
                                if (resposta == 1) {
                                    limparTela();
                                    for(Bandas bandas : controllerMusica.lis)
                                    System.out.print("Digite o ID da banda que deseja adicionar: ");
                                    int idBandaAdd = Integer.parseInt(tecladoScanner.nextLine());
                                    controllerMusica.adicionarBandaNaMusica(idBandaAdd, idMusica);
                                    System.out.println("Banda adicionada com secesso");
                                } else{
                                    limparTela();
                                    System.out.print("Digite o ID da banda que deseja remover");
                                    int idBandaRemov = Integer.parseInt(tecladoScanner.nextLine());
                                    controllerMusica.deletarBandaDaMusica(idBandaRemov, idMusica);
                                    System.out.println("Banda removida com sucesso");
                                }
                                break;
                            default:
                                break;
                        }
                    }
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        iniciarControllers();

        while (true) {
            menuInicial();
            int opcao = tecladoScanner.nextInt();
            
            /*if (opcao == 1) {
                criarConta();
                limparBuffer();
            } else if (opcao == 2) {
                Usuario u = fazerLogin();
                if (u != null) {
                    menuUsuario(u);
                }
            }*/

            switch (opcao) {
                case 1:
                    criarConta();
                    limparTela();
                    break;
                case 2:
                    Usuario u = fazerLoginUsuario();
                    if (u != null) {
                        menuUsuario(u);
                    }
                    break;
                case 3:
                    Cantor c = fazerLoginCantor();
                    if (c != null) {
                        menuCantor(c);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}