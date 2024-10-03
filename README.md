# APP-CRYPTO-WALLET-JAVA

<p>
Refatoração realizada em um projeto após 2 anos, com o objetivo de entender minhas principais evoluções nesse período. A release anterior pode ser encontrada no seguinte endereço: https://github.com/leonardodantas/app-crypto-wallet-java/tree/release-v1.0.0, e a release refatorada está disponível em: https://github.com/leonardodantas/app-crypto-wallet-java/tree/release-v1.0.0.
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
  Substituição de classes por records para representar objetos que possuem apenas getters, aproveitando os recursos modernos do Java para simplificar o código e melhorar a legibilidade.
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
Na versão anterior, uma única classe era usada tanto para representar o domínio quanto para servir como entidade do banco de dados, como no exemplo a seguir:

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
Com a refatoração na nova release, agora temos uma classe dedicada para representar o documento do banco de dados e outra para representar o domínio da aplicação. Essa abordagem se refere ao conceito de separação de responsabilidades (Separation of Concerns), que sugere que diferentes partes de uma aplicação devem ser responsáveis por diferentes aspectos ou funcionalidades.

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
Na release anterior do projeto, um determinado endpoint exigia a realização de uma série de requisições de forma síncrona para uma API externa durante o processamento, conforme exemplo a seguir:

```
    @Override
    public List<TickerResponse> getAllTicker() {
        List<DigitalCurrencyAcronymResponse> digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .collect(Collectors.toUnmodifiableList());
    }
```
Devido a esse processamento, o tempo de resposta era consideravelmente alto. Como a resposta a essa chamada tende a variar apenas após algumas horas, desenvolvi uma funcionalidade na nova release responsável por buscar essas informações e armazená-las em uma base de dados em intervalos específicos durante o dia. Para otimizar a performance, as requisições agora são realizadas de forma assíncrona utilizando WebFlux.

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

O princípio da imutabilidade de código foi aplicado na nova release, garantindo que nenhum atributo de um objeto possa ser alterado sem que um novo objeto seja criado. Na release anterior, trechos de código eram estruturados da seguinte maneira:

```
    public void overrideWallet(Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
```
Com imutabilidade o codigo foi refatorado para o codigo a seguir:
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

Na nova release, busquei aproveitar ao máximo os recursos do Swagger para aprimorar a documentação de cada endpoint do projeto, conforme exemplo a seguir:

```
@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
@Tag(name = "APP-CRYPTO-WALLET-JAVA", description = "Gerenciamento de criptomoedas")
public class AddCryptocurrencyWalletController {

    private final AddCryptocurrencyWallet addCryptocurrencyWallet;

    @Operation(summary = "Adicionar criptomoedas na carteira")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criptomoeda adicionada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.crypto.wallet.domain.CryptocurrencyWallet.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptocurrencyWalletResponse addCryptocurrency(@Valid @RequestBody final CryptocurrencyWalletRequest request) {
        final var domain = addCryptocurrencyWallet.addCryptocurrency(Cryptocurrency.of(request.name(), request.quantity()));
        return CryptocurrencyWalletResponse.from(domain);
    }
}
```
### Utilização de docker compose para subir a aplicação
Foi criado um arquivo Docker Compose responsável por iniciar uma instância do MongoDB e configurar o Mongo Express. Além disso, um script JavaScript foi desenvolvido para inserir os dados necessários para que a aplicação funcione corretamente.

### Mongo Express para acessar dados no MongoDB
Após a execução do docker compose é possivel acessar os dados armazenados no MongoDB via interface, basta acessar a url http://localhost:8081/ com o usuario **admin** e a senha **pass**

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
