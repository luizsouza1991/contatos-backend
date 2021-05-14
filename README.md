# Contatos api
Descrição: É Utilizado o spring boot e padrãos de projeto para estruturar o projeto. Postgres é usado como banco de dados.

## Project setup

### Application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/dbteste
spring.datasource.username=postgres
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update
server.port=8080

Observação: substitua os dados de autenticação do postgres conforme as suas configurações

Levando em consideração que você tenha criado um banco com o nome "dbteste" e configurando os demais campos, implica na comunicação entre back-end e bancos de dados.
```

### Build & Run
```
Utilizando o Eclipse:
src/main/java > com.contato.apicontato, temos o arquivo ApicontatoApplication, onde será executado através de um "Java Application.
botão direito > Run As > Java Application.
```