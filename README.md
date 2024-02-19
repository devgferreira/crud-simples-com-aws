# Crud Simple com AWS

---

## Concepção da API
A concepção da API adotou os preceitos fundamentais da Clean Architecture, aliados à filosofia do clean code.

O projeto é envolta de um crud simples e é baseado em um estrutura monolítica. De forma simplificada, a estrutura do projeto é contruiída em torno das controller CategoryController e ProductController, onde um Product precisa de uma Category para existir.

Tal abordagem possobilita a criação de um Product.

## Ferramentas usadas

AWS: Sistema de serviço de cloud.

S3: Sistema de armazenamento de dados da AWS.

SQS: Sistema de fila de mensageria da AWS.

Lambda: Sistema orientado a evento da AWS.

SNS: Sistema de mensageria da AWS.

Java 17: Linguagem de programação.

MongoDB: Sistema de banco de dados NoSql.

Recomendo fortemente que você tenha essas ferramentas instaladas.

## Como iniciar as APIs
Você precisa ter instalado as ferramentas mencionadas no tópico anterior. 

Uso de uma IDE: Vs Code, Eclipse e Intellij.

Essas ferramentas e instruções irão ajudá-lo a configurar e executar a API de forma eficiente.

## Como usar a AWS

Criar a fila de mensageria no SQS com o nome: catalog-update.

Criar um topic no SNS: catalog-emit, e vincular ele a fila de mensageria do SQS: catalog update.

Criar uma IAM com as permissões do SQS, SNS e S3 assim podendo fazer o acesso via IDE.

Cria uma função Lambda com o nome: catalog-emit-consumer, dar permissão para o S3 e SQS, adicionar um gatilho apontando para o SQS e criar o script para a criação de .json em um bucket no S3, para ter acesso ao script clique [aqui].

Criar um bucket no S3 com o nome: crud-simples-catalog-marketplace.

E por fim, configurar as credenciais no application.yml dentro da estrutura de projeto Java.  


# Estrutura do Projeto
## Apresentation
Controlador: Controladores de API, responsáveis por receber solicitações e enviar respostas.

## Application
DTOs: Objetos de Transferência de Dados usados para passar dados entre camadas.

Interfaces: Contratos para os serviços.

Services: Contém lógica de negócios de alto nível e chama métodos do repositório.

## Domain
Entity/model: Entidades de domínio.

Repository: Contratos para os Repositórios.

Enums: Enumerações usadas em entidades e/ou regras de negócio.

## Infra
Config: Configuração de dependência do projeto.
  - Aws: Configuração do bean da AWS.
  - ModelMapper: Configuração do bean do ModelMapper.
  - Mongo: Configuração do bean do MongoDB.

Exceptions: Configuração de exceção e seu manipulador.
  - Constants: Regras onde os valores não podem ser mutáveis.
  - Handler: Onde manipulamos as exceptions.

