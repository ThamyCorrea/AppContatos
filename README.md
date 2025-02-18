# AppContatos

Este projeto é uma API REST desenvolvida com Spring Boot, que permite o cadastro, edição, leitura e remoção de pessoas e seus contatos. Utiliza Swagger para documentação, Spring Data JPA para manipulação do banco de dados e segue boas práticas de desenvolvimento.

## Tecnologias Utilizadas

- Java 21 (https://www.oracle.com/br/java/technologies/downloads/#java21)

- Spring Boot

- Spring Data JPA

- Swagger OpenAPI 

- Banco de Dados H2 (para testes) ou PostgreSQL

- Maven

## Como Executar o Projeto

1. Clonar repositório
```
git clone https://github.com/ThamyCorrea/AppContatos.git 
```
## 2. Alguns exemplos para Configurar Banco de Dados

Configure o banco de dados:

### **H2:**

Arquivo application.properties:
```
spring.datasource.url=jdbc:h2:mem:cadastrodb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

Dependência no pom.xml
```
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
- Para PostgreSQL
```

### **PostgreSQL**

Arquivo application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/meubanco
spring.datasource.username=meuusuario
spring.datasource.password=senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Dependência no pom.xml
```
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>

```
### **MariaDB**

Arquivo application.properties:
```
spring.datasource.url=jdbc:mariadb://localhost:3306/meubanco
spring.datasource.username=meuusuario
spring.datasource.password=senha
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect
```

Dependência no pom.xml
```
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <scope>runtime</scope>
</dependency>

``` 
## Endpoints Disponíveis

✅Pessoas
- Criar Pessoa: POST /api/pessoas
**Request Body:**
  ```json
   
    {  
      "nome": "string",  
      "endereco": "string",  
      "cep": "string",  
      "cidade": "string",  
      "uf": "string"  (aceita apenas 2 caracteres maiúsculos)  
    }  
  
- Obter Pessoa por ID: GET /api/pessoas/{id}
- Obter Pessoa por ID para Mala Direta GET /api/pessoas/malaDireta/{id}
- Listar todas as Pessoas: GET /api/pessoas
- Atualizar Pessoa: PUT /api/pessoas/{id}
  **Request Body:**
  ```json
    
    {  
      "nome": "string",
      "endereco": "string",  
      "cep": "string",  
      "cidade": "string",  
      "uf": "string"  (aceita apenas 2 caracteres maiúsculos)  
    }  
  
- Deletar Pessoa: DELETE /api/pessoas/{id}

✅Contatos
- Criar Contato: POST /api/contatos
  
**Request Body:**
  ```json
  [  
    {  
        "tipoContato": "string",  (Escolha entre: 0 (Telefone), 1 (Celular), 2 (E-mail) ou 3 (URL))
        "contato": "string",
        "pessoa": {"id": int}  
    }  
  ]
```
- Obter todos os Contatos de uma Pessoa: GET /api/contatos/pessoas/{idPessoa}
- Obter Contato por ID: GET /api/contatos/{id}
- Atualizar Contato: PUT /api/contatos/{id}
  **Request Body:**
  ```json
  [  
    {  
        "tipoContato": "string", (Escolha entre: 0 (Telefone), 1 (Celular), 2 (E-mail) ou 3 (URL))
        "contato": "string",
        "pessoa": {"id": int}  
    }  
  ]
- Deletar Contato: DELETE /api/contatos/{id}

## Testando a API

Após iniciar o projeto, você pode testar os endpoints usando:

Swagger UI: http://localhost:8080/swagger-ui.html

Postman ou Insomnia

## Ajustes e melhorias
### Projeto em constante desenvolvimento
- [ ] Adicionar mais tipos de contato
- [ ] Melhoras as validações dos atributos
- [ ] Criar um sistema de gerenciamento de erros mais robusto
- [ ] Implemente autenticação e autorização para proteger os endpoints sensíveis
- [ ] Adicionar diferentes níveis de acesso (por exemplo, administrador, usuário comum) para gerenciar permissões
- [ ] Desenvolver uma interface gráfica (frontend) para interagir com a API

## Aceito sugestões de melhorias 🤩

Para contribuir, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push -u origin <nome_branch>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).


## 🚩Autor

Desenvolvido por Thamiris de Oliveira Corrêa

- GitHub: https://github.com/ThamyCorrea
- LinkedIn: https://www.linkedin.com/in/thamiris-correa/




