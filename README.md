# TrabFinal-ProjEDesenvDeSoftware

OBSERVAÇÕES/USO

Passos do desenvolvimento:

Backend:
1 - Backend desenvolvido com Java 8 no VSCode;
2 - Projeto criado com base na arquitetura Clean;
3 - API principal do projeto: Spring Boot;
4 - API para organização de código: Maven;

Testes:
1 - A classe de testes encontra-se em: Projeto\src\test\java\com\eventoapp\eventoapp\EventoappApplicationTest.java;
2 - Para executar os testes, o Maven deve estar instalado. Ao executar, basta abrir o terminal do VSCode;e digitar "mvn-test" sem aspas;
3 - As classes onde havia lógica foram contempladas pelos testes, são elas: DescontoPromocional.java, ValoresPorDiasDaSemana.java e ValoresPorQtdPessoas.java
OBS: A implementação dos testes teve como base a utilização da técnica de Testes Parametrizados, onde pudemos utilizar uma única classe para testar vários cenários diferentes.

Banco de dados:
1 - O banco de dados utilizado foi o MySQL 5.7;

Frontend:
1 - Páginas feitas com HTML;
2 - Estilização feita com o CSS;
3 - Principal framework de estilização: Materialize;
4 - A lógica por trás do frontend como por exemplo, pegar os dados de formulários e enviar para o backend processar foi feita com JavaScript;

Execução da aplicação:
1 - Para executar a aplicação basta digitar "mvn spring-boot:run" sem aspas, no terminal do VSCode;

Tecnologias utilizadas:
1 - Java;
2 - Spring Boot;
3 - Maven;
4 - MySQL;
5 - HTML;
6 - CSS;
7 - JavaScript;

