# Crud Simple com AWS

---

## Concepção da API
A concepção da API adotou os preceitos fundamentais da Clean Architecture, aliados à filosofia do clean code.

O projeto é envolta de um crud simples com __AWS__ e é baseado em um estrutura monolítica. De forma simplificada, a estrutura do projeto é construida em torno das controller CategoryController e ProductController, onde um Product precisa de uma Category para existir.

Tal abordagem possobilita a criação de uma Category e de um Product, onde a API ira processar a requisição e mandar uma mensagem via SNS para a AWS que será processada em uma função lambda, e caso não aja erro vai ser salva em um arquivo .json dentro de um bucket no S3.

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
- Controller: Controladores da API, responsáveis por receber solicitações e enviar respostas.

## Application
- DTOs: Objetos de Transferência de Dados usados para passar dados entre camadas.

- Interfaces: Contratos para os serviços.

- Services: Contém lógica de negócios de alto nível e chama métodos do repositório.

## Domain
- Entity/model: Entidades de domínio.

- Repository: Contratos para os Repositórios.

- Enums: Enumerações usadas em entidades e/ou regras de negócio.

## Infra
- Config: Configuração de dependência do projeto.
  - Aws: Configuração do bean da AWS.
  - ModelMapper: Configuração do bean do ModelMapper.
  - Mongo: Configuração do bean do MongoDB.

- Exceptions: Configuração de exceção e seu manipulador.
  - Constants: Regras onde os valores não podem ser mutáveis.
  - Handler: Onde manipulamos as exceptions.

# API
## Crud-Simples-Com-Aws
A API ‘crud-simples-com-aws’ é um CRUD básico que processa requisições para ‘Category’ e ‘Product’. O diferencial desta API reside no uso de várias tecnologias da __AWS__. Utiliza-se o __SNS__ para enviar mensagens em formato JSON, o __SQS__ para gerenciar a fila de mensagens, o __Lambda__ para processar as mensagens e o __S3__ para armazenar as requisições de ‘Product’ e ‘Category’. Este conjunto de tecnologias otimiza a eficiência e a escalabilidade do sistema. 

E consiste com dois controller: CategoryController e ProductController.

CategoryController: O CategoryControlelr tem quatro end-points.
  - createCategory: Esse end-point é responsável por fazer a criação de uma nova Category.
  - getAll: Esse end-point é responsável por trazer todas as Category.
  - updateCategory: Esse end-point é responsável por atualizar uma Category existente.
  - deleteCategory: Esse end-point é reponsável por deletar uma Category existente.

ProductController: O ProductController tem quatro end-points.
 - createProduct: Esse end-point é responsável por fazer a criação de um nova Product.
 - getAll: Esse end-point é responsável por trazer todas os Product.
 - updateProduct: Esse end-point é responsável por atualizar um Product existente.
 - deleteProduct: Esse end-point é reponsável por deletar um Product existente.
