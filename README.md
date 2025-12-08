Sistema para Site de Musica

Sobre o Projeto: Este projeto é um Sistema para um Site de Musica, desenvolvido em Java utilizando o padrão MVC(Model-View-Controller). O sistema permite que usuários comuns consultarem músicas e bandas, enquanto cantores podem gerenciar músicas e bandas.

Funcionalidades Principais:
  Usuário:
    -Cadastrar
    -Fazer login
    -Listar músicas
    -Listar bandas
    -Acessar músicas
    -Acessar bandas
    -Mudar senha
    -Mudar objetivo

  Cantor:
    -Cadastrar
    -Fazer login
    -Listar músicas do cantor
    -Adicionar nova música
    -Editar uma música
    -Editar a banda do cantor
    -Mudar senha

Arquitetura do Projeto:
  /src├── controller │ ├── AgenciaController.java │ ├── BandaController.java │ ├── CantorController.java │ ├── EstiloController.java │ ├── InstrumentoController.java │ ├── MusicaController.java │ └── UsuarioController.java │ ├── model │ ├── Agencia.java │ ├── Banda.java │ ├── Cantor.java │ ├── Estilo.java │ ├── Instrumento.java │ ├── Musica.java │ └── Usuario.java │ └── view └── Main.java

Banco de Dados

Banco de dados MySQL com tabelas: agencia, banda, cantor, estilo, instrumento, musica, usuario, banda_has_musica, estilo_has_musica, musica_has_instrumento, usuario_has_musica

Como Executar o Projeto Pré-requisitos

Java 21+ MySQL 8+ JDBC IDE(InteliJ, VSCode) ou terminal

Passos:
  -Clone o repositório: git clone https://github.com/felipe-boll/TCB_Project.git
  -Importe o projeto na IDE.
  -Configure a conexão MySQL nos controllers.
  -Execute o arquivo: Main.java

Tecnologias Utilizadas:
  -Java 21+
  -MySQL 8+
  -JDBC
  -MVC
  -Programação Orientada a Objetos

Por: Felipe Chaves Boll
Turma: 2° Técnico em Informatica IFPR - Câmpus Cascavel
