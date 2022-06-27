# ATS-API
## Descrição do ptojeto
Este proveto foi desenvolvido durante o desafio para um processo seletivo e tem por objetivo ser o back-end de uma aplicação web do tipo ats.

## Funcionalidades
### Candidatos
- Cadastrar um candidato;
- Consultar um candidato pelo id;
- Consultar a lista de todos os candidatos;
- Editar um candidato;
- Excluir um candidato;
- Fazer upload de currículo para um candidato;
- Remover o currículo de um candidato;
- Fazer login do candidato;

### Recrutadores
- Cadastrar um recrutador;
- Consultar um recrutador pelo id;
- Consultar a lista de todos os recrutadores;
- Editar um recrutador;
- Excluir um recrutador;
- Fazer login do recrutador;

### Vagas
- Cadastrar uma vaga;
- Consultar uma vaga pelo id;
- Consultar a lista de todas as vagas criadas por um recrutador;
- Editar uma vaga;
- Excluir uma vaga;

### Aplicações
- Criar a aplicação de um candidato para uma vaga;
- Consultar todas as aplicações para uma vaga;
- Consultar todas as aplicações de um candidato;
- Editar uma aplicaçao;
- Excluir uma aplicação;

## Como executar a aplicação
A aplicação está dividida em 4 microsserviços, cada um possui sua respectiva imagem docker. Para executá-los, é necessário ter o [docker](https://www.docker.com/) instalado na máquina.

### Clone do projeto na máquina
`git clone https://github.com/bruna-nb/ats-api.git`

### Pull das imagens no docker

#### Postgres
O Postgres é o banco de dados utilizado pela aplicação. Para fazer o pull da imagem, basta digitar o seguinte comando no cmd: 
`docker pull postgres`

#### candidato-api
É o microsserviço responsável por gerenciar os candidatos na aplicação. Para fazer o pull da imagem, basta digitar o seguinte comando no cmd: 

`INSERIR COMANDO AQUI`

#### recrutador-api
É o microsserviço responsável por gerenciar os recrutadores na aplicação. Para fazer o pull da imagem, basta digitar o seguinte comando no cmd: 

`INSERIR COMANDO AQUI`

#### vaga-api
É o microsserviço responsável por gerenciar as vagas na aplicação. Para fazer o pull da imagem, basta digitar o seguinte comando no cmd: 

`INSERIR COMANDO AQUI`

#### ats-main-api
É o microsserviço responsável por gerenciar as candidaturas/aplicações de candidatos a vagas na aplicação. Para fazer o pull da imagem, basta digitar o seguinte comando no cmd: 

`INSERIR COMANDO AQUI`

### Colocar serviços no ar usando docker-compose
Neste repositório existe uma pasta chamada [docker](https://github.com/bruna-nb/ats-api/tree/main/docker) onde contém um arquivo de nome [docker-compose.yml](https://github.com/bruna-nb/ats-api/blob/main/docker/docker-compose.yml). 

Considerando que o projeto já está clonado em sua máquina e que todas as imagens já estão baixadas, basta abrir o cmd no caminho da pasta docker que existe no projeto e executar o comando: 
`docker-compose up`

Este comando fará com que os quatro microsserviços e o postgres sejas executados. Para isso, é necessário que as seguintes portas estejam livres: 

- 8080
- 8081
- 8082
- 8083
- 5432

## Documentação da aplicação
Assim que os serviços estiverem todos rodando, a documentação de cada uma das aplicações pode ser encontrada em: http://localhost:PORT/swagger-ui.html, conforme as portas listadas abaixo.

### candidato-api
http://localhost:8080/swagger-ui.html

### recrutador-api
http://localhost:8081/swagger-ui.html

### vaga-api
http://localhost:8082/swagger-ui.html

### ats-main-api
http://localhost:8083/swagger-ui.html

## Collection do Postman
Na pasta [postman](https://github.com/bruna-nb/ats-api/tree/main/postman) é possível encontrar uma collection que possui as requisições para todos os endpoints de cada api. As requisições estão configuradas usando variáveis de ambiente, para que elas funcionem, além de importar a [collection](https://github.com/bruna-nb/ats-api/blob/main/postman/ats-api.postman_collection.json) para o postman é necessário importar também o [ambiente](https://github.com/bruna-nb/ats-api/blob/main/postman/ats-api.postman_environment.json).