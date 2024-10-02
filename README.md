# APP-CRYPTO-WALLET-JAVA

<p>
Refatoração feita com o objetivo de entender e aplicar as evoluções tecnicas que tive nos ultimos 2 anos.
</p>

### :hammer: Pré-requisitos

- IDE de sua preferência.
- JDK 21.
- Docker e Docker Compose

### 🛠 Detalhes Tecnicos

- Java 21
- Arquitetura baseada em Clean Arch
- Swagger
- MongoDB
- Docker e Docker Compose
- Inserção de dados de forma automatica
- WebFlux
- Spring Cloud

###  Iniciando projeto pela primeira vez

```bash
# Clone este repositório
git clone https://github.com/leonardodantas/app-crypto-wallet-java.git

# Tenha o docker compose instalando, acesse a pasta raiz do projeto e execute o seguinte comando
docker-compose up

# O comando acima ira criar instâncias das seguintes aplicações
- Mongo
- Mongo Express
 
# Na primeira execução do projeto, executar o seguinte comando após subir os containers via docker compose
docker exec -it mongodb mongo /scripts/insert_script.js

# Inicie a aplicação com uma IDE de sua preferência

#Acesse o seguinte endereço no navegador
http://localhost:8080/swagger-ui/index.html
```

## Documentação da API

### Retorna todas as criptomoedas

```
  GET /cryptocurrency/{cryptocurrency}/trend
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8080/cryptocurrency' \
  -H 'accept: application/json'
```

### Retorna a tendência de venda e compra da criptomoeda

```
  GET /cryptocurrency/{cryptocurrency}/trend
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cryptocurrency` | `string` | **Obrigatório**. Nome da criptomoeda |

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8080/cryptocurrency/btc/trend' \
  -H 'accept: application/json'
```

### Adiciona a criptomoeda em sua carteira

```
  POST /crypto
```

A requisição precisa de um body com os seguintes parâmetros:
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório**. Nome da criptomoeda |
| `quantity` | `double` | **Obrigatório**. Quantidade a ser comprada |

CURL de exemplo:

```
curl -X 'POST' \
  'http://localhost:8080/crypto' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "BTDC",
  "quantity": 90
}'
```

### Retorna a criptomoeda caso ela exista em sua carteira

```
  GET /cryptocurrency/{cryptocurrency}/wallet
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cryptocurrency` | `string` | **Obrigatório**. Nome da criptomoeda |

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8080/cryptocurrency/btcd/wallet' \
  -H 'accept: application/json'
```

### Resumo de operação de todas as criptomoedas disponíveis na aplicação

```
  GET /lastday/cryptocurrency/summary
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8080/lastday/cryptocurrency/summary' \
  -H 'accept: application/json'
```

## Principais refatorações aplicadas 
### Classes para records

<p>
  Substituição de classes por records para representar objetos que possuem apenas getters, fazendo assim uso de recursos mais novos do Java.
</p>

CryptocurrencySummaryResponse antiga:

```
@Getter
public class CryptocurrencySummaryResponse {

    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal vol;
    private final BigDecimal last;
    private final BigDecimal buy;
    private final BigDecimal sell;
    private final BigDecimal open;
    private final LocalDateTime date;

    private CryptocurrencySummaryResponse(ITickerDTO ticker) {
        this.high = ticker.getHigh();
        this.low = ticker.getLow();
        this.vol = ticker.getVol();
        this.last = ticker.getLast();
        this.buy = ticker.getBuy();
        this.sell = ticker.getSell();
        this.open = ticker.getOpen();
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(ticker.getDate()), ZoneId.of("America/Sao_Paulo"));
    }

    public static CryptocurrencySummaryResponse from(ITickerDTO ticker) {
        return new CryptocurrencySummaryResponse(ticker);
    }
}
```

CryptocurrencySummaryResponse nova:

