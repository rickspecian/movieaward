# Pré-requisitos
- Oracle JDK 8
- Maven 3.8

# Configurações Gerais
- Spring Tool Suite 4 para back-end
- Mavel 3.8 para controle de dependências no back-end
- JPA
- H2 in memory
- Testes com JUnit
- Arquivo CSV movielist.csv
- Arquivo Movie.postman_collection.json para importar no Postman para requisições externas

# Execução
Download

```
git clone https://github.com/rickspecian/movieaward.git
```

Mover o arquivo "movielist.csv", que consta na pasta 'CSV', para a pasta local do seu computador home\user. 

```
Ex: "C:\users\server1" (win) ou "/home/server1" (unix)
```

Após a importação para a IDE, realizar o Maven Install para baixar as dependências necessárias



#Necessidade da API

Esta API foi desenvolvida para gravar, ler, atualizar, excluir e classificar os registros dentro da tabela MOVIE.

Para utilização, deverá ser armazenado o arquivo CSV no local determinado no tópico de Execução e com a seguinte estrutura:


	year(INT);title(STRING);studios(STRING);producers(STRING);winner(YES/NO/NULL)
	
Ao iniciar a aplicação, ocorrerá a leitura do arquivo na pasta mencionada acima e acionará o método saveAll do JPA, 
para persistir os registro em banco na tabela Movie.

Após a API iniciada, poderá ser utilizado os seguintes métodos:

- Para inclusão/criação de novo filme

```
Requisição: http://localhost:8080/movie/ (POST)
body sample raw (JSON): 
{
    "year": 1985,
    "title": "Title 1",
    "studios": "Studio 1",
    "producers": "Producer 1",
    "winner": true
}


Retornará um JSON com o código ID em caso de sucesso e status 201

Retorno exemplo:
{
    "id": 415,
    "year": 1985,
    "title": "Title 1",
    "studios": "Studio 1",
    "producers": "Producer 1",
    "winner": true
}
```

- Para busca de um filme específico

```
Requisição: localhost:8080/movie/{id} (GET)
Onde {id} deverá ser substituido pelo código Id correspondente.
Retornará um JSON em caso de sucesso e status 201
Retorno exemplo ID 50:
{
    "id": 50,
    "year": 1988,
    "title": "Rambo III",
    "studios": "TriStar Pictures, Carolco Pictures",
    "producers": "Buzz Feitshans",
    "winner": false
}
Retornará um JSON com a message "Objeto não encontrado! Id: {id}, Tipo: com.goldenawards.rest.domain.Movie" e status 404 em caso de ID não encontrado
```

- Para alteração de um filme

```
Requisição: http://localhost:8080/movie/{id} (PUT)
Onde {id} deverá ser substituido pelo código Id correspondente.
body sample raw (JSON): 
{
    "year":1985,
    "title": "testado",
    "studios":"todos",
    "producers":"nenhum",
    "winner":true
}
Retornará um JSON com o código ID em caso de sucesso e status 201
Retorno exemplo ID 2:
{
    "id": 2,
    "year": 1985,
    "title": "testado",
    "studios": "todos",
    "producers": "nenhum",
    "winner": true
}
```

- Para exclusão de um filme

```
Requisição: http://localhost:8080/movie/{id} (DELETE)
Onde {id} deverá ser substituido pelo código Id correspondente.
Retornará um status 204
```

- Para solicitar os producers com menor e maior intervalo de tempo de ganho do prêmio
Este método faz um split dos produtores de um filme e faz a classificação por produtor, determinando os produtores que ganharam um prêmio com menor intervalo e com maior intervalo de anos.

```
Requisição: localhost:8080/movie/classification (GET)
Retornará um JSON com um array com a classificação "min" e "max" e status 200.
Retorno exemplo:
{
    "min": [
        {
            "producer": "Joel Silver",
            "interval": 1,
            "previousWin": 1990,
            "followingWin": 1991
        }
    ],
    "max": [
        {
            "producer": "Matthew Vaughn",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ]
}
```