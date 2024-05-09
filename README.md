

<img src="https://github.com/brunnotm23/database-banco-javer/assets/99679969/695b8917-3a06-43dc-931a-d7c0c988904e.png" width="250" height="250">

# Database H2 do Banco Javer
Database Spring Boot H2 do Banco **Javer**

É responsável por realizar as operações CRUD (Create, Read, Update e Delete) na base de dados local

## Requisitos:
- JDK 17 (recomendado: versão 17 como "JAVA_HOME" nas variáveis de ambiente do seu sitema)
- Maven (usar compilador com a versão 17 do Java)

## Execução:
### Passo 1:
Fazer o download .zip do repositório ou cloná-lo utilizando o comando `git clone https://github.com/brunnotm23/database-banco-javer.git` no terminal
### Passo 2:
Executar a classe DatabaseApplication do diretório `src/main/java/io/github/brunnotoscano/DatabaseApplication.java`

OU

Utilizar os seguintes comandos em sequência: `cd database-banco-javer` e `mvn spring-boot:run`

## Onde Acessar:
Os endpoints estão configurados no diretório http://localhost:8080/api/clientes

Referir à documentação para obter mais detalhes sobre as operações e sintaxe de cada endpoint

## Operações
### GET
- **Busca de cliente por ID**
- **Busca de cliente por propriedades**
### POST
- **Criação de clientes na base de dados**
### PUT
- **Atualização de clientes existentes**
### DELETE
- **Remoção de clientes da base de dados**

## Documentação
A documentação dos métodos, retornos e requisições esperadas pode ser acessada pelo endereço http://localhost:8080/documentacao.html (só funcionará com a aplicação em execução)
