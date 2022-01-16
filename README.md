# APP-CRYPTO-WALLET-JAVA

Aplicação SpringBoot Java para compra e armazenamento de cryptomoedas, seguindo os principios de boas praticas do livro Clean Architecture do uncle Bob. 

## Documentação da API

#### Retorna a tendencia de venda e compra da criptomoeda

  GET /get-cryptocurrency-trend/{cryptocurrency}

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cryptocurrency` | `string` | **Obrigatório**. Nome da criptomoeda |
