# API de Cálculo do Preço Tarifado de Produtos de Seguros

Esta API fornece endpoints para calcular o preço tarifado de um produto de seguro a partir do preço base. A partir de uma tabela de categorias de seguros, aplica-se as porcentagens dos impostos e taxas correspondentes a aquela categoria.
O cálculo do preço tarifado será feito ao inserir ou atualizar um produto.



## Endpoints

### Listar Produtos [GET /api/produto]
Retorna uma lista de todos os produtos de seguro cadastrados.
#### Resposta
- 200 OK: Retorna a lista de produtos com sucesso.
	Modelo ResponseBody:
	{
    	"id": "65e67131da25bf0cc3398c6a",
    	"nome": "Seguro de Vida Individual 6",
    	"categoria": "VIDA",
    	"precoBase": 120.0,
    	"precoTarifado": 126.0
	}

### Buscar Produto por ID [GET /api/produto/{id}]
Retorna um produto de seguro a partir do ID especificado no parâmetro.
#### Parâmetros de Path
- id (string): O ID do produto a ser buscado.
#### Resposta
- 200 OK: Retorna o produto com o ID especificado.
- 404 Not Found: Se o produto não for encontrado.

### Criar Produto [POST /api/produto]
Cria um novo produto de seguro com base nos dados fornecidos.
#### Corpo da Requisição
{
  "nome": "Seguro de automovel ABC",
  "categoria": "AUTO",
  "precoBase": 130,
  "precoTarifado": 110
}
OBS: Como o preço tarifado é calculado, se ele for passado será ignorado.
#### Resposta
201 Created: Retorna o produto criado com sucesso, incluindo o preço tarifado calculado.
	Modelo ResponseBody:
{
    "id": "65e6e8c08701f059505e60ce",
    "nome": "Seguro de automovel ABC",
    "categoria": "AUTO",
    "precoBase": 130.0,
    "precoTarifado": 143.65
}

### Atualizar Produto [PUT /api/produto/{id}]
Atualiza um produto de seguro existente com base no ID especificado.
#### Parâmetros de Path
- id (string): O ID do produto a ser atualizado.
#### Corpo da Requisição
{
  "nome": "Seguro de automovel DEF",
  "categoria": "VIAGEM",
  "precoBase": 140
}
#### Resposta
- 200 OK: Retorna o produto atualizado com sucesso, incluindo o preço tarifado recalculado.
- 404 Not Found: Se o produto não for encontrado.



### Executando Localmente
Clone o repositório.
Atualize o ambiente com as dependências necessárias.
Compile a aplicação para que seja gerado o .jar, e em seguida execute-a:
	- mvn clean package
	- java -jar ./target/SegItau-1.0-SNAPSHOT.jar
Pronto! A API está de pé.
Acesse os endpoints usando um cliente HTTP, como Postman ou cURL, ou no próprio navegador de sua preferência digite na barra de endereço:
	http://localhost:8080/api/produto



### Tecnologias utilizadas
Java 17
Spring Boot
Spring Web
Spring Data MongoDB
NoSQL MongoDB
Postman
JUnit5
Mockito
Prometheus (não consegui terminar a implementação do framework para observabilidade)



### Contribuindo
Contribuições são bem-vindas! Se encontrar algum problema ou tiver alguma sugestão de melhoria, sinta-se à vontade para abrir uma issue ou enviar um pull request.