```
public record CryptocurrencySummaryResponse(
        BigDecimal high,
        BigDecimal low,
        BigDecimal vol,
        BigDecimal last,
        BigDecimal buy,
        BigDecimal sell,
        BigDecimal open,
        LocalDateTime date
) {
    public static CryptocurrencySummaryResponse from(final CryptocurrencySummary cryptocurrencySummary) {
        return new CryptocurrencySummaryResponse(
                cryptocurrencySummary.high(),
                cryptocurrencySummary.low(),
                cryptocurrencySummary.vol(),
                cryptocurrencySummary.last(),
                cryptocurrencySummary.buy(),
                cryptocurrencySummary.sell(),
                cryptocurrencySummary.open(),
                cryptocurrencySummary.date()
        );
    }
}
```
### Separação de responsabilidades
Na versão anterior, um unica classe era utilizada tanto para representar o dominio quanto para representar um entidade do banco de dados, como no exemplo a seguir:
```
@Entity
@Getter
@Table(name = "digital_currency_acronym")
public class DigitalCurrencyAcronym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 60)
    private String name;
    private String description;

}
```
Com a refatoração da nova release, agora temos uma classe para representar o documento do banco de dados, e outra para representar o dominio da aplicação.

Classe para representar o documento do banco de dados:
```
@Getter
@Document("digital_currency_acronym")
public class DigitalCurrencyAcronymDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    private DigitalCurrencyAcronymDocument(final String id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static DigitalCurrencyAcronymDocument from(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymDocument(digitalCurrencyAcronym.id(), digitalCurrencyAcronym.name(), digitalCurrencyAcronym.description());
    }

    public static DigitalCurrencyAcronymDocument from(final Ticker ticker) {
        return new DigitalCurrencyAcronymDocument(UUID.randomUUID().toString(), ticker.digitalCurrencyAcronym().name(), ticker.digitalCurrencyAcronym().description());
    }
}
```
Record para representar o dominio:
```
public record DigitalCurrencyAcronym(
        String id,
        String name,
        String description
) {

    public static DigitalCurrencyAcronym of(final String id, final String name, final String description) {
        return new DigitalCurrencyAcronym(id, name, description);
    }
}
```
### Criação de schedule para otimizar performance
Na release anterior do projeto, em um determinado endpoint, durante o processamento era necessario a realização de uma serie de requisições de forma sincrona para uma api externa, da seguinte forma:
```
    @Override
    public List<TickerResponse> getAllTicker() {
        List<DigitalCurrencyAcronymResponse> digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .collect(Collectors.toUnmodifiableList());
    }
```
Devido a esse processamento, o tempo de resposta era consideravelmente alto. Por se tratar de um chamada onde a resposta tende a variar apenas apos algumas horas, desenvolvi
uma schedule na nova release, responsavel por buscar estas informações e armazenar em uma base de dados em periodos especificos durante o dia. Para uma melhor performance as requisições são feitas de forma assincrona com WebFlux.

```
    @Scheduled(cron = "0 0 0/3 * * ?")
    public void updateTickerSchedule() {
        log.info("EXECUÇÃO SCHEDULE TICKERS");
        validateUpdateTickers();
    }
```
Para garantir que a base de dados sempre estará populada, o metodo de inserção na base de dados tambem será executado sempre que a aplicação iniciar.
```
    @PostConstruct
    public void initialize() {
        log.info("EXECUÇÃO DE POSTSCONSTRUCT");
        validateUpdateTickers();
    }
```
### Imutabilidade
O principio de imutabilidade de codigo foi aplicado de forma rigorosa a nova release, em nenhum momento é possivel alterar um atributo de um objeto sem gerar um novo. Na release anterior existiam trechos de codigo da seguinte forma:
```
    public void overrideWallet(Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
```
Com imutabilidade o codigo foi refatorado da seguinte forma:
```
    public static Wallet of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Wallet(digitalCurrencyAcronym, cryptoWallet);
    }

    public void overrideWallet(Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
```

### Atualização do swagger

### Migração de H2 para MongoDB

### Utilização de docker compose para subir a aplicação

### Mongo Express para acessar dados no MongoDB





## Apêndice

Para calcular a tendência da moeda foi utilizada uma função de regressão linear. Em uma aplicação complexa vários outros fatores deveriam ser considerados para esse cálculo, porém utilizei uma lib simples e que considera apenas dois fatores em seu cálculo (tempo e valor) para fins de estudo.


## Tecnologias

<div style="display: inline_block">
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="swagger" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" />
  <img align="center" alt="docker" src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
  <img align="center" alt="mongodb" src="https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white" />
</div>

### :sunglasses: Autor

Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/)
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença

Este projeto esta sobe a licença MIT.
