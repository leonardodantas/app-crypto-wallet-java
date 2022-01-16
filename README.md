# APP-CRYPTO-WALLET-JAVA

Aplicação SpringBoot Java para compra e armazenamento de cryptomoedas, seguindo os principios de boas praticas do livro Clean Architecture do uncle Bob. 

## Documentação da API

### Retorna a tendencia de venda e compra da criptomoeda

```
  GET /get-cryptocurrency-trend/{cryptocurrency}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cryptocurrency` | `string` | **Obrigatório**. Nome da criptomoeda |

### Adiciona a criptomoeda em sua carteira

```
  POST /add-crypto-currency-wallet
```

A requisição precisa de um body com os seguintes parametros:
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório**. Nome da criptomoeda |
| `quantity` | `double` | **Obrigatório**. Quantidade a ser comprada |

### Retorna todas criptomoedas em sua carteira

```
  GET /find-crypto-currency-wallet
```
### Retorna a criptomoeda caso ela exista em sua carteira

```
  GET /find-crypto-currency-wallet/{cryptocurrency}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cryptocurrency` | `string` | **Obrigatório**. Nome da criptomoeda |

### Resumo de operação de todas as criptomoedas disponiveis na aplicação

```
  GET /get-last-day-all-cryptocurrency-summary
```
