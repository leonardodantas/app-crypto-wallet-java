# APP-CRYPTO-WALLET-JAVA

Aplicação SpringBoot Java para compra e armazenamento de cryptomoedas, seguindo os principios de boas praticas do livro Clean Architecture do uncle Bob. 


<div style="display: inline_block">

  <img align="center" alt="java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />

</div><br/>

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


## Apêndice

Para calcular a tendencia da moeda foi utilizada uma função de regressão linear. Em uma aplicação complexa varios outros fatores deveriam ser considerados para esse calculo, porem utilizei uma lib simples e que considera apenas dois fatores em seu calculo(tempo e valor) para fins de estudo.
